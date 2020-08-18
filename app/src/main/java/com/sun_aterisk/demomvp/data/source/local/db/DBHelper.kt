package com.sun_aterisk.demomvp.data.source.local.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.sun_aterisk.demomvp.utils.AppConstraints

class DBHelper(context: Context) :
    SQLiteOpenHelper(context, AppConstraints.DB_NAME, null, AppConstraints.DB_VERSION) {


    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL(SQLITE_CREATE_TABLE_FAVORITE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORITE)
    }

    companion object {
        //column of Favorite table
        const val TABLE_FAVORITE = "favorite"
        const val COLUMN_FAVORITE_ID = "Duty_Id"
        const val COLUMN_POST_ID = "Post_Id"
        const val COLUMN_USER_ID = "User_Id"
        const val COMA_SEP = ","

        // create table favorite
        val SQLITE_CREATE_TABLE_FAVORITE =
            "CREATE TABLE " + DBHelper.TABLE_FAVORITE + " (" + DBHelper.COLUMN_FAVORITE_ID + " INTEGER PRIMARY KEY " +
                    "AUTOINCREMENT" + DBHelper.COMA_SEP +
                    DBHelper.COLUMN_POST_ID + " INTEGER" + DBHelper.COMA_SEP +
                    DBHelper.COLUMN_USER_ID + " INTEGER" +
                    ")"
    }

}
