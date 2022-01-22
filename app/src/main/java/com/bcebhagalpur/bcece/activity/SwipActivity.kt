package com.bcebhagalpur.bcece.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentTransaction
import com.bcebhagalpur.bcece.R
import com.bcebhagalpur.bcece.fragment.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_swip.*

class SwipActivity : AppCompatActivity() {

    lateinit var bottomNavigation:BottomNavigationView
    lateinit var homeFragment: HomeFragment
    lateinit var settingFragment: SettingFragment
    lateinit var aboutFragment: AboutFragment

    lateinit var drawerLayout: DrawerLayout
    private lateinit var frameLayout: FrameLayout
    lateinit var navigationView: NavigationView
    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    lateinit var coordinatorLayout: CoordinatorLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_swip)

        bottomNavigation = findViewById(R.id.btnNav)
//        homeFragment = HomeFragment()
//        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, homeFragment)
//            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit()
        bottomNavigationView()

        drawerLayout = findViewById(R.id.drawerLayout)
        frameLayout = findViewById(R.id.frameLayout)
        navigationView = findViewById(R.id.navigationView)
        coordinatorLayout = findViewById(R.id.coordinatorLayout)
        toolbar = findViewById(R.id.toolbar)
        setUpToolbar()

        val actionBarDrawerToggle = ActionBarDrawerToggle(
            this@SwipActivity,
            drawerLayout,
            R.string.open_drawer,
            R.string.close_drawer
        )

        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.dashboard->{
                    supportFragmentManager.beginTransaction().replace(R.id.drawerLayout,DashboardFragment()).commit()
                    supportActionBar?.title = "Profile"
                    drawerLayout.closeDrawers()
                }
                }
            return@setNavigationItemSelectedListener true
            }
        }

        private fun bottomNavigationView(){
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.home->{
                    homeFragment= HomeFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.frameLayout,homeFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit()
                }
                R.id.setting->{
                    settingFragment= SettingFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.frameLayout,settingFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit()
                }
                R.id.about->{
                    aboutFragment= AboutFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.frameLayout,aboutFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit()
                }
            }
            true
        }

    }

    private fun setUpToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Toolbar Title"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

}
