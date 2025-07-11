package com.example.cryptoapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoapp.R
import com.example.cryptoapp.model.Crypto

    class CryptoAdapter(private val cryptoList: List<Crypto>): RecyclerView.Adapter<CryptoAdapter.CryptoViewHolder>() {
        inner class CryptoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
            val tvSymbol: TextView = itemView.findViewById(R.id.tvSymbol)
            val tvPrice:TextView = itemView.findViewById(R.id.tvPrice)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.coin_single, parent, false)
            return CryptoViewHolder(view)
        }

        override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
            val item= cryptoList[position]
            holder.tvSymbol.text = item.symbol
            val price = item.price.toDoubleOrNull() ?: 0.0
            holder.tvPrice.text = String.format("%.2f", price)


        }

        override fun getItemCount(): Int = cryptoList.size


    }