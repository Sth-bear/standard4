package com.example.standard4

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.standard4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val adapter = CardAdapter(CardList.cardList)
        binding.cardView.adapter = adapter
        binding.cardView.layoutManager = LinearLayoutManager(this)
        binding.cardView.addItemDecoration(ItemSpace(this,15))
        val bundle = Bundle()
        adapter.itemClick = object : CardAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                val selectedCard = CardList.cardList[position]
                bundle.putParcelable("selectedData", selectedCard)
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra("selectedData", bundle)
                startActivity(intent)
            }
        }
    }
}