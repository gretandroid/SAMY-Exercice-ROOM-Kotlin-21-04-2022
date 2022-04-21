package com.example.tpandroid_room_kotlin.database

import java.text.SimpleDateFormat
import java.util.*

class TestDatas {

    // On définit les éléments statiques de la classe
    companion object {
        val formatter = SimpleDateFormat(
            "dd/mm/yyyy",
            Locale.getDefault()
        )

        fun getAll() = arrayListOf<PersonEntity>(
            PersonEntity(formatter.parse("29/1/1988"),"Michel"),
            PersonEntity(formatter.parse("07/3/2008"),"Kim"),
            PersonEntity(formatter.parse("12/8/2055"),"Samy"),
            PersonEntity(formatter.parse("29/11/1980"),"Mathieu")
        )
    }



}