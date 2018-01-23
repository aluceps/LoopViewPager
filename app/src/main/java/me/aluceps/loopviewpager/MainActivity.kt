package me.aluceps.loopviewpager

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val adapter by lazy {
        ViewPagerAdapter(supportFragmentManager)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeView()
        setData()
        initializePosition()
    }

    private fun initializeView() {
        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            private var jumpPosition = -1

            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> jumpPosition = adapter.getRealCount()
                    adapter.getRealCount() + 1 -> jumpPosition = 1
                    else -> {
                    }
                }
            }

            override fun onPageScrollStateChanged(state: Int) {
                if (state == ViewPager.SCROLL_STATE_IDLE && jumpPosition >= 0) {
                    viewPager.setCurrentItem(jumpPosition, false)
                    jumpPosition = -1
                }
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }
        })
    }

    private fun initializePosition() {
        viewPager.setCurrentItem(1, false)
    }

    private fun setData() {
        adapter.add("a")
        adapter.add("b")
        adapter.add("c")
        adapter.add("d")
        adapter.add("e")
    }

    private class ViewPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {

        private val items: MutableList<String> = mutableListOf()

        override fun getItem(position: Int): Fragment =
                PageFragment.newInstance(items[mapPosition(position)])

        override fun getCount(): Int = when (items.size) {
            0 -> 0
            else -> items.size + 2
        }

        fun getRealCount(): Int = items.size

        private fun mapPosition(position: Int): Int = when (position) {
            0 -> getRealCount() - 1
            getRealCount() + 1 -> 0
            else -> position - 1
        }

        fun add(text: String) {
            items.add(text)
            notifyDataSetChanged()
        }
    }
}
