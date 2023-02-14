package com.example.aleo.presentation.explorer

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.CookieManager
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.aleo.databinding.FragmentExplorerBinding
import com.example.aleo.presentation.explorer.ionbackpressed.IOnBackPressed

class ExplorerFragment : Fragment(), IOnBackPressed {

    private lateinit var binding: FragmentExplorerBinding
    private var connection: Boolean = false

    companion object {
        @JvmStatic
        fun newInstance() = ExplorerFragment()
        private const val link: String = "https://www.aleo.network/"
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        connection = isInternetAvailable(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExplorerBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.webView.setBackgroundColor(Color.parseColor("#12141d"))

        if (savedInstanceState != null) {
            binding.webView.restoreState(savedInstanceState)
        } else {
            initWebView()

            if (link != "") {
                showPage(link)
            } else {
                showPage(link)
            }
        }
        binding.tryAgainButton.setOnClickListener { showPage(link) }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.webView.saveState(outState)
    }

    private fun showPage(link: String) {
        hideInternetLackMessage()
        if (connection) {
            binding.webView.loadUrl(link)
        } else {
            showInternetLackMessage()
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView() {
        binding.webView.webViewClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                return super.shouldOverrideUrlLoading(view, url)
            }
        }
        val webSettings = binding.webView.settings
        webSettings.loadWithOverviewMode = true
        webSettings.useWideViewPort = true
        webSettings.javaScriptEnabled = true
        webSettings.domStorageEnabled = true
        webSettings.javaScriptCanOpenWindowsAutomatically = true
        webSettings.databaseEnabled = true
        webSettings.setSupportZoom(false)
        webSettings.allowFileAccess = true
        webSettings.allowContentAccess = true
        val cookieManager = CookieManager.getInstance()
        cookieManager.setAcceptCookie(true)

    }

    private fun showInternetLackMessage() {
        binding.webView.visibility = View.GONE
        binding.internetLackLayout.visibility = View.VISIBLE
    }

    private fun hideInternetLackMessage() {
        binding.webView.visibility = View.VISIBLE
        binding.internetLackLayout.visibility = View.GONE
    }

    private fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        } else {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
            }
        }
        return false
    }

    override fun onBackPressed() {
         if (binding.webView.canGoBack()) {
            binding.webView.goBack()
        } else return

    }
}
