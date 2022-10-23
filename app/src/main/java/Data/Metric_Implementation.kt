package Data

import com.example.life_is_well.SettingsPageMain


class Metric_Implementation(unit : UnitProvider) {

    private val unitSystem = unit.getUnitsSystem()

    val isMetric: Boolean
        get() = unitSystem == UnitSystem.METRIC




}



