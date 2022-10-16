package com.example.mywatertracker

import android.accounts.Account
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mywatertracker.databinding.ActivityMainBinding

import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

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
        record.setOnClickListener{
            val intent = Intent(this@MainActivity, RecordsActivity::class.java)
                startActivity(intent)
        }

        accountsRecyclerView = findViewById(R.id.records)
        val articleAdapter = AccountAdapter(this, accounts)
        accountsRecyclerView.adapter = articleAdapter
        accountsRecyclerView.layoutManager = LinearLayoutManager(this).also {
            val dividerItemDecoration = DividerItemDecoration(this, it.orientation)
            accountsRecyclerView.addItemDecoration(dividerItemDecoration)
        }
        lifecycleScope.launch {
            (application as WaterApplication).db.accountDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    Accnt(
                        entity.timeOfDay,
                        entity.amountDrank
                    )
                }.also { mappedList ->
                    accounts.clear()
                    accounts.addAll(mappedList)
                    articleAdapter.notifyDataSetChanged()
                }
            }
        }

    }
}