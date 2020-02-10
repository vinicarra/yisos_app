package com.carra.yisos_app.api.repositories

import com.carra.yisos_app.api.ApiClient
import com.carra.yisos_app.model.person.Person
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PersonRepository {

    fun getPersonList(onResult: (isSuccess: Boolean, response: List<Person>?) -> Unit) {
        ApiClient.instance.getPersonList().enqueue(object: Callback<List<Person>> {
            override fun onResponse(call: Call<List<Person>>?, response: Response<List<Person>>) {
                if (response != null && response.isSuccessful)
                    onResult(true, response.body()!!)
                else
                    onResult(false, null)
            }

            override fun onFailure(call: Call<List<Person>>, t: Throwable) {
                onResult(false, null)
            }
        })
    }

    companion object {
        private var INSTANCE: PersonRepository? = null
        fun getInstance() = INSTANCE
            ?: PersonRepository().also {
                INSTANCE = it
            }
    }

}