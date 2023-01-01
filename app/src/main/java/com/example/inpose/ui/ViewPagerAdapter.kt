package com.example.inpose.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.inpose.ui.following.FollowingFragment
import com.example.inpose.ui.photolog.PhotoLogFragment

class ViewPagerAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FollowingFragment()
            else -> PhotoLogFragment()
        }
    }
}