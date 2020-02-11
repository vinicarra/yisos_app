package com.carra.yisos_app.view.ui.person_detail

import androidx.lifecycle.MutableLiveData
import com.carra.yisos_app.model.person.Person
import com.carra.yisos_app.view.ui.BaseViewModel

class PersonDetailViewModel : BaseViewModel() {

    val person = MutableLiveData<Person>()

}