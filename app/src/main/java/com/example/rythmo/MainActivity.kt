package com.example.rythmo

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var dropdownButton: ImageView

    // Shared ViewModel across fragments
    val moodViewModel: MoodViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.viewPager)
        tabLayout = findViewById(R.id.tabLayout)
        dropdownButton = findViewById(R.id.dropdownButton)

        // Set up ViewPager adapter
        val adapter = ViewPagerAdapter(this)
        viewPager.adapter = adapter

        // Attach TabLayout with ViewPager
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Feeling Today"
                1 -> "Your Music"
                2 -> "Stats"
                else -> ""
            }
        }.attach()

        // Dropdown click
        dropdownButton.setOnClickListener { showPopupMenu(it) }
    }

    private fun showPopupMenu(anchor: View) {
        val popup = PopupMenu(this, anchor)
        popup.menuInflater.inflate(R.menu.profile_dropdown_menu, popup.menu)

        // Optional: Show icons in dropdown (for some Android versions)
        try {
            val fields = popup.javaClass.declaredFields
            for (field in fields) {
                if ("mPopup" == field.name) {
                    field.isAccessible = true
                    val menuPopupHelper = field.get(popup)
                    val classPopupHelper = Class.forName(menuPopupHelper.javaClass.name)
                    val setForceIcons = classPopupHelper.getMethod("setForceShowIcon", Boolean::class.java)
                    setForceIcons.invoke(menuPopupHelper, true)
                    break
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // Handle menu item clicks
        popup.setOnMenuItemClickListener { item: MenuItem ->
            when (item.itemId) {
                R.id.menu_profile -> {
                    viewPager.currentItem = 0 // Go to Feeling Today
                    true
                }
                R.id.menu_my_music -> {
                    viewPager.currentItem = 1 // Go to Your Music
                    true
                }
                R.id.menu_settings -> {
                    viewPager.currentItem = 2 // Go to Stats
                    true
                }
                R.id.menu_logout -> {
                    FirebaseAuth.getInstance().signOut()

                    val intent = Intent(this, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish()

                    true}
                else -> false
            }
        }

        popup.show()
    }
}
