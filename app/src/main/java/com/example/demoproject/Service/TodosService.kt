package com.example.demoproject.Service

import android.provider.ContactsContract.Data
import com.example.demoproject.Models.TodosItem
import android.util.Log
import io.realm.kotlin.query.RealmResults
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import java.net.HttpURLConnection
import java.net.URL


class TodosService {


    companion object {

        fun fetchTodosFromApi(urlString: String, callback: (List<TodosItem>?) -> Unit) {
//            DatabaseService.deleteAllItems()
            val items = mutableListOf<TodosItem>()
            Thread {
                val url = URL(urlString)
                val connection = url.openConnection() as HttpURLConnection

                try {
                    connection.requestMethod = "GET"
                    connection.connect()

                    val inputStream = connection.inputStream
                    val response = inputStream.bufferedReader().use { it.readText() }

                    val jsonArray = JSONArray(response)

                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        val completed = jsonObject.getBoolean("completed")
                        val id = jsonObject.getInt("id")
                        val title = jsonObject.getString("title")
                        val userId = jsonObject.getInt("userId")
//                        DatabaseService.saveItem(completed, id, userId, title)
                        items.add(TodosItem(completed, id, title, userId))
                    }

                    android.os.Handler(android.os.Looper.getMainLooper()).post {
                        callback(items)
                    }

                } catch (e: Exception) {
                    Log.e("MYTAG", "Error exception: $e")

                    android.os.Handler(android.os.Looper.getMainLooper()).post {
                        callback(null)
                    }
                } finally {
                    connection.disconnect()
                }
            }.start()
        }
    }
}
