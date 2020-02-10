package com.carra.yisos_app.api

import com.carra.yisos_app.model.person.Person
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("mobiletest.json")
    fun getPersonList(): Call<List<Person>>
}