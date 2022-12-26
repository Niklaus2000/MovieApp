package com.example.movieappmvvm.ui.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.movieappmvvm.data.model.moviesUiModel.MoviesUIModel
import com.example.movieappmvvm.databinding.ItemMovieHomeBinding
import com.example.movieappmvvm.ui.base.BaseRecyclerViewAdapter
import com.example.movieappmvvm.ui.core.OnItemClick

//@ExperimentalCoroutinesApi
//class HomeViewAdapter(val context: Context , val movies: ArrayList<MoviesUIModel>) :
//    RecyclerView.Adapter<HomeViewAdapter.ViewHolder>() {
//
//    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val binding = ItemMovieHomeBinding.bind(itemView)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int): ViewHolder {
//        return ViewHolder(LayoutInflater.from(parent.context)
//            .inflate(R.layout.item_movie_home , parent , false))
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder , position: Int) {
//
//        with(holder) {
//
//
//            binding.movieImage.load(CONSTANTS.ImageBaseURL + movies[position].image) {
//                placeholder(CONSTANTS.moviePlaceHolder[position % 4])
//                error(CONSTANTS.moviePlaceHolder[position % 4])
//            }
//            binding.textMovieName.text = movies[position].title
//            binding.textMovieRating.text = movies[position].vote_average.toString()
//
//            itemView.setOnClickListener {
//                val bundle = bundleOf(CONSTANTS.movie to movies[position])
//                it.findNavController().navigate(R.id.movieDetailsFragment , bundle)
//            }
//
//            if (position == movies.size - 1) {
//                binding.spacingEnd.visibility = View.VISIBLE
//            }
//        }
//
//    }
//
//    fun updateAll(items: List<MoviesUIModel>) {
//        this.movies.clear()
//        this.movies.addAll(items)
//        notifyDataSetChanged()
//    }
//
//    override fun getItemCount() = movies.size
//
//
//}

class HomeViewAdapter(
    private val itemClickListener: OnItemClick<MoviesUIModel> ,
) : BaseRecyclerViewAdapter<MoviesUIModel>() {


//    override fun onCreateViewHolder(
//        parent: ViewGroup ,
//        viewType: Int ,
//    ): BaseViewHolder<MoviesUIModel> =
//        if (viewType == 1) HomeItemViewHolder(
//            ItemMovieHomeBinding.inflate(
//                LayoutInflater.from(parent.context) ,
//                parent ,
//                false
//            ) , itemClickListener
//        )
//        else (HomeItemViewHolder(
//            ItemMovieHomeBinding.inflate(
//                LayoutInflater.from(parent.context) ,
//                parent ,
//                false
//            ) , itemClickListener
//        )
//                )
//
//    override fun getItemViewType(position: Int): Int = if (position == 0) 1 else 2


    override fun onCreateViewHolder(
        parent: ViewGroup ,
        viewType: Int ,
    ): BaseViewHolder<MoviesUIModel> =
       HomeItemViewHolder(
            ItemMovieHomeBinding.inflate(
                LayoutInflater.from(parent.context) ,
                parent ,
                false
            ) , itemClickListener
        )
}










