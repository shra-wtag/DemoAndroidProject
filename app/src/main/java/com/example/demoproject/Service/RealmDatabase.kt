package com.example.demoproject.Service

import com.example.demoproject.Models.TodosItem
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

object RealmDatabase {
    val realm: Realm

    init {
        val config = RealmConfiguration.Builder(
            schema = setOf(TodosItem::class)
        )
            .name("myRealm")
            .schemaVersion(1)
            .build()
        realm = Realm.open(config)
    }
}