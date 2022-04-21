package com.example.tpandroid_room_kotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tpandroid_room_kotlin.MyAdapter.ViewHolder
import com.example.tpandroid_room_kotlin.database.PersonEntity
import com.example.tpandroid_room_kotlin.databinding.RowBinding
import java.text.SimpleDateFormat
import java.util.*

class MyAdapter(val listener: listItemListener, val persons: List<PersonEntity>): RecyclerView.Adapter<ViewHolder>() {

    val selectedPersons = arrayListOf<PersonEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val person = persons[position]

        with(holder.binding) {
            name.text = person.nom
            date.text = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(person.date)

            checkbox.setOnClickListener{
                if(selectedPersons.contains(person)) {
                    selectedPersons.remove(person)
                    checkbox.setImageResource(android.R.drawable.checkbox_off_background)
                } else {
                    selectedPersons.add(person)
                    checkbox.setImageResource(android.R.drawable.checkbox_on_background)
                }

                listener.inItemSelectionChanged()
            }
        }
    }

    override fun getItemCount(): Int {
        return persons.size
    }



    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val binding = RowBinding.bind(itemView)
    }

    interface listItemListener {
        fun onItemClick(id: Int)
        fun inItemSelectionChanged()
    }
}