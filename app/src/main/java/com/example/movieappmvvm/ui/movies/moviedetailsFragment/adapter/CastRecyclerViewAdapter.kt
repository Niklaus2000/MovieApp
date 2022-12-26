package com.example.movieappmvvm.ui.movies.moviedetailsFragment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.movieappmvvm.R
import com.example.movieappmvvm.data.model.Cast
import com.example.movieappmvvm.data.model.moviesUiModel.CastUIModel
import com.example.movieappmvvm.data.model.moviesUiModel.MoviesUIModel
import com.example.movieappmvvm.databinding.ItemCastBinding
import com.example.movieappmvvm.databinding.ItemMovieHomeBinding
import com.example.movieappmvvm.ui.base.BaseRecyclerViewAdapter
import com.example.movieappmvvm.ui.core.OnItemClick
import com.example.movieappmvvm.ui.movies.adapter.HomeItemViewHolder
import com.example.movieappmvvm.utils.CONSTANTS
import kotlinx.coroutines.ExperimentalCoroutinesApi

//class CastRecyclerViewAdapter(val context: Context ,  val castList: ArrayList<Cast>) :
//    RecyclerView.Adapter<CastRecyclerViewAdapter.ViewHolder>() {
//
//    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val binding = ItemCastBinding.bind(itemView)
//    }
//
//    override fun onCreateViewHolder(
//        parent: ViewGroup ,
//        viewType: Int ,
//    ): CastRecyclerViewAdapter.ViewHolder {
//        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_cast, parent,false))
//    }
//
//    override fun onBindViewHolder(holder: CastRecyclerViewAdapter.ViewHolder , position: Int) {
//        with(holder) {
//
//
//            binding.castImage.load(CONSTANTS.ImageBaseURL + castList[position].profile_path) {
//                placeholder(CONSTANTS.actorPlaceHolder[position%4])
//                error(CONSTANTS.actorPlaceHolder[position%4])
//            }
//
//            binding.castName.text = castList[position].name
//
//
//        }
//
//    }
//
//    override fun getItemCount(): Int = 20.coerceAtMost(castList.size)
//
//
//}

class CastRecyclerViewAdapter(
) : BaseRecyclerViewAdapter<CastUIModel>() {

    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int): BaseViewHolder<CastUIModel> =
        CastItemViewHolder(
            ItemCastBinding.inflate(
                LayoutInflater.from(parent.context) ,
                parent ,
                false
            )
        )
}