package com.example.demoproject.Models

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class TodosItem: RealmObject {
    var completed: Boolean = false
    @PrimaryKey
    var id: Int = 0
    var title: String = ""
    var userId: Int = 0
}