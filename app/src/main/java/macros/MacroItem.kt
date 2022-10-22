package macros

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.life_is_well.R

class MacroItem : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.macro_item)
    }
}