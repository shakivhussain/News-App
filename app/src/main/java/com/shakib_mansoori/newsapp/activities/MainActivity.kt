package com.shakib_mansoori.newsapp.activities

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener
import com.shakib_mansoori.newsapp.R
import com.shakib_mansoori.newsapp.adapter.PagerAdapter
import com.shakib_mansoori.newsapp.databinding.ActivityMainBinding
import com.shakib_mansoori.newsapp.utils.CheckInternet

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ViewModel
    private lateinit var dialog: Dialog
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager
    private lateinit var checkInternet: CheckInternet

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkInternet = CheckInternet()
        tabLayout = binding.tablayout
        viewPager = binding.viewPager

        tabLayout.addTab(tabLayout.newTab().setText("Health"))
        tabLayout.addTab(tabLayout.newTab().setText("Politics"))
        tabLayout.addTab(tabLayout.newTab().setText("Science"))
        tabLayout.addTab(tabLayout.newTab().setText("Business"))
        tabLayout.addTab(tabLayout.newTab().setText("Technology"))


        viewPager.adapter = PagerAdapter(supportFragmentManager, tabLayout.tabCount)

        viewPager.addOnPageChangeListener(TabLayoutOnPageChangeListener(tabLayout))

        tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.setCurrentItem(tab.position)
                if (tab.position == 0 || tab.position == 1 || tab.position == 2 ||
                    tab.position == 3 || tab.position == 4
                ) {

                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        dialog = Dialog(this)
        dialog.setContentView(R.layout.layout_progress)
        dialog.setCancelable(false)
        dialog.show()

        init()
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