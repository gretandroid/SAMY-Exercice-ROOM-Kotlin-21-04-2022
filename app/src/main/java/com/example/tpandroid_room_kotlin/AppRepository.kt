package com.example.tpandroid_room_kotlin

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.tpandroid_room_kotlin.database.AppDatabase
import com.example.tpandroid_room_kotlin.database.PersonEntity
import java.util.concurrent.Executor
import java.util.concurrent.Executors

// On communique avec la base de données
// Cette classe est toujours un singleton
class AppRepository(context: Context) {
    var ldPersons: LiveData<List<PersonEntity>>
    private val database: AppDatabase

    // Methods
    fun getAllPersons(): LiveData<List<PersonEntity>> = database.personDAO().getAll()

    suspend fun addPerson(person: PersonEntity) {
        database.personDAO().insertPerson(person)
    }

    suspend fun addPersons(persons: List<PersonEntity>) {
        database.personDAO().insertAll(persons)
    }

    suspend fun deleteAllPersons() {
        database.personDAO().deleteAll()
    }

    suspend fun deletePersons(persons: List<PersonEntity>) {
        database.personDAO().deletePersons(persons)
    }

    suspend fun deletePerson(person: PersonEntity) {
        database.personDAO().deletePerson(person)
    }

    companion object {
        // Attributes
        // Une classe qui prends en attribut une instance d'elle même en statique est un Singleton
        private var instance: AppRepository? = null

        // Getters
        fun getInstance(context: Context): AppRepository? {
            if (instance == null) {
                instance = AppRepository(context)
            }
            return instance
        }
    }

    // Constructors
    init {
        database = AppDatabase.getInstance(context)!!
        ldPersons = getAllPersons()
    }
}