package com.bcebhagalpur.bcece.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bcebhagalpur.bcece.R
import com.google.android.material.tabs.TabLayout

@Suppress("DEPRECATION")
class HomeFragment : Fragment() {
    private lateinit var viewPager:ViewPager
    private lateinit var tabs:TabLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_home, container, false)

        viewPager=view.findViewById(R.id.viewPager)
        tabs=view.findViewById(R.id.tabs)
        val adapter=MyViewPagerAdapter(childFragmentManager)
        adapter.addFragment(Fragment1(),"One")
        adapter.addFragment(Fragment2(),"Two")
        adapter.addFragment(Fragment3(),"Three")
        adapter.addFragment(Fragment4(),"Four")
        adapter.addFragment(Fragment5(),"Five")
        viewPager.adapter=adapter
        tabs.setupWithViewPager(viewPager)
        return view
    }
    class MyViewPagerAdapter(manager: FragmentManager): FragmentPagerAdapter(manager){
        
        private val fragmentList:MutableList<Fragment> = ArrayList()
        private val titleList:MutableList<String> = ArrayList()
        override fun getItem(position: Int): Fragment {

            return fragmentList[position]
        }

        override fun getCount(): Int {

            return fragmentList.size
        }
        fun addFragment(fragment: Fragment,title:String){
            fragmentList.add(fragment)
            titleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return titleList[position]
        }

    }

}
