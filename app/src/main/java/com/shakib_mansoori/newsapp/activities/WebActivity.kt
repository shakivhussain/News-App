package com.shakib_mansoori.newsapp.activities

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.shakib_mansoori.newsapp.R
import com.shakib_mansoori.newsapp.databinding.ActivityWebBinding
import com.shakib_mansoori.newsapp.databinding.LayoutProgressBinding
import com.shakib_mansoori.newsapp.utils.CheckInternet


class WebActivity : AppCompatActivity() {

    private lateinit var url: String
    private lateinit var bindingWebActivity: ActivityWebBinding
    private lateinit var bindLayoutProgressBinding: LayoutProgressBinding
    private lateinit var dialog: Dialog
    private lateinit var checkInternet: CheckInternet

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingWebActivity = ActivityWebBinding.inflate(layoutInflater)
        setContentView(bindingWebActivity.root)

        checkInternet = CheckInternet()
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
            val bundle = intent.extras
            if (bundle != null) {
                url = bundle.getString("url").toString()

            }

            bindingWebActivity.webView.webChromeClient = object : WebChromeClient() {
                override fun onProgressChanged(view: WebView, progress: Int) {
                    title = "Loading..."
                    setProgress(progress * 100)


                    if (progress == 100)
                        dialog.hide()
                }
            }

            bindingWebActivity.webView.loadUrl(url)
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