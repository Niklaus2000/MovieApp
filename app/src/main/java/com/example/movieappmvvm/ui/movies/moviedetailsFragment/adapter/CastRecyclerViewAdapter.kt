package com.example.movieappmvvm.ui.movies.moviedetailsFragment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.movieappmvvm.R
import com.example.movieappmvvm.data.model.Cast
import com.example.movieappmvvm.databinding.ItemCastBinding
import com.example.movieappmvvm.utils.CONSTANTS
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class CastRecyclerViewAdapter(val context: Context ,  val castList: ArrayList<Cast>) :
    RecyclerView.Adapter<CastRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemCastBinding.bind(itemView)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup ,
        viewType: Int ,
    ): CastRecyclerViewAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_cast, parent,false))
    }

    override fun onBindViewHolder(holder: CastRecyclerViewAdapter.ViewHolder , position: Int) {
        with(holder) {

            when (position) {
                0 -> {
                    binding.spacingStart.visibility = View.VISIBLE
                }
                20.coerceAtMost(castList.size) -1 -> {
                    binding.spacingEnd.visibility = View.VISIBLE
                }
                else -> {
                    binding.spacingEnd.visibility = View.GONE
                    binding.spacingStart.visibility = View.GONE
                }
            }

            binding.castImage.load(CONSTANTS.ImageBaseURL + castList[position].profile_path) {
                placeholder(CONSTANTS.actorPlaceHolder[position%4])
                error(CONSTANTS.actorPlaceHolder[position%4])
            }

            binding.castName.text = castList[position].name


        }

    }

    override fun getItemCount(): Int = 20.coerceAtMost(castList.size)


}