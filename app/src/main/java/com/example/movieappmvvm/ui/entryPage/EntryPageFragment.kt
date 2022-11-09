package com.example.movieappmvvm.ui.entryPage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.movieappmvvm.R
import com.example.movieappmvvm.data.onboardingitem.OnBoardingItem
import com.example.movieappmvvm.databinding.FragmentEntryPageBinding
import com.example.movieappmvvm.ui.Adapter.OnBoardingItemsAdapter
import com.example.movieappmvvm.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EntryPageFragment : BaseFragment<EntryPageViewModel,FragmentEntryPageBinding>(FragmentEntryPageBinding::inflate){
    override val viewModel: EntryPageViewModel by viewModels()

    private lateinit var onBoardingItemsAdapter: OnBoardingItemsAdapter
//    private lateinit var indicatorsContainer: LinearLayout





    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)
        setOnBoardingItems()
        setUpIndicators()
        setCurrentIndicator(0)
    }
    private fun setOnBoardingItems() {
        onBoardingItemsAdapter = OnBoardingItemsAdapter(
            listOf(
                OnBoardingItem(
                    onBoardingImage = R.drawable.bitmap,
                    description = "Get the first \n" +
                            "Movie &TV information"
                ) ,
                OnBoardingItem(
                    onBoardingImage = R.drawable.bitmap,
                    description = "Know the movie \n" +
                            "is not worth Watching"
                ) , OnBoardingItem(
                    onBoardingImage = R.drawable.bitmap,
                    description = "Real-time \n" +
                            "updates movie Trailer"
                )
            )
        )
        val onBoardingViewPager = binding.onBoardingViewPager
        onBoardingViewPager.adapter = onBoardingItemsAdapter
        onBoardingViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })
        (onBoardingViewPager.getChildAt(0) as RecyclerView).overScrollMode =
            RecyclerView.OVER_SCROLL_NEVER
        binding.lottieNext.setOnClickListener {
            if (onBoardingViewPager.currentItem + 1 < onBoardingItemsAdapter.itemCount) {
                onBoardingViewPager.currentItem += 1
            }else {
                navigateToLoginAndRegister()
            }
        }
        binding.lottieSkip.setOnClickListener{
            navigateToLoginAndRegister()
        }
        binding.buttonGetStarted.setOnClickListener {
            val bounceAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.bounce_anim_button)
            view?.startAnimation(bounceAnimation)

            navigateToLoginAndRegister()
        }
    }
    private fun navigateToLoginAndRegister() {
//        startActivity(Intent(applicationContext,Loginandregister::class.java))
        findNavController().navigate(R.id.action_entryPageFragment_to_loginAndRegisterFragment)

    }
    private fun setUpIndicators() {
       // indicatorsContainer.findViewById<LinearLayout>(R.id.indicatorsContainer)
        val indicators = arrayOfNulls<ImageView>(onBoardingItemsAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT ,
                ViewGroup.LayoutParams.WRAP_CONTENT)
        layoutParams.setMargins(8,0,8,0)
        for (i in indicators.indices) {
            indicators[i] = ImageView(requireActivity())
            indicators[i]?.let {
                it.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireActivity(),
                        R.drawable.indicator_inactive_background
                    )
                )
                it.layoutParams = layoutParams
                binding.indicatorsContainer.addView(it)

            }
        }
    }
    private fun setCurrentIndicator(position: Int) {
        val childCount = binding.indicatorsContainer.childCount
        for (i in 0 until childCount) {
            val imageView = binding.indicatorsContainer.getChildAt(i) as ImageView
            if (i == position) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireActivity(),
                        R.drawable.indicator_active_background
                    )
                )
            }else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireActivity(),
                        R.drawable.indicator_inactive_background
                    )
                )
            }
        }
    }

}