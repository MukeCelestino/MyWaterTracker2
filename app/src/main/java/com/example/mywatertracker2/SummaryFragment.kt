package com.example.mywatertracker2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
//import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.mywatertracker.R
import com.example.mywatertracker.WaterApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.math.RoundingMode

//private const val TAG = "SummaryFragment"

class SummaryFragment : Fragment() {
//    private lateinit var summaryView: LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_summary, container, false)
        fetchStatistics(view)
        return view
    }

    companion object {
//        fun newInstance(): SummaryFragment {
//            return SummaryFragment()
//        }
    }

    private fun fetchStatistics(view: View) {
        val avgTextView = view.findViewById<TextView>(R.id.avg)
        val maxTextView = view.findViewById<TextView>(R.id.max)
        val minTextView = view.findViewById<TextView>(R.id.min)
        val clearButton: Button = view.findViewById(R.id.clear)

        lifecycleScope.launch(Dispatchers.IO) {
            val db = (activity?.application as WaterApplication).db
            val avgDrank = db.accountDao().getAvg().toDouble()
            val minDrank = db.accountDao().getMin().toDouble()
            val maxDrank = db.accountDao().getMax().toDouble()

            // Update UI on the main (UI) thread
            launch(Dispatchers.Main) {
                avgTextView.text = "${avgDrank.toBigDecimal().setScale(1, RoundingMode.UP)} cups"
                maxTextView.text = "$maxDrank cup(s)"
                minTextView.text = "$minDrank cup(s)"
            }
        }

        clearButton.setOnClickListener {
            avgTextView.text = "0.0"
            maxTextView.text = "0.0"
            minTextView.text = "0.0"
        }
    }
}
