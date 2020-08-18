package com.sun_aterisk.demomvp.data.source.local.db

import android.content.ContentValues
import android.content.Context
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import com.sun_aterisk.demomvp.data.model.Favorite

class FavoriteLocalDataSource : FavoriteDAO {
    private var context: Context? = null
    private var database: SQLiteDatabase? = null
    private var dBHelper: DBHelper? = null

    constructor(context: Context) {
        this.context = context
        dBHelper = DBHelper(context)
        try {
            open()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    fun open() {
        database = dBHelper?.getWritableDatabase()
    }

    fun close() {
        dBHelper?.close()
    }

    override fun insertFavorite(favorite: Favorite): Boolean {
        return try {
            val contentValues = ContentValues()
            contentValues.put(DBHelper.COLUMN_POST_ID, favorite.post.postId)
            contentValues.put(DBHelper.COLUMN_USER_ID, favorite.user.userId)
            database?.insert(DBHelper.TABLE_FAVORITE, null, contentValues)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    override fun isPostFavorite(postId: Int, userId: Int): Boolean {
        val cursor = database?.rawQuery(
            "select * from " + DBHelper.TABLE_FAVORITE + " where " +
                    DBHelper.COLUMN_POST_ID + " =" + postId + " and " +
                    DBHelper.COLUMN_USER_ID + " =" + userId,
            null
        )
        cursor?.moveToFirst()
        return if (cursor != null && cursor.moveToFirst()) {
            cursor.close()
            true
        } else false
    }

    override fun deletePostFavorite(postId: Int, userId: Int) {
        database?.delete(
            DBHelper.TABLE_FAVORITE,
            DBHelper.COLUMN_POST_ID + " = ?" + " and " + DBHelper.COLUMN_USER_ID + " = ?",
            arrayOf(postId.toString(), userId.toString())
        );
    }


}
