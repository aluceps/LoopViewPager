package me.aluceps.loopviewpager

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_page.*

class PageFragment : Fragment() {

    private val dataText by lazy {
        arguments?.getString(DATA_TEXT)
    }

    companion object {

        const val DATA_TEXT = "DataText"

        fun newInstance(text: String): PageFragment {
            val fragment = PageFragment()
            val args = Bundle()
            args.putString(DATA_TEXT, text)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(context).inflate(getLayout(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textView.text = dataText
        when (dataText) {
            "a" -> view.setBackgroundColor(Color.YELLOW)
            "b" -> view.setBackgroundColor(Color.RED)
            "c" -> view.setBackgroundColor(Color.MAGENTA)
            "d" -> view.setBackgroundColor(Color.BLUE)
            "e" -> view.setBackgroundColor(Color.CYAN)
            else -> view.setBackgroundColor(Color.GREEN)
        }
    }

    private fun getLayout(): Int = R.layout.fragment_page
}