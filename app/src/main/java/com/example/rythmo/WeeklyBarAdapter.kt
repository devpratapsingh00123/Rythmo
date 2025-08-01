package com.example.rythmo

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView

class WeeklyBarAdapter(
    private val data: List<Pair<String, Float>>
) : RecyclerView.Adapter<WeeklyBarAdapter.BarViewHolder>() {

    inner class BarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dayText: TextView = itemView.findViewById(R.id.dayText)
        val barView: View = itemView.findViewById(R.id.barView)
        val hoursText: TextView = itemView.findViewById(R.id.hoursText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BarViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_bar_graph, parent, false)
        return BarViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: BarViewHolder, position: Int) {
        val (day, hours) = data[position]
        holder.dayText.text = day
        holder.hoursText.text = "${hours}h"

        // Max bar width scale (you can adjust 200f to control max width in px)
        val maxHours = data.maxOf { it.second }
        val scale = hours / maxHours
        val barParams = holder.barView.layoutParams as LinearLayout.LayoutParams
        barParams.width = (200 * scale).toInt()
        holder.barView.layoutParams = barParams
    }
}
