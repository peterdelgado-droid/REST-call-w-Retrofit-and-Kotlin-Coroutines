package com.e.herolist.data.repository

import android.content.Context
import com.e.herolist.R
import com.e.herolist.domain.ResponseCharacterModel
import com.e.herolist.data.listener.APIListener
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await

class CharacterRepository(val context: Context) {

    private val mRemote = ApiService.createService(ApiService::class.java)
    suspend fun list(listener: APIListener<ResponseCharacterModel>, limit: Int, offset: Int) {
        val call: Call<ResponseCharacterModel> = mRemote.getList(limit, offset)

        call.enqueue(object : Callback<ResponseCharacterModel> {
            override fun onFailure(call: Call<ResponseCharacterModel>, t: Throwable) {
              //todu
            }
            override fun onResponse(
                call: Call<ResponseCharacterModel>,
                response: Response<ResponseCharacterModel>
            ) {
                response.body()?.let { listener.onSuccess(it) }

            }
        })
    }
}