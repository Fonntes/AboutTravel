package com.example.abouttravel.pages.IntroSlider

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.viewpager.widget.PagerAdapter
import com.example.abouttravel.R

class IntroPagerAdapter(private val context: Context) : PagerAdapter() {

    private val layouts = arrayOf(
        R.layout.intro_1,
        R.layout.intro_2,
        R.layout.intro_3
    )

    override fun getCount(): Int {
        return layouts.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(layouts[position], container, false)

        val buttonId = when (position) {
            0 -> R.id.intro1
            1 -> R.id.intro2
            2 -> R.id.intro3
            else -> R.id.intro1
        }

        val button: Button? = view.findViewById(buttonId)
        button?.setOnClickListener {
            if (context is IntroActivity) {
                context.goToNextSlide()
            }
        }

        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}
