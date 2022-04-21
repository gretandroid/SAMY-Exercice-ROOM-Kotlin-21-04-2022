package com.example.tpandroid_room_kotlin.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PersonDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPerson(person: PersonEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(persons: List<PersonEntity>)

    @Delete
    suspend fun deletePerson(person: PersonEntity)

    @Query("SELECT * FROM person WHERE id=:id")
    suspend fun getPersonById(id: Int): PersonEntity

    @Query("SELECT * FROM person")
    fun getAll(): LiveData<List<PersonEntity>>

    @Query("DELETE FROM person")
    suspend fun deleteAll(): Int

    @Query("SELECT COUNT(*) FROM person")
    suspend fun getCount(): Int

    @Delete
    suspend fun deletePersons(persons: List<PersonEntity>)
}