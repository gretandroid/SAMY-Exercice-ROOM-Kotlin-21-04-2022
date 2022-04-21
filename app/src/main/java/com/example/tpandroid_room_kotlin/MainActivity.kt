package com.example.tpandroid_room_kotlin

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.tpandroid_room_kotlin.database.PersonEntity
import com.example.tpandroid_room_kotlin.database.TestDatas
import com.example.tpandroid_room_kotlin.databinding.ActivityMainBinding
import com.example.tpandroid_room_kotlin.viewmodels.MainActivityViewModel

class MainActivity : AppCompatActivity(), MyAdapter.listItemListener {

    // Attributes
    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var myAdapter: MyAdapter
    private lateinit var mainActivityViewModel: MainActivityViewModel
    private val persons: List<PersonEntity> = ArrayList<PersonEntity>()
    private val loadedMenu = R.menu.main_menu


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        mainActivityViewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        myAdapter = MyAdapter(this, mainActivityViewModel.ldPersons?.value!!)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuId = if(this::myAdapter.isInitialized && myAdapter.selectedPersons.isNotEmpty())
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    fun addAllPersonsButtonOnClick(item: MenuItem) { mainActivityViewModel.addPersons(TestDatas.getAll()) }
    fun deleteAllPersonsButtonOnClick(item: MenuItem) { mainActivityViewModel.deleteAllPersons() }
    fun deleteSelectedPersonsOnClick(item: MenuItem) { mainActivityViewModel.deletePersons(TestDatas.getAll()) }


    override fun onItemClick(id: Int) {

    }

    override fun inItemSelectionChanged() {
        invalidateOptionsMenu()
    }
}