package com.example.myapplication1

import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        viewRecord()
        val backButton = findViewById<Button>(R.id.backButton)
        backButton.setOnClickListener {
            val mainActivity = Intent(this, MainActivity::class.java)
            startActivity(mainActivity)
        }
    }

    fun viewRecord() {
        //creating the instance of DatabaseHandler class
        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
        //calling the viewEmployee method of DatabaseHandler class to read the records
        val people: List<PersonClass> = databaseHandler.viewPersons()
        val empArrayId = Array<String>(people.size) { "0" }
        val empArrayName = Array<String>(people.size) { "null" }
        val empArrayPassword = Array<String>(people.size) { "null" }

        var index = 0
        for (e in people) {
            empArrayId[index] = e.userId.toString()
            empArrayName[index] = e.userName
            empArrayPassword[index] = e.password

            index++
        }
        //creating custom ArrayAdapter
        val myListAdapter =
            MyListAdapter(this, empArrayId, empArrayName, empArrayPassword)
        val listView = findViewById<ListView>(R.id.listView)
        listView.adapter = myListAdapter
    }
    //method for deleting records based on id
//    fun deleteRecord(view: View){
//        //creating AlertDialog for taking user id
//        val dialogBuilder = AlertDialog.Builder(this)
//        val inflater = this.layoutInflater
//        val dialogView = inflater.inflate(R.layout.delete_dialog, null)
//        dialogBuilder.setView(dialogView)
//
//        val dltId = dialogView.findViewById(R.id.deleteId) as EditText
//        dialogBuilder.setTitle("Delete Record")
//        dialogBuilder.setMessage("Enter id below")
//        dialogBuilder.setPositiveButton("Delete", DialogInterface.OnClickListener { _, _ ->
//
//            val deleteId = dltId.text.toString()
//            //creating the instance of DatabaseHandler class
//            val databaseHandler: DatabaseHandler= DatabaseHandler(this)
//            if(deleteId.trim()!=""){
//                //calling the deleteEmployee method of DatabaseHandler class to delete record
//                val status = databaseHandler.deleteEmployee(PersonClass(Integer.parseInt(deleteId),"",""))
//                if(status > -1){
//                    Toast.makeText(applicationContext,"record deleted",Toast.LENGTH_LONG).show()
//                    viewRecord()
//                }
//            }else{
//                Toast.makeText(applicationContext,"id cannot be blank",Toast.LENGTH_LONG).show()
//            }
//
//        })
//        dialogBuilder.setNegativeButton("Cancel", DialogInterface.OnClickListener { _, _ ->
//            dltId.setText("")
//        })
//        val b = dialogBuilder.create()
//        b.show()
//    }
}
