package com.example.demoproject.Utilities

import com.example.demoproject.Models.TodosItem
import android.app.Application
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

class MyApp: Application() {

    companion object {
        lateinit var realm: Realm
    }

    override fun onCreate() {
        super.onCreate()
        val config = RealmConfiguration.create(
            schema = setOf(TodosItem::class)
        )
        realm = Realm.open(config)

    }
}