package com.shakib_mansoori.newsapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.shakib_mansoori.newsapp.fragments.*

class PagerAdapter(fm: FragmentManager?, var allTabs: Int) :
    FragmentPagerAdapter(fm!!, allTabs) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> HealthFragment()
            1 -> PoliticsFragment()
            2 -> SceinceFragment()
            3 -> ArtFragment()
            4 -> FoodFragment()
            else -> HealthFragment()
        }
    }

    override fun getCount(): Int {
        return allTabs
    }
}
