package com.example.tpandroid_room_kotlin.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.*

@Entity(tableName = "person")
data class PersonEntity(

    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var date: Date,
    var nom: String
) {
    constructor(date: Date, nom: String): this(
        0,
        date,
        nom
    )
}
