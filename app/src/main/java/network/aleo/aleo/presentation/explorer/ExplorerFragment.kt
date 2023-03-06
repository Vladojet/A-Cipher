package network.aleo.aleo.presentation.explorer

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.CookieManager
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import network.aleo.aleo.databinding.FragmentExplorerBinding
import network.aleo.aleo.presentation.explorer.ionbackpressed.IOnBackPressed
import network.aleo.aleo.presentation.utils.isOnline

class ExplorerFragment : Fragment(), IOnBackPressed {

    private lateinit var binding: FragmentExplorerBinding


    companion object {
        @JvmStatic
        fun newInstance() = ExplorerFragment()
        private const val link: String = "https://www.aleo.network/"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentExplorerBinding.inflate(layoutInflater).apply { binding = this }.root
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
        if (requireContext().isOnline()) binding.webView.loadUrl(link)
        else showInternetLackMessage()
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

    override fun onBackPressed() {
         if (binding.webView.canGoBack()) {
            binding.webView.goBack()
        } else return
    }
}
