package com.example.life_is_well
import Data.UnitSystem
import android.content.SharedPreferences
import android.icu.util.MeasureUnit
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import java.util.*
import kotlin.Any

class SettingsPageMain(function: () -> UnitSystem) : AppCompatActivity(),
    SharedPreferences.OnSharedPreferenceChangeListener {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)


        val actionBar = supportActionBar
        actionBar!!.title = ""

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment())
                .commit()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        PreferenceManager.getDefaultSharedPreferences(this)
            .registerOnSharedPreferenceChangeListener(this)


    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)

        }
    }


    internal interface ColorDialogCallback {
        fun onChosen(chosenColor: String?)
    }


    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if (key == "key_theme") {


            val prefs = sharedPreferences?.getString(key, "1")

            when (prefs?.toInt()) {
                1 -> {

                    AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM

                    )
                }
                2 -> {

                    AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_NO
                    )
                }

                3 -> {
                    AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_YES
                    )
                }


            }
        }




        fun onDestroy() {
            super.onDestroy()
            PreferenceManager.getDefaultSharedPreferences(this)
                .unregisterOnSharedPreferenceChangeListener(this)
        }
    }


  /*  fun MeasureUnit(sharedPreferences: SharedPreferences?, key: String?) {
        if (key == "key_units") {


            val prefs = sharedPreferences?.getString(key, "1")

            when (prefs?.toInt()) {
                1 -> {
                    MeasureUnit.CENTIMETER.apply {  }

                    )
                }
                2 -> {

                    AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_NO
                    )
                }


            }
        }

    }*/
}




/* private fun chooseThemeDialog() {

     val builder = AlertDialog.Builder(this)
     builder.setTitle(getString(R.string.settings))
     val styles = arrayOf("Light","Dark","System default")
     val checkedItem = 0

     builder.setSingleChoiceItems(styles, checkedItem) { dialog, which ->


     }

     val dialog = builder.create()
     dialog.show()
 }



*/


/*
   private fun Set_Themes(){


       val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(baseContext)
       if( sharedPreferences?.getString("Theme.Life_is_well_Dark_Theme,"""))

}*/

