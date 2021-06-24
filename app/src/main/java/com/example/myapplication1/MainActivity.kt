package com.example.myapplication1

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_main)
        super.onCreate(savedInstanceState)

    }

    fun onButtonOkClicked(view: View) {

        val  editTextTextId  = findViewById(R.id.editTextTextPersonName) as EditText
        val editTextTextName = findViewById(R.id.editTextTextPersonName2)  as EditText
        val editTextTextPassword= findViewById(R.id.editTextPassword)  as EditText

        var id = editTextTextId.text.toString()
        var name =editTextTextName.text.toString()
        var password=editTextTextPassword.text.toString()

        val databaseHandler: DatabaseHandler= DatabaseHandler(this)
        if(id.trim()!="" && name.trim()!="" && password.trim()!=""){
            val status = databaseHandler.addPerson(PersonClass(Integer.parseInt(id),name, password))
            if(status > -1){
                Toast.makeText(applicationContext,"record save",Toast.LENGTH_LONG).show()
                val secondActivity = Intent(this,SecondActivity::class.java)
                startActivity(secondActivity)
            }
        }else{
            Toast.makeText(applicationContext,"id or name or email cannot be blank",Toast.LENGTH_LONG).show()
        }

    }

    fun onButtonCancelClicked(view: View) {

        val editTextTextId = findViewById<EditText>(R.id.editTextTextPersonName)
        val editTextTextName = findViewById<EditText>(R.id.editTextTextPersonName2)
        val editTextTextPassword= findViewById(R.id.editTextPassword)  as EditText

        editTextTextId.setText("")
        editTextTextName.setText("")
        editTextTextPassword.setText("")
    }
}