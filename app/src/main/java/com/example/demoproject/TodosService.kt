package com.example.demoproject

import TodosItem
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import java.net.HttpURLConnection
import java.net.URL


class TodosService {
    suspend fun fetchTodosFromApi(urlString: String): List<TodosItem> {
        val items = mutableListOf<TodosItem>()
        withContext(Dispatchers.IO) {
            Log.i("MYTAG", "${Thread.currentThread().name}")
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

                    items.add(TodosItem(completed, id, title, userId))
                }
            } finally {
                connection.disconnect()
            }
        }
        return items
    }
}
