package com.example.mywatertracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecordsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_records)

        findViewById<Button>(R.id.addRecord).setOnClickListener {
            val timeOfDay = findViewById<EditText>(R.id.time).text.toString()
            val amount = findViewById<EditText>(R.id.amountOfWater).text.toString()
            val i = Intent(this@RecordsActivity, MainActivity::class.java)
            startActivity(i)

            lifecycleScope.launch(Dispatchers.IO) {
                (application as WaterApplication).db.accountDao().insert(
                    AccountEntity(timeOfDay, amount)
                )
//                (application as WaterApplication).db.accountDao().deleteAll(
//                )
            }
        }
    }
}