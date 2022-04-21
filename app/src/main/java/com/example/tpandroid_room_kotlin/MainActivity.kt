package com.example.tpandroid_room_kotlin

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tpandroid_room_kotlin.database.PersonEntity
import com.example.tpandroid_room_kotlin.database.TestDatas
import com.example.tpandroid_room_kotlin.databinding.ActivityMainBinding
import com.example.tpandroid_room_kotlin.viewmodels.MainActivityViewModel

class MainActivity : AppCompatActivity(), MyAdapter.ListItemListener {

    // Attributes
    private lateinit var binding: ActivityMainBinding
    private lateinit var myAdapter: MyAdapter
    private lateinit var mainActivityViewModel: MainActivityViewModel
    private val persons: MutableList<PersonEntity> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        mainActivityViewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]

        mainActivityViewModel.ldPersons?.observe(this) { newList ->
            persons.clear()
            persons.addAll(newList)

            if(!this::myAdapter.isInitialized) {
                myAdapter = MyAdapter(this, persons)
                binding.mainActivityRecyclerView.adapter = myAdapter
                binding.mainActivityRecyclerView.layoutManager = LinearLayoutManager(this)
            } else {
                myAdapter!!.notifyDataSetChanged()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuId = if(this::myAdapter.isInitialized && myAdapter.selectedPersons.isNotEmpty()) {
            R.menu.secondary_menu
        } else {
            R.menu.main_menu
        }
        val menuInflater = menuInflater
        menuInflater.inflate(menuId, menu)
        return true
    }

    fun addAllPersonsButtonOnClick(item: MenuItem) { mainActivityViewModel.addPersons(TestDatas.getAll()) }

    fun deleteAllPersonsButtonOnClick(item: MenuItem) {
        mainActivityViewModel.deleteAllPersons()
    }

    fun deleteSelectedPersonsOnClick(item: MenuItem) {

        // /!\ On doit dupliquer l'ArrayList car même si on le vide à la ligne suivante, le tableau est vidé AVANT que la requête
        // SQL n'arrive en base de données sur le Thread secondaire ! Le delete ne s'effectuera donc pas.
        val personsToDelete = ArrayList<PersonEntity>(myAdapter.selectedPersons)
        mainActivityViewModel.deletePersons(personsToDelete)
        myAdapter.selectedPersons.clear()
        invalidateOptionsMenu()
    }


    override fun onItemClick(id: Int) {

    }

    override fun inItemSelectionChanged() {
        invalidateOptionsMenu()
    }
}