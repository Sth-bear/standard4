package com.example.standard4

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.standard4.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val selectedCard = intent.getParcelableExtra("selectedData", CardInfo::class.java)
        selectedCard?.let { putEachData(it) }
    }

    private fun putEachData(card: CardInfo) {
        binding.tvDetailDate.text = "유효기간: ${card.date}"
        binding.tvDetailName.text = "이름: ${card.name}"
        binding.tvDetailPrice.text = "가격: ${card.price}"
        binding.tvDetailNumber.text = "카드 번호: ${card.number}"
    }
}