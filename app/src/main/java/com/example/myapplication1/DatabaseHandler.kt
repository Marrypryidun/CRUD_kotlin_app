package com.example.myapplication1

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteException

//creating the database logic, extending the SQLiteOpenHelper base class
class DatabaseHandler(context: Context): SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "PersonDatabase"
        private val TABLE_CONTACTS = "PersonTable"
        private val KEY_ID = "id"
        private val KEY_NAME = "name"
        private val KEY_PASSWORD = "password"

    }
    override fun onCreate(db: SQLiteDatabase?) {
        //creating table with fields
        val CREATE_CONTACTS_TABLE = ("CREATE TABLE " + TABLE_CONTACTS + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_NAME + " TEXT, "
                + KEY_PASSWORD + " TEXT ) ")
        db?.execSQL(CREATE_CONTACTS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS)
        onCreate(db)
    }


    //method to insert data
    fun addPerson(v: PersonClass):Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ID, v.userId)
        contentValues.put(KEY_NAME, v.userName)
        contentValues.put(KEY_PASSWORD,v.password)

        // Inserting Row
        val success = db.insert(TABLE_CONTACTS, null, contentValues)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }
    //method to read data
    fun viewPersons():List<PersonClass>{
        val personList:ArrayList<PersonClass> = ArrayList<PersonClass>()
        val selectQuery = "SELECT  * FROM $TABLE_CONTACTS"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try{
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var userId: Int
        var userName: String
        var password: String

        if (cursor.moveToFirst()) {
            do {
                userId = cursor.getInt(cursor.getColumnIndex("id"))
                userName = cursor.getString(cursor.getColumnIndex("name"))
                password = cursor.getString(cursor.getColumnIndex("password"))

                val emp= PersonClass(userId = userId, userName = userName, password = password)
                personList.add(emp)
            } while (cursor.moveToNext())
        }
        return personList
    }
    //method to update data
//    fun updateOrder(v: PersonClass):Int{
//        val db = this.writableDatabase
//        val contentValues = ContentValues()
//        contentValues.put(KEY_ID, v.userId)
//        contentValues.put(KEY_NAME, v.userName)
//        contentValues.put(KEY_PASSWORD, v.password)
//
//
//        // Updating Row
//        val success = db.update(TABLE_CONTACTS, contentValues,"id="+v.userId,null)
//        //2nd argument is String containing nullColumnHack
//        db.close() // Closing database connection
//        return success
   //}

    //method to delete data
//    fun deleteEmployee(v: PersonClass):Int{
//        val db = this.writableDatabase
//        val contentValues = ContentValues()
//        contentValues.put(KEY_ID, v.userId) // EmpModelClass UserId
//        // Deleting Row
//        val success = db.delete(TABLE_CONTACTS,"id="+v.userId,null)
//        //2nd argument is String containing nullColumnHack
//        db.close() // Closing database connection
//        return success
//    }
}