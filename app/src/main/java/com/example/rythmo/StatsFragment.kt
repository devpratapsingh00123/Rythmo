package com.example.rythmo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class StatsFragment : Fragment() {

    // ✅ Simulated data for the weekly graph with proper type annotation
    private val dummyWeeklyData: List<Pair<String, Float>> = listOf(
        "Mon" to 2.5f,
        "Tue" to 3.0f,
        "Wed" to 1.8f,
        "Thu" to 4.2f,
        "Fri" to 3.5f,
        "Sat" to 2.0f,
        "Sun" to 4.8f
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_stats, container, false)

        // ✅ Set top mood
        val topMoodText = view.findViewById<TextView>(R.id.topMoodText)
        topMoodText.text = "Energetic" // Simulated top mood

        // ✅ Set total minutes
        val totalMinutesText = view.findViewById<TextView>(R.id.totalMinutesText)
        val totalHours = dummyWeeklyData.map { it.second }.sum()
        val totalMinutes = (totalHours * 60).toInt()
        totalMinutesText.text = "$totalMinutes mins"

        // ✅ Setup horizontal bar graph RecyclerView
        val recyclerView = view.findViewById<RecyclerView>(R.id.weeklyStatsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = WeeklyBarAdapter(dummyWeeklyData)

        return view
    }
}
