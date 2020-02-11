package com.carra.yisos_app.view.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.carra.yisos_app.databinding.ViewPersonListItemBinding
import com.carra.yisos_app.model.person.Person
import com.carra.yisos_app.view.ui.adapter.holder.PersonListViewHolder
import com.carra.yisos_app.view.ui.person_list.PersonListViewModel

class PersonListAdapter(private val personListViewModel: PersonListViewModel) : RecyclerView.Adapter<PersonListViewHolder>() {
    var personList: List<Person> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val dataBinding = ViewPersonListItemBinding.inflate(inflater, parent, false)
        return PersonListViewHolder(dataBinding, personListViewModel)
    }

    override fun getItemCount(): Int {
        return personList.size
    }

    override fun onBindViewHolder(holder: PersonListViewHolder, position: Int) {
        holder.setup(personList[position])
    }

    fun updatePersonList(personList: List<Person>) {
        this.personList = personList
        notifyDataSetChanged()
    }
}