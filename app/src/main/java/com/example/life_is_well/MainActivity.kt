package com.example.life_is_well


import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import fragments.GoalsFragment
import fragments.HomeFragment
import fragments.ProfileFragment
//import fragments.SettingsFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.newFixedThreadPoolContext

class MainActivity : AppCompatActivity() {
    private val homeFragment = HomeFragment()
    private val profileFragment = ProfileFragment()
    private val goalsFragment = GoalsFragment()
    //private val settingsFragment = SettingsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(homeFragment)


        home_bottom_nav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.profileBottomNav -> replaceFragment(profileFragment)
                //R.id.settingsBottomNav -> replaceFragment(settingsFragment)
                R.id.goalsBottomNav -> replaceFragment(goalsFragment)
                R.id.homeBotNav -> replaceFragment(homeFragment)


                //John's fix, The R.id separator needed to point towards the SettingPageMain
                R.id.settingsBottomNav -> {
                    val intent = Intent(this, SettingsPageMain::class.java)
                    startActivity(intent)
                }
            }
            true
        }
    }

    /*   //John's Fix, this is for the back botton once your in the settings page
       override fun onOptionsItemSelected(item: MenuItem): Boolean {
           when (item.itemId) {
               android.R.id.home-> {
                   super.onBackPressed()
                   return true
               }
           }
           return super.onOptionsItemSelected(item)
       }*/

    private fun replaceFragment(fragment: Fragment) {
        if (fragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.toDo_container, fragment)
            transaction.commit()
        }
    }


    /* HOME PAGE FUNCTIONS */

    fun financeBtn(view: View) {
        val intent = Intent(this, FinancePageMain::class.java)
        startActivity(intent)
    }

    fun healthBtn(view: View) {
        val intent = Intent(this, HealthPageMain::class.java)
        startActivity(intent)
    }

    fun mentalBtn(view: View) {
        val intent = Intent(this, MentalPageMain::class.java)
        startActivity(intent)
    }


    fun addBtn(view: View) {}

    /* PROFILE PAGE FUNCTIONS */


    /* GOALS PAGE FUNCTIONS */


    /* SETTINGS PAGE FUNCTIONS */


}
