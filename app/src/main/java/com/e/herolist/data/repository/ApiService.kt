package com.e.herolist.data.repository

import com.e.herolist.domain.ResponseCharacterModel
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("characters")
    fun getList(
            @Query("limit") limit: Int,
            @Query("offset") offset: Int
    ): Call<ResponseCharacterModel>

    @GET("/v1/public/characters/{characterId}")
    fun getCharacter(@Path("characterId") marvelID: String):  Call<ResponseCharacterModel>


    companion object Factory {
        private const val baseUrl = "http://gateway.marvel.com/v1/public/"
        private fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(getOkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
        fun getOkHttpClient() = OkHttpClient().newBuilder()
                .addInterceptor { chain ->
                    val newUrl = chain.request().url()
                            .newBuilder()
                            .addQueryParameter("apikey", "1e113f8b3d71dca2aa057267609e35fb")
                            .addQueryParameter("ts", "9")
                            .addQueryParameter(
                                    "hash",
                                    "138160348bb39c47e6285ff513f80c10"
                            )
                            .build()

                    val newRequest = chain.request()
                            .newBuilder()
                            .url(newUrl)
                            .build()
                    chain.proceed(newRequest)
                }
                .build()

        fun <S> createService(serviceClass: Class<S>): S {
            return getRetrofitInstance().create(serviceClass)
        }


    }

}