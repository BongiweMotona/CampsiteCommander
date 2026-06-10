package com.example.campsitecommander

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.time.measureTime

class MainActivity : AppCompatActivity() {
//ST10520889 Bongiwe Motona
    private val gearList = mutableListOf<GearItem>()
    private lateinit var adapter: ArrayAdapter<String>
    private val displayList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etGearItem = findViewById<EditText>(R.id.etGearItem)
        val etQuantity = findViewById<EditText>(R.id.etQuantity)
        val etCategory = findViewById<EditText>(R.id.etCategory)
        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val lvGearList = findViewById<ListView>(R.id.lvGearList)
        val tvPackedCount = findViewById<TextView>(R.id.tvPackedCount)
        val btnDetails = findViewById<TextView>(R.id.btnDetail)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, displayList)
        lvGearList.adapter = adapter

        btnAdd.setOnClickListener {
            val name = etGearItem.text.toString().trim()
            val quantityText = etQuantity.text.toString().trim()
            val category = etCategory.text.toString().trim()

            if (name.isEmpty() || quantityText.isEmpty() || category.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else {
                val quantity = quantityText.toInt()
                val item = GearItem(name, quantity, category)
                gearList.add(item)
                displayList.add("$name (x$quantity) — $category")
                adapter.notifyDataSetChanged()
                tvPackedCount.text = "Items packed: ${gearList.size}"
                etGearItem.text.clear()
                etQuantity.text.clear()
                etCategory.text.clear()
            }
        }

        lvGearList.setOnItemClickListener { _, _, position, _ ->
            val selected = gearList[position]
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("ITEM_NAME", selected.name)
            intent.putExtra("ITEM_QUANTITY", selected.quantity)
            intent.putExtra("ITEM_CATEGORY", selected.category)
            startActivity(intent)
        }

        btnDetails.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}