package com.example.movieappmvvm.ui.base

import android.view.View
import androidx.paging.PagingData
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movieappmvvm.data.model.MovieDB
import com.example.movieappmvvm.data.model.moviesUiModel.MoviesUIModel

abstract class BaseRecyclerViewAdapter<T : Any> :
    ListAdapter<T, BaseRecyclerViewAdapter.BaseViewHolder<T>>(BaseItemCallback<T>()) {
    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) =
        holder.bind(getItem(position))


    abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(item: T)


        protected fun onClick(block: () -> Unit) = itemView.setOnClickListener {
            block.invoke()
        }
    }
}





