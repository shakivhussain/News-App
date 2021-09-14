package com.shakib_mansoori.newsapp.activities

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.shakib_mansoori.newsapp.R
import com.shakib_mansoori.newsapp.adapter.PagerAdapter
import com.shakib_mansoori.newsapp.databinding.ActivityMainBinding
import com.shakib_mansoori.newsapp.utils.CheckInternet

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ViewModel
    private lateinit var dialog: Dialog
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var checkInternet: CheckInternet
    private lateinit var pagerAdapter: PagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkInternet = CheckInternet()
        tabLayout = binding.tablayout
        viewPager = binding.viewPager

        val fm = supportFragmentManager

        pagerAdapter = PagerAdapter(fm, lifecycle)
        viewPager.adapter = pagerAdapter

        tabLayout.addTab(tabLayout.newTab().setText("Health"))
        tabLayout.addTab(tabLayout.newTab().setText("Politics"))
        tabLayout.addTab(tabLayout.newTab().setText("Science"))
        tabLayout.addTab(tabLayout.newTab().setText("Business"))
        tabLayout.addTab(tabLayout.newTab().setText("Technology"))

        tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })


        viewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }

        })


        apply {

            binding.smoothBottomBar.onItemSelected = {

                when (it) {
                    0 -> Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
                    1 -> Toast.makeText(this, "Favorite", Toast.LENGTH_SHORT).show()
                    2 -> Toast.makeText(this, "Bookmark", Toast.LENGTH_SHORT).show()
                    3 -> Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show()
                }

            }


            dialogSet()

            init()
        }

        apply {

            binding.searchEditText.setOnClickListener(View.OnClickListener {

                Toast.makeText(this, "Search Feature Under Development...", Toast.LENGTH_SHORT)
                    .show()

            })

            binding.notification.setOnClickListener(View.OnClickListener {

                Toast.makeText(
                    this,
                    "Notification Feature, Under Development...",
                    Toast.LENGTH_SHORT
                ).show()

            })

        }


    }

    private fun dialogSet() {

        dialog = Dialog(this)
        dialog.setContentView(R.layout.layout_progress)
        dialog.setCancelable(false)
        dialog.show()

    }

    private fun init() {
        if (!checkInternet.isConected(this)) {
            showCustomDialog()
        } else {
            viewModel = ViewModel()
            viewModel.fetchNewsDataFromApi()
            viewModel.getHealthNews().observe(this) {
                if (it != null) {
                    dialog.hide()
                }
            }
        }

    }

    private fun showCustomDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Please connect to the internet to proceed further")
            .setCancelable(false)
            .setPositiveButton(
                "Connect"
            ) { dialog, which -> startActivity(Intent(Settings.ACTION_WIFI_SETTINGS)) }
            .setNegativeButton("Cancel") { dialog, which ->

                dialog.dismiss()
                init()
            }
        val alertDialog = builder.create()
        alertDialog.show()
    }

    override fun onRestart() {
        super.onRestart()
        init()
    }
}