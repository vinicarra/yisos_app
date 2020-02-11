package com.carra.yisos_app.view.ui.adapter.holder

import androidx.core.os.bundleOf
import androidx.databinding.ViewDataBinding
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.carra.yisos_app.BR
import com.carra.yisos_app.R
import com.carra.yisos_app.model.person.Person
import com.carra.yisos_app.view.ui.person_list.PersonListViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_person_list_item.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import kotlinx.serialization.json.*
import kotlinx.serialization.*

class PersonListViewHolder constructor(private val dataBinding: ViewDataBinding, private val personListViewModel: PersonListViewModel)
    : RecyclerView.ViewHolder(dataBinding.root) {

    fun setup(person: Person) {
        dataBinding.setVariable(BR.person, person)
        dataBinding.executePendingBindings()

        Picasso.get().load(person.photo).into(itemView.profile_image)

        itemView.onClick {
            val json = Json(JsonConfiguration.Stable)
            val bundle = bundleOf("person" to json.stringify(Person.serializer(), person))
            itemView.findNavController().navigate(R.id.action_personListFragment_to_personDetailFragment, bundle)
        }
    }

}