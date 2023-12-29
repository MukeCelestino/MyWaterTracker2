package com.example.mywatertracker

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mywatertracker.databinding.ActivityMainBinding
import com.example.mywatertracker2.SummaryFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

private const val TAG = "MainActivity/"

class MainActivity : AppCompatActivity() {
    private val accounts = mutableListOf<Accnt>()
    private lateinit var accountsRecyclerView: RecyclerView
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val record = findViewById<Button>(R.id.record)
        record.setOnClickListener {
            val intent = Intent(this@MainActivity, RecordsActivity::class.java)
            startActivity(intent)
        }

        val fragmentManager: FragmentManager = supportFragmentManager

        val accountFragment: Fragment = AccountFragment()
        val summaryFragment: Fragment = SummaryFragment()

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavigationView.setOnItemSelectedListener { item ->
            lateinit var fragment: Fragment
            when (item.itemId) {
                R.id.logs -> fragment = accountFragment
                R.id.dashboard -> fragment = summaryFragment
            }
            replaceFragment(fragment)
            true
        }

        bottomNavigationView.selectedItemId = R.id.logs
    }

    private fun replaceFragment(summaryFragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.summary_frame_layout, summaryFragment)
        fragmentTransaction.commit()

    }
}