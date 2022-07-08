package com.devnight.pruebafirebase.ui.view.ui.home

import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.content.Intent.ACTION_GET_CONTENT
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.devnight.pruebafirebase.databinding.FragmentHomeBinding
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    var storage = Firebase.storage
    var rerefence: StorageReference = storage.getReferenceFromUrl("gs://examen-7aa4a.appspot.com")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root



        binding.imageView2.setOnClickListener {
            val intent = Intent(ACTION_GET_CONTENT)
            intent.setType("*/*")
            startActivityForResult(intent, 1)
        }





        return root
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 1){
            if (resultCode == RESULT_OK){
                val uri = data?.data


                if (uri != null) {
                  val filePath = rerefence?.child("${uri.lastPathSegment}")
                   val responseFile = filePath?.putFile(uri)

                   val urlTask = responseFile?.continueWithTask { task ->

                       if (!task.isSuccessful){
                           task.exception?.let {
                               val dialog = AlertDialog.Builder(activity?.applicationContext!!)
                               dialog.setMessage(it.localizedMessage)
                               dialog.setNeutralButton("Aceptar") { fra: DialogInterface, _: Int -> fra.dismiss() }
                           }
                       }
                       rerefence.downloadUrl
                   }.addOnCompleteListener { task -> if (task.isSuccessful){
                        val downloadUri = task.result
                       val dialog = AlertDialog.Builder(activity?.applicationContext!!)
                       dialog.setMessage(downloadUri.toString() + "Se subio correctamente")
                       dialog.setNeutralButton("Aceptar") { fra: DialogInterface, _: Int -> fra.dismiss() }
                    } else{
                       val dialog = AlertDialog.Builder(activity?.applicationContext!!)
                       dialog.setMessage("No se subio el documento")
                       dialog.setNeutralButton("Aceptar") { fra: DialogInterface, _: Int -> fra.dismiss() }
                    }
                    }
                }


            }
        }

    }





    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}