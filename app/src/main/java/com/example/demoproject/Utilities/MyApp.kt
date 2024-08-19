package com.example.demoproject.Utilities

import com.example.demoproject.Models.TodosItem
import android.app.Application
import com.example.demoproject.Service.RealmDatabase
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()
        RealmDatabase
    }
}