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
import com.example.life_is_well.databinding.ActivityFinancePageMainBinding
import com.example.life_is_well.databinding.ActivityMainBinding
import com.example.life_is_well.finance.FinanceUserData
import com.example.life_is_well.finance.UserAdapter
import com.example.life_is_well.finance_account_pages.*
import com.example.life_is_well.goalsPages.GoalsPageMain
import com.example.life_is_well.home.HomeButtonAdapter
import com.example.life_is_well.home.HomeUserData
import com.example.life_is_well.home.misc_home_activities.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_finance_page_main.*

class FinancePageMain : AppCompatActivity() {
    private lateinit var addsBtn:FloatingActionButton

    private lateinit var mainAccountIntent: Intent
    private lateinit var account2Intent: Intent
    private lateinit var account3Intent: Intent
    private lateinit var account4Intent: Intent
    private lateinit var account5Intent: Intent
    private lateinit var account6Intent: Intent
    private lateinit var account7Intent: Intent
    private lateinit var account8Intent: Intent
    private lateinit var account9Intent: Intent
    private lateinit var account10Intent: Intent

    private lateinit var finRecv:RecyclerView

    private lateinit var bindingFinance: ActivityFinancePageMainBinding

    private lateinit var userList:ArrayList<FinanceUserData>

    private lateinit var title: Array<String>

    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingFinance = ActivityFinancePageMainBinding.inflate(layoutInflater)

        setContentView(bindingFinance.root)

        addsBtn = findViewById(R.id.financeAddingBtn)

        finRecv = findViewById(R.id.financeRecycler)

        mainAccountIntent = Intent(this,MainAccountActivity::class.java)
        account2Intent = Intent(this,AccountActivity2::class.java)
        account3Intent = Intent(this,AccountActivity3::class.java)
        account4Intent = Intent(this, AccountActivity4::class.java)
        account5Intent = Intent(this, AccountActivity5::class.java)
        account6Intent = Intent(this, AccountActivity6::class.java)
        account7Intent = Intent(this, AccountActivity7::class.java)
        account8Intent = Intent(this, AccountActivity8::class.java)
        account9Intent = Intent(this, AccountActivity9::class.java)
        account10Intent = Intent(this, AccountActivity10::class.java)

        title = arrayOf("Main")

        finRecv.layoutManager = GridLayoutManager(this, 2, RecyclerView.VERTICAL,false)

        addsBtn.setOnClickListener { addInfo() }
        userList = ArrayList()
        userAdapter = UserAdapter(this, userList){position: Int -> onFinanceListItemClick(position)}
        getUserdata()


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

    private fun onFinanceListItemClick(position: Int) : Unit {
        when(position){
            0 -> startActivity(mainAccountIntent)
            1 -> startActivity(account2Intent)
            2 -> startActivity(account3Intent)
            3 -> startActivity(account4Intent)
            4 -> startActivity(account5Intent)
            5 -> startActivity(account6Intent)
            6 -> startActivity(account7Intent)
            7 -> startActivity(account8Intent)
            8 -> startActivity(account9Intent)
            9 -> startActivity(account10Intent)

        }

    }

    private fun getUserdata() {

        for (i in title.indices) {
            val finBtn = FinanceUserData(title[i])
            userList.add(finBtn)
        }

        finRecv.adapter = UserAdapter(this, userList){position: Int ->
            onFinanceListItemClick(position)
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