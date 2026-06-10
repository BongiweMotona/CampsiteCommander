package com.example.campsitecommander

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)

        val tvDetailName = findViewById<TextView>(R.id.tvDetailName)
        val tvDetailQuantity = findViewById<TextView>(R.id.tvDetailQuantity)
        val tvDetailCategory = findViewById<TextView>(R.id.tvDetailCategory)
        val btnBack = findViewById<Button>(R.id.btnBack)

        val name = intent.getStringExtra("ITEM_NAME") ?: "Unknown"
        val quantity = intent.getIntExtra("ITEM_QUANTITY", 0)
        val category = intent.getStringExtra("ITEM_CATEGORY") ?: "Unknown"

        tvDetailName.text = name
        tvDetailQuantity.text = "$quantity"
        tvDetailCategory.text = category

        btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}