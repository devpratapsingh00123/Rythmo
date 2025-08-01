package com.example.rythmo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class YourMusicFragment : Fragment() {

    private val moodViewModel: MoodViewModel by activityViewModels()

    private val songsByMood = mapOf(
        "Happy" to listOf(
            "Happy – Pharrell Williams",
            "Uptown Funk – Bruno Mars",
            "Can't Stop the Feeling – Justin Timberlake",
            "Best Day of My Life – American Authors",
            "Walking on Sunshine – Katrina & The Waves",
            "Shake It Off – Taylor Swift",
            "Good as Hell – Lizzo",
            "Sugar – Maroon 5",
            "I Got You (I Feel Good) – James Brown",
            "On Top of the World – Imagine Dragons"
        ),

        "Sad" to listOf(
            "Someone Like You – Adele",
            "Let Her Go – Passenger",
            "Fix You – Coldplay",
            "Happier – Ed Sheeran",
            "Say Something – A Great Big World ft. Christina Aguilera",
            "When I Was Your Man – Bruno Mars",
            "Jealous – Labrinth",
            "Skinny Love – Bon Iver",
            "The Night We Met – Lord Huron",
            "I Will Always Love You – Whitney Houston"
        ),

        "Angry" to listOf(
            "Numb – Linkin Park",
            "Stronger – Kanye West",
            "Break Stuff – Limp Bizkit",
            "Killing in the Name – Rage Against The Machine",
            "Duality – Slipknot",
            "B.Y.O.B. – System of a Down",
            "Given Up – Linkin Park",
            "Rollin’ – Limp Bizkit",
            "Bodies – Drowning Pool",
            "In the End – Linkin Park"
        ),

        "Romantic" to listOf(
            "Perfect – Ed Sheeran",
            "All of Me – John Legend",
            "Just the Way You Are – Bruno Mars",
            "Thinking Out Loud – Ed Sheeran",
            "You Are the Reason – Calum Scott",
            "Love Me Like You Do – Ellie Goulding",
            "A Thousand Years – Christina Perri",
            "Truly Madly Deeply – Savage Garden",
            "Make You Feel My Love – Adele",
            "I Won’t Give Up – Jason Mraz"
        ),

        "Relaxed" to listOf(
            "Weightless – Marconi Union",
            "Sunset Lover – Petit Biscuit",
            "Bloom – The Paper Kites",
            "Cold Little Heart – Michael Kiwanuka",
            "Night Owl – Galimatias",
            "Intro – The xx",
            "River Flows In You – Yiruma",
            "Ocean Eyes – Billie Eilish",
            "Breathe Me – Sia",
            "Slow Dancing in a Burning Room – John Mayer"
        ),

        "Energetic" to listOf(
            "Believer – Imagine Dragons",
            "Titanium – David Guetta ft. Sia",
            "Don't Stop Me Now – Queen",
            "Feel This Moment – Pitbull ft. Christina Aguilera",
            "Can't Hold Us – Macklemore & Ryan Lewis",
            "Uprising – Muse",
            "Bang Bang – Jessie J, Ariana Grande, Nicki Minaj",
            "Power – Kanye West",
            "Level Up – Ciara",
            "Stronger (What Doesn’t Kill You) – Kelly Clarkson"
        ))

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_your_music, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.songRecyclerView)
        val emptyText = view.findViewById<TextView>(R.id.emptyTextView)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        moodViewModel.selectedMood.observe(viewLifecycleOwner) { mood ->
            if (mood.isNullOrEmpty()) {
                recyclerView.visibility = View.GONE
                emptyText.visibility = View.VISIBLE
                emptyText.text = "🎧 Select a mood to see your music."
            } else {
                val songs = songsByMood[mood]?.shuffled()?.take(10) ?: listOf("No songs found for: $mood")
                recyclerView.adapter = SongAdapter(songs) { song ->
                    Toast.makeText(requireContext(), "🎵 Playing: $song", Toast.LENGTH_SHORT).show()
                }
                recyclerView.visibility = View.VISIBLE
                emptyText.visibility = View.GONE
            }
        }

        return view
    }
}
