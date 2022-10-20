package com.example.life_is_well

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.life_is_well.finance.FinanceUserData
import com.example.life_is_well.finance.UserAdapter
import com.example.life_is_well.goalsPages.GoalsPageMain
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_finance_page_main.*

class FinancePageMain : AppCompatActivity() {
    private lateinit var addsBtn:FloatingActionButton
    private lateinit var finRecv:RecyclerView
    private lateinit var userList:ArrayList<FinanceUserData>
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finance_page_main)

        addsBtn = findViewById(R.id.financeAddingBtn)
        finRecv = findViewById(R.id.financeRecycler)
        userList = ArrayList()
        userAdapter = UserAdapter(this, userList)


        finRecv.layoutManager = GridLayoutManager(this, 2)

        finRecv.adapter = userAdapter

        addsBtn.setOnClickListener { addInfo() }




        finance_main_bottom_nav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.profileBottomNav -> {
                    val intent = Intent(this, ProfilePageMain::class.java)
                    startActivity(intent)
                }

                R.id.goalsBottomNav -> {
                    val intent = Intent(this, GoalsPageMain::class.java)
                    startActivity(intent)
                }


                R.id.homeBotNav -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }

                R.id.settingsBottomNav -> {
                    val intent = Intent(this, SettingsPageMain::class.java)
                    startActivity(intent)
                }
            }
            true
        }
    }


    fun financeBackBtn(view: View) {

        val intent = Intent(this,MainActivity::class.java )
        startActivity(intent)

    }

    private fun addInfo() {
        val inflter = LayoutInflater.from(this)
        val v = inflter.inflate(R.layout.finance_add_item_prompt, null)

        val userName = v.findViewById<EditText>(R.id.financeAccountName)

        val addDialog = AlertDialog.Builder(this)
        addDialog.setView(v)
        addDialog.setPositiveButton("Ok"){
                dialog,_->

            val names = userName.text
            userList.add(FinanceUserData("$names"))
            userAdapter.notifyDataSetChanged()
            Toast.makeText(this,"Account Creation Success",Toast.LENGTH_SHORT).show()

            dialog.dismiss()

        }
        addDialog.setNegativeButton("Cancel"){
                dialog, _->
            dialog.dismiss()
            Toast.makeText(this,"Cancel",Toast.LENGTH_SHORT).show()
        }
        addDialog.create()
        addDialog.show()
    }
}