package com.example.standard4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.lang.RuntimeException

class CardAdapter(val mItems: MutableList<CardInfo>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface ItemClick {
        fun onClick(view: View, position: Int)
    }

    var itemClick: ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View?
        return when(viewType) {
            0 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.card_item1, parent, false)
                Holder(view)
            }
            1 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.card_item2, parent, false)
                Holder(view)
            }
            2 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.card_item3, parent, false)
                Holder(view)
            }

            else -> throw RuntimeException("알 수 없는 뷰 타입")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            itemClick?.onClick(it, position)
        }
        (holder as Holder).cardPrice.text = "\$${mItems[position].price}"
        holder.cardNumber.text = mItems[position].number
        holder.cardName.text = mItems[position].name
        holder.cardDate.text = mItems[position].date

    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    override fun getItemViewType(position: Int): Int {
        return position%3
    }

    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val cardName = itemView.findViewById<TextView>(R.id.tvName)
        val cardNumber = itemView.findViewById<TextView>(R.id.tvNumber)
        val cardPrice = itemView.findViewById<TextView>(R.id.tvPrice)
        val cardDate = itemView.findViewById<TextView>(R.id.tvDate)
    }
}