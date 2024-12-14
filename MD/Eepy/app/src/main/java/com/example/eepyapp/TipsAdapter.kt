package com.example.eepyapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TipsAdapter(private val tipsList: List<String>) : RecyclerView.Adapter<TipsAdapter.TipsViewHolder>() {

    inner class TipsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTip: TextView = itemView.findViewById(R.id.tvTip)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TipsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tip, parent, false)
        return TipsViewHolder(view)
    }

    override fun onBindViewHolder(holder: TipsViewHolder, position: Int) {
        holder.tvTip.text = tipsList[position]
    }

    override fun getItemCount(): Int = tipsList.size
}
