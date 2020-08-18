package com.sun_aterisk.demomvp.utils

import com.sun_aterisk.demomvp.data.model.Album
import com.sun_aterisk.demomvp.data.model.Photo
import com.sun_aterisk.demomvp.data.model.Post
import org.json.JSONArray
import org.json.JSONObject

object JsonUtils {
    fun convertToMap(jsonObject: JSONObject): Map<String, String> {
        val keys = jsonObject.names()
        var map: MutableMap<String, String> = HashMap()
        for (i in 0 until keys.length()) {
            val key = keys.getString(i)
            val value = jsonObject.getString(key)
            map[key] = value
        }
        return map
    }
    fun toListPost(json: JSONArray): MutableList<Post> {
        var listPost = mutableListOf<Post>()
        for (index in 0..json.length() - 1) {
            listPost.add(Post(json.getJSONObject(index)))
        }
        return listPost
    }

    fun toListPhoto(json: JSONArray): MutableList<Photo> {
        var listPhoto = mutableListOf<Photo>()
        for (index in 0..json.length() - 1) {
            listPhoto.add(Photo(json.getJSONObject(index)))
        }
        return listPhoto
    }

    fun toListAlbum(json: JSONArray): MutableList<Album> {
        var listAlbum = mutableListOf<Album>()
        for (index in 0..json.length() - 1) {
            listAlbum.add(Album(json.getJSONObject(index)))
        }
        return listAlbum
    }
}
