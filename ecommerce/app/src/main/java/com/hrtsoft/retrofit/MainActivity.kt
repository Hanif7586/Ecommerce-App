package com.hrtsoft.retrofit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView



class MainActivity : AppCompatActivity() {

    private lateinit var fragment1: Fragment
    private lateinit var fragment2: Fragment
    private lateinit var fragment3: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize fragments
        fragment1 = HomeFragment()

        fragment2 = Progile_Fragment() // Replace with your ProfileFragment instance
        // Check for Intent extras
        val email = intent.getStringExtra("email")
        val password = intent.getStringExtra("password")

        // Pass data to fragment1
        val bundle = Bundle()
        bundle.putString("email", email)
        bundle.putString("password", password)
        fragment2.arguments = bundle

        fragment3 = Progile_Fragment() // Replace with your MoreFragment instance

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.Home -> {
                    // Load Fragment1 when the Home button is clicked
                    loadFragment(fragment1)
                    true
                }
                R.id.Category -> {
                    // Load Fragment2 when Profile button is clicked
                    loadFragment(fragment1)
                    true
                }
                R.id.Cart -> {
                    // Load Fragment2 when Profile button is clicked
                    loadFragment(fragment1)
                    true
                }
                R.id.Profile -> {
                    // Load Fragment3 when More button is clicked
                    loadFragment(fragment2)
                    true
                }
                else -> false
            }
        }

        // Set the default selected fragment
        loadFragment(fragment1) // Replace with your default fragment
    }

    // Function to load fragments
    private fun loadFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.flFragment, fragment) // Replace fragmentContainer with your fragment container ID
        transaction.addToBackStack(null) // Optional: Add transaction to the back stack
        transaction.commit()
    }
}
