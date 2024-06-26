package network.aleo.aleo.presentation.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import network.aleo.aleo.R

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {

    private val runnable: Runnable = Runnable {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        injectTransparentStatusBar()
        injectSplash()
    }

    private fun injectSplash() {
        supportActionBar?.hide()
        Handler(Looper.getMainLooper()).postDelayed(runnable, 2000)
    }

    private fun injectTransparentStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val window: Window = window
            window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
    }

    override fun onStop() {
        super.onStop()
        Handler(Looper.getMainLooper()).removeCallbacks(runnable)
    }
}