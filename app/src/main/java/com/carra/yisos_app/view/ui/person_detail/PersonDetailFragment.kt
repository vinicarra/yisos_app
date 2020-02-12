package com.carra.yisos_app.view.ui.person_detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import com.carra.yisos_app.R
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.carra.yisos_app.databinding.FragmentPersonDetailBinding
import com.carra.yisos_app.model.person.Person
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_person_detail.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import kotlinx.serialization.parse
import org.jetbrains.anko.longToast
import org.jetbrains.anko.sdk27.coroutines.onClick

class PersonDetailFragment : Fragment() {

    private lateinit var viewDataBinding: FragmentPersonDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = FragmentPersonDetailBinding.inflate(inflater, container, false).apply {
            viewmodel = ViewModelProviders.of(this@PersonDetailFragment).get(PersonDetailViewModel::class.java)
            lifecycleOwner = viewLifecycleOwner
        }
        return viewDataBinding.root
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity).supportActionBar?.show()
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupLayout()
    }

    private fun setupLayout() {
        val json = Json(JsonConfiguration.Stable)
        var person = json.parse(Person.serializer(), arguments?.get("person") as String)
        viewDataBinding.person = person
        val loadedPhoto = Picasso.get().load(person.photo)
        loadedPhoto.into(profile_image)
        loadedPhoto.into(cover_image)

        back_image.onClick {
            findNavController().popBackStack()
        }
    }

}