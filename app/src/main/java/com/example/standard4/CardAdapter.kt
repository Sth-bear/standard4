package com.example.standard4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.standard4.data.CardInfo
import com.example.standard4.databinding.CardItem1Binding
import com.example.standard4.databinding.CardItem2Binding
import com.example.standard4.databinding.CardItem3Binding


class CardAdapter(private val cardList: List<CardInfo>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface ItemClick {
        fun onClick(view: View, position: Int)
    }

//    var cardList = listOf<CardInfo>() //선언단계에서 안받으면 이후 추가해야함. 재사용이 가능해보이나 뭐가 더 좋은지?
    // main -> val adapter = CardAdapter
    // 이후 adapter.cardList(a) || adapter.cardList(b) 이런식의 재활용이 가능함.
    // 위처럼 선언시에 요구한다면 val adapter1 = card(a) || val adapter2 = card(b) 와 같은 선언이 필요함

    var itemClick: ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when(viewType) {
            0 -> {
                val binding = CardItem1Binding.inflate(LayoutInflater.from(parent.context), parent, false)
                Holder(binding) //viewType에 맞는 binding을 실행. 이전의 holder와 달리 view 대신 binding을.
            }
            1 -> {
                val binding = CardItem2Binding.inflate(LayoutInflater.from(parent.context), parent, false)
                Holder1(binding)
            }
            2 -> {
                val binding = CardItem3Binding.inflate(LayoutInflater.from(parent.context), parent, false)
                Holder2(binding)
            }

            else -> throw RuntimeException("알 수 없는 뷰 타입")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            itemClick?.onClick(it, position)
        }
        val currentItem = cardList[position]
        when(holder.itemViewType) {
            0 -> {
                val holder0 = holder as Holder
                holder0.bind(currentItem)
            }
            1 -> {
                val holder1 = holder as Holder1
                holder1.bind(currentItem)
            }
            2 -> {
                val holder2 = holder as Holder2
                holder2.bind(currentItem)
            }
            else -> throw RuntimeException("알 수 없는 홀더")

        }

    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    override fun getItemViewType(position: Int): Int {
        return position%3
    }

    //기존의 다 같은 형식이어서 하나로 통일함에서 각각의 holder를 생성 이에 binding이 가능해짐.
    inner class Holder(private val binding: CardItem1Binding): RecyclerView.ViewHolder(binding.root) {
        fun bind(card: CardInfo) {
            binding.apply {
                binding.tvName.text = card.name
                binding.tvNumber.text = card.number
                binding.tvPrice.text = card.price
                binding.tvDate.text = card.date
            }
        }
    }
    inner class Holder1(private val binding: CardItem2Binding): RecyclerView.ViewHolder(binding.root) {
        fun bind(card: CardInfo) {
            binding.apply {
                binding.tvName.text = card.name
                binding.tvNumber.text = card.number
                binding.tvPrice.text = card.price
                binding.tvDate.text = card.date
            }
        }
    }
    inner class Holder2(private val binding: CardItem3Binding): RecyclerView.ViewHolder(binding.root) {
        fun bind(card: CardInfo) {
            binding.apply {
                binding.tvName.text = card.name
                binding.tvNumber.text = card.number
                binding.tvPrice.text = card.price
                binding.tvDate.text = card.date
            }
        }
    }
}