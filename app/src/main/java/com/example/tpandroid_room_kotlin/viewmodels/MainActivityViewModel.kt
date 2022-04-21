package com.example.tpandroid_room_kotlin.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.tpandroid_room_kotlin.AppRepository
import com.example.tpandroid_room_kotlin.database.PersonEntity
import kotlinx.coroutines.launch

class MainActivityViewModel(application: Application): AndroidViewModel(application) {

    private var repository: AppRepository? = null
    var ldPersons: LiveData<List<PersonEntity>>? = null

    init {
        repository = AppRepository.getInstance(application)
        ldPersons = repository?.ldPersons
    }


    // Methods
    fun addPerson(person: PersonEntity) {
        viewModelScope.launch { repository?.addPerson(person) }
    }

    fun addPersons(persons: List<PersonEntity>) {
        viewModelScope.launch { repository?.addPersons(persons) }
    }

    fun deleteAllPersons() {
        viewModelScope.launch { repository?.deleteAllPersons() }
    }

    fun deletePersons(persons: List<PersonEntity>) {
        viewModelScope.launch { repository?.deletePersons(persons)}
    }

    fun deletePerson(person: PersonEntity) {
        viewModelScope.launch { repository?.deletePerson(person) }
    }
}