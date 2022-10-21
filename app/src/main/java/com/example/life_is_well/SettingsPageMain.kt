package com.example.life_is_well
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager

class SettingsPageMain() : AppCompatActivity(),
    SharedPreferences.OnSharedPreferenceChangeListener {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        setTheme(R.style.Theme_Life_is_well)

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
                      setTheme(R.style.Theme_Life_is_well)

                }

                2 -> {
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



    }



/*

    // Notification

if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
    // Create the NotificationChannel
    val name = getString(R.string.channel_name)
    val descriptionText = getString(R.string.channel_description)
    val importance = NotificationManager.IMPORTANCE_DEFAULT
    val mChannel = NotificationChannel(CHANNEL_ID, name, importance)
    mChannel.description = descriptionText
    // Register the channel with the system; you can't change the importance
    // or other notification behaviors after this
    val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
    notificationManager.createNotificationChannel(mChannel)
}


val intent = Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS).apply {
    putExtra(Settings.EXTRA_APP_PACKAGE, packageName)
    putExtra(Settings.EXTRA_CHANNEL_ID, myNotificationChannel.getId())
}
startActivity(intent)


// The id of the channel.
val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
val id: String = "my_channel_01"
notificationManager.deleteNotificationChannel(id)





*/





  /*
         // Measurement Settings

public class UnitLocale {
    public static  Metric_Implementation
    public static  Imperial_Implementation

    public static UnitLocale getDefault() {
            return getFrom(Locale.getDefault())
    }
    public static UnitLocale getFrom(Locale locale) {
        String countryCode = locale.getCountry()
        if ("US".equals(countryCode))
        return Imperial // USA

        return Metric
    }
}




   fun MeasureUnit(sharedPreferences: SharedPreferences?, key: String?) {
        if (key == "key_units") {

// Combining and deducting a list of Measures
val combined = listOf(10(gram), 10(Kilo.gram)).combined()
val deducted = listOf(10(Mega.byte), 10(Kilo.byte)).deducted()





            val prefs = sharedPreferences?.getString(key, "1")

            when (prefs?.toInt()) {
                1 -> {
                    MeasureUnit.CENTIMETER.apply {  }

                    )
                }
                2 -> {

                      MeasureUnit.CENTIMETER.apply {  }

                    )
                }


            }
        }

    }*/





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

