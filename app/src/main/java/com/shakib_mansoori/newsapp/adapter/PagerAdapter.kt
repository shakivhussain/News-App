package com.shakib_mansoori.newsapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.shakib_mansoori.newsapp.fragments.*


class PagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun createFragment(position: Int): Fragment {
        when (position) {
            1 -> return HealthFragment()
            2 -> return PoliticsFragment()
            3 -> return SceinceFragment()
            4 -> return ArtFragment()
            5 -> return FoodFragment()
        }
        return return HealthFragment();
    }

    override fun getItemCount(): Int {
        return 5
    }
}

