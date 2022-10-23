package Data


import android.content.Context
import android.content.SharedPreferences
import android.icu.util.Measure
import androidx.benchmark.macro.Metric
import androidx.core.util.rangeTo
import androidx.preference.PreferenceManager

enum class UnitSystem{
    METRIC,IMPERIAL, IMPERIAL_US

}

interface UnitProvider  {

fun getUnitsSystem() : UnitSystem

}
const val Units = "key_units"

class UnitClass(context: Context) : Data.UnitProvider {

    private val ChangeUnit = context.applicationContext

    private val preferences: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(ChangeUnit)


    override fun getUnitsSystem(): UnitSystem {
        val selectsystem = preferences.getString(Units, UnitSystem.METRIC.name)
        return selectsystem?.let { UnitSystem.valueOf(it) }!!
    }

}



/*
val measure1: Measure<Volume> = 10(liter)

object liter {

}

val measure2: Measure<Length> = 10(meter)
val measure3: Measure<Mass> = 10(gram)
val measure4: Measure<Power> = 10(watt)
val measure5: Measure<Byte> = 10(byte)
val measure6: Measure<Time> = 10(second)
val measure7: Measure<Time> = 10(minute)
val measure8: Measure<Time> = 10(hour)
val measure9: Measure<Time> = 10(day)

// Instantiating a Measure with a multiplier
val measure10: Measure<Length> = 10(Kilo.meter)
val measure11: Measure<Volume> = 10(Femto.liter)

// Basic operations on two Measures
val reduction: Measure<Power> = 10(watt) - 10(Kilo.watt)
val addition: Measure<Time> = 10(second) + 10(minute)

// Dynamic between two Measures
val dynamic1: Dynamic<Mass, Volume> = 10(Nano.gram) / liter
val dynamic2: Dynamic<Byte, Time> = 10(Giga.byte) / 2(hour)

// Normalizing a Dynamic of two Measures by multiplying with a Measure
val dynamicNormalized1: Measure<Mass> = 10(Nano.gram) / liter * 2(liter)
val dynamicNormalized2: Measure<Length> = 10(meter) / day * 2(day)

// Combining and deducting a list of Measures
val combined = listOf(10(gram), 10(Kilo.gram)).combined()
val deducted = listOf(10(Mega.byte), 10(Kilo.byte)).deducted()

// Converting the multiplier of Measures
val converted1 = 10(Kilo.meter) convertedTo meter
val converted2 = 10(liter) convertedTo Nano.liter

// Support for all numbers and complex calculations
val largeNumber = 10(Yotta.gram) / 10(Femto.meter) * 10(Yotta.meter) convertedTo Femto.gram
val smallNumber = 10(Femto.watt) / 10(Yotta.meter) * 10(Femto.meter) convertedTo Yotta.watt*/
