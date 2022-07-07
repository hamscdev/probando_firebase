package com.devnight.pruebafirebase.core.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.devnight.pruebafirebase.R
import com.devnight.pruebafirebase.domain.model.RecomendationModel

class MoviesAdapter(val moviesList: List<RecomendationModel>, val context: Context): RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    class ViewHolder (item: View) : RecyclerView.ViewHolder(item) {

        val title = item.findViewById<TextView>(R.id.title_movie)
        val description = item.findViewById<TextView>(R.id.description_movie)
        val date = item.findViewById<TextView>(R.id.release_date)
        val rating = item.findViewById<RatingBar>(R.id.rating_bar)


        fun render(it: RecomendationModel, context: Context){
            title.setText(it.title)
            description.setText(it.overview)
            date.setText(it.releaseDate)
            rating.rating = (it.voteAverage.toFloat() * 5)/10
        }


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_movies,parent,false))
    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.render(moviesList[position], context)
    override fun getItemCount(): Int = moviesList.size
}