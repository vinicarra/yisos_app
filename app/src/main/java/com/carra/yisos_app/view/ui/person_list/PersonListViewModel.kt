package com.carra.yisos_app.view.ui.person_list

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import com.carra.yisos_app.api.repositories.PersonRepository
import com.carra.yisos_app.model.person.Person
import com.carra.yisos_app.view.ui.BaseViewModel

class PersonListViewModel : BaseViewModel() {

    val personListLive = MutableLiveData<List<Person>>()
    private val hasLoaded = MutableLiveData<Boolean>().also { it.value = false }

    fun fetchPersonList() {
        if (!hasLoaded.value!!) {
            dataLoading.value = true
            PersonRepository.getInstance().getPersonList { isSuccess, response ->
                dataLoading.value = false
                if (isSuccess) {
                    personListLive.value = response
                    hasLoaded.value = true
                    empty.value = false
                } else {
                    empty.value = true
                    hasLoaded.value = false
                }
            }
        }
    }

}