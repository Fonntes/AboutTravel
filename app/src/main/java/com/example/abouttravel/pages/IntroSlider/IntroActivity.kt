package com.example.abouttravel.pages.IntroSlider

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.abouttravel.MainActivity
import com.example.abouttravel.R

class IntroActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager
    private lateinit var adapter: IntroPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        viewPager = findViewById(R.id.viewPager)
        adapter = IntroPagerAdapter(this)
        viewPager.adapter = adapter
    }

    fun goToNextSlide() {
        if (viewPager.currentItem + 1 < adapter.count) {
            viewPager.currentItem = viewPager.currentItem + 1
        } else {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}
