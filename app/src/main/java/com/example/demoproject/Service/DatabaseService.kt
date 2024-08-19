package com.example.demoproject.Service

import com.example.demoproject.Models.TodosItem
import com.example.demoproject.Utilities.MyApp
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmResults

class DatabaseService {

    companion object {
        private val realm = MyApp.realm

        fun saveItem(isCompleted: Boolean, id: Int, userId: Int, title: String) {
            realm.writeBlocking {
                copyToRealm(TodosItem().apply {
                    completed = isCompleted
                    this.id = id
                    this.userId = userId
                    this.title = title
                }, updatePolicy = UpdatePolicy.ALL)
            }
        }
        fun deleteAllItems() {
            realm.writeBlocking {
                val deleteitems = query<TodosItem>().find()
                delete(deleteitems)
            }
        }

        fun readData(): RealmResults<TodosItem> {
            val tododata = realm.query<TodosItem>().find()
            return tododata
        }
    }
}