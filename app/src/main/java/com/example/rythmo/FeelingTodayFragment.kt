package com.example.rythmo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2

class FeelingTodayFragment : Fragment() {

    // Shared ViewModel for communicating mood
    private val moodViewModel: MoodViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_feeling_today, container, false)

        val moodButtons = mapOf(
            R.id.emoji_happy to "Happy",
            R.id.emoji_sad to "Sad",
            R.id.emoji_angry to "Angry",
            R.id.emoji_romantic to "Romantic",
            R.id.emoji_relaxed to "Relaxed",
            R.id.emoji_energy to "Energetic"
        )

        moodButtons.forEach { (id, mood) ->
            view.findViewById<ImageButton>(id)?.setOnClickListener {
                handleMoodClick(mood)
            }
        }

        return view
    }

    private fun handleMoodClick(mood: String) {
        Toast.makeText(requireContext(), "Mood selected: $mood", Toast.LENGTH_SHORT).show()

        // Set the mood in ViewModel
        moodViewModel.setMood(mood)

        // Switch ViewPager tab to "Your Music"
        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)
        viewPager?.currentItem = 1
    }
}
