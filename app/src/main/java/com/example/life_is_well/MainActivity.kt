package com.example.life_is_well

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import fragments.GoalsFragment
import fragments.HomeFragment
import fragments.ProfileFragment
import fragments.SettingsFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val homeFragment = HomeFragment()
    private val profileFragment = ProfileFragment()
    private val goalsFragment = GoalsFragment()
    private val settingsFragment = SettingsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(homeFragment)

        home_bottom_nav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.profileBottomNav -> replaceFragment(profileFragment)
                R.id.settingsBottomNav -> replaceFragment(settingsFragment)
                R.id.goalsBottomNav -> replaceFragment(goalsFragment)
                R.id.homeBotNav -> replaceFragment(homeFragment)
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment){
        if (fragment != null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
        }
    }

    /* HOME PAGE FUNCTIONS */

    fun financeBtn(view: View) {
        val intent = Intent(this,FinancePageMain::class.java )
        startActivity(intent)
    }

    fun healthBtn(view: View) {
        val intent = Intent(this,HealthPageMain::class.java )
        startActivity(intent)
    }

    fun mentalBtn(view: View) {
        val intent = Intent(this,MentalPageMain::class.java )
        startActivity(intent)
    }

    fun addBtn(view: View) {}

    /* PROFILE PAGE FUNCTIONS */


    /* GOALS PAGE FUNCTIONS */


    /* SETTINGS PAGE FUNCTIONS */



}