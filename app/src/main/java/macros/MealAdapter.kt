package macros

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.life_is_well.R

class MealAdapter(val c:Context,val mealList:ArrayList<MealData>):RecyclerView.Adapter<MealAdapter.MealViewHolder>()
{

    inner class MealViewHolder(val v: View): RecyclerView.ViewHolder(v){
        val meal = v.findViewById<TextView>(R.id.tvMeal)
        val foods = v.findViewById<TextView>(R.id.tvFoods)
        val calories = v.findViewById<TextView>(R.id.tvCalories)
        val ounces = v.findViewById<TextView>(R.id.tvOunces)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
         val inflater = LayoutInflater.from(parent.context)
         val v = inflater.inflate(R.layout.macro_item,parent,false)
         return MealViewHolder(v)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val newMeal = mealList[position]
        holder.meal.text = newMeal.userMeal
        holder.foods.text = newMeal.userFoodDrink
        holder.calories.text = newMeal.userCalories.toString()
        holder.ounces.text = newMeal.userOunces.toString()
    }

    override fun getItemCount(): Int {
        return mealList.size
    }
}