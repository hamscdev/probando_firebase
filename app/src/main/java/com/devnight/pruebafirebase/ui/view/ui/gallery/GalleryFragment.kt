package com.devnight.pruebafirebase.ui.view.ui.gallery

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.devnight.pruebafirebase.R
import com.devnight.pruebafirebase.databinding.FragmentGalleryBinding
import com.devnight.pruebafirebase.domain.model.Directions
import com.devnight.pruebafirebase.domain.model.ObjectValue
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class GalleryFragment : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentGalleryBinding? = null

    private val REQUEST_CODE = 200
    private lateinit var locationCallback: LocationCallback
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private lateinit var  mMap: GoogleMap
    private val binding get() = _binding!!

    val db = Firebase.firestore
    val collection = db.collection("maps").document("directions")


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel = ViewModelProvider(this).get(GalleryViewModel::class.java)
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root


        fusedLocationClient = activity?.let { LocationServices.getFusedLocationProviderClient(it.applicationContext) }!!


        val mapFRagment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFRagment.getMapAsync(this)

        getCurrentLocation()


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



    override fun onMapReady(p0: GoogleMap) {
        mMap = p0
    }

    private fun getCurrentLocation() {
        if(getPermissions()) {

            var locationRequest = LocationRequest()
            locationRequest.interval = 900000
            locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY

            locationCallback = object: LocationCallback(){
                override fun onLocationResult(p0: LocationResult?) {
                    super.onLocationResult(p0)


                    val mapa  = hashMapOf(
                        "direccion" to p0?.lastLocation?.provider,
                        "latitud" to p0?.lastLocation?.latitude.toString(),
                        "longitud" to p0?.lastLocation?.longitude.toString()
                    )

                    collection.update("direct", FieldValue.arrayUnion(mapa))


                    collection.get().addOnSuccessListener {
                        task ->
                        val datos = Gson().toJson(task.data)
                        val data: ObjectValue = Gson().fromJson(datos.toString(), ObjectValue::class.java)
                        data.direct?.forEach {
                            changemaps(it.latitud, it.longitud)
                        }
                    }



                }
            }
            fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())

        }else{
            requestPermissions()
        }
    }

    private fun changemaps(lat: String, lng: String) {
        val sydney = LatLng(lat.toFloat().toDouble(), lng.toFloat().toDouble())
        mMap.addMarker(MarkerOptions().position(sydney))
        mMap.animateCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    private fun requestPermissions() {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), REQUEST_CODE)
    }


    private fun getPermissions(): Boolean {
        return activity?.applicationContext?.let { ContextCompat.checkSelfPermission(it, Manifest.permission.ACCESS_FINE_LOCATION) } == PackageManager.PERMISSION_GRANTED
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation()
            } else {
                getPermissions()
            }
        }
    }




}