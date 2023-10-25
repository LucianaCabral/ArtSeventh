package com.lcabral.artseventh

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.lcabral.artseventh.databinding.ActivitySplashScreenBinding

internal const val DURATION_ANIMATE = 1000L

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    private val splashScreen: ImageView by lazy { binding.starImage }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        window.statusBarColor = Color.BLACK
        supportActionBar?.hide()
        setupSplash()
    }
    private fun setupSplash() {
        splashScreen.alpha = 0f
        splashScreen.animate().setDuration(DURATION_ANIMATE).alpha(1f).withEndAction {
            val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}
