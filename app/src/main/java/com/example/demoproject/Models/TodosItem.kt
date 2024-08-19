package com.example.demoproject.Models

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class TodosItem(
    var completed: Boolean,
    @PrimaryKey
    var id: Int,
    var title: String,
    var userId: Int
): RealmObject {
    constructor(): this(false, 0, "", 0)
}