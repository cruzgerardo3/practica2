package com.example.practica2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Ajustar los insets del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0) // Margen inferior = 0
            insets
        }

        bottomNavigationView = findViewById(R.id.bottom_navigation_tab_menu)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.nav_fragment_container, HomeFragment()).commit()
        }
        setNavigationMenuTab()
    }

    private fun setNavigationMenuTab() {
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> loadFragment(HomeFragment())
                R.id.navigation_account -> loadFragment(AccountFragment())
                R.id.navigation_transaction -> loadFragment(TransactionFragment())
                R.id.navigation_cash_flow -> loadFragment(CashFlowFragment())
                R.id.navigation_profile -> loadFragment(ProfileFragment())
                else -> false
            }
            true
        }
    }

    private fun loadFragment(fragment: Fragment): Boolean {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
            .replace(R.id.nav_fragment_container, fragment)
            .commit()
        return true
    }
}