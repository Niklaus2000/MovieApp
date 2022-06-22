package com.example.movieappmvvm.ui.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieappmvvm.data.onboardingitem.OnBoardingItem
import com.example.movieappmvvm.databinding.OnboardingItemContainerBinding

class OnBoardingItemsAdapter(private var onBoardingList: List<OnBoardingItem>) :
    RecyclerView.Adapter<OnBoardingItemsAdapter.ViewHolder>() {


    class ViewHolder(val binding: OnboardingItemContainerBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int , ): OnBoardingItemsAdapter.ViewHolder {
        val binding = OnboardingItemContainerBinding.inflate(LayoutInflater.from(parent.context) , parent , false)

        return ViewHolder(binding)


    }

    override fun onBindViewHolder(holder: ViewHolder , position: Int) {
        val data = onBoardingList[position]
//        holder.binding.textTitle.text = data.title
        holder.binding.textDescription.text = data.description
        holder.binding.imageOnBoarding.setImageResource(data.onBoardingImage)
    }

    override fun getItemCount(): Int {
        return onBoardingList.size
    }
}