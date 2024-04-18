package com.example.standard4

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.standard4.data.CardInfo
import com.example.standard4.data.DataSource
import com.example.standard4.data.cardList
import com.example.standard4.databinding.ActivityMainBinding
import com.example.standard4.extension.launchActivity

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val cardViewModel by viewModels<CardViewModel> {
        CardViewModelFactory()
    }
    private val cardAdapter by lazy { CardAdapter(cardViewModel.cardLiveData) } //viewmodel로 연결
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//        val cardList = DataSource.getDataSource().getCardList() // DataSource.getDataSource()도 선언하면 되는것 아닌지?
//        cardAdapter.cardList = cardList                         // Companion Object로 선언했기에 위의 이름을 통해서 접근해야함.



        binding.cardView.adapter = cardAdapter
        binding.cardView.layoutManager = LinearLayoutManager(this)
        binding.cardView.addItemDecoration(ItemSpace(this,15))

        cardAdapter.itemClick = object : CardAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                launchActivity<DetailActivity>(
                    DetailActivity.EXTRA_CARD to cardList()[position] // cardlist의 position값의 id만 가지고 lunch
                )
//                val bundle = Bundle().apply {
//                    val selectedCard = DataSource.getDataSource().getCardForID(position.toLong()+1) // position은 0부터, id를 1부터부여
//                    putParcelable(DetailActivity.EXTRA_CARD, selectedCard)
//                }
//                val intent = Intent(this@MainActivity, DetailActivity::class.java)
//                intent.putExtras(bundle) //bundle은 extras
//                startActivity(intent)
            }
        }
    }
}