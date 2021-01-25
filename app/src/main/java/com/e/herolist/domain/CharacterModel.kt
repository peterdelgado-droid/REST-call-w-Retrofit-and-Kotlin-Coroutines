package com.e.herolist.domain
import com.google.gson.annotations.SerializedName

data class CharacterModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    )

data class DataCharacterModel(
        @SerializedName("offset")
        val offset: Int,
        @SerializedName("limit")
        val limit: Int,
        @SerializedName("total")
        val total: Int,
        @SerializedName("count")
        val count: Int,
        @SerializedName("results")
        val results: List<CharacterModel>
)


data class ResponseCharacterModel(
        @SerializedName("code")
        val code: Int,
        @SerializedName("etag")
        val etag: String,
        @SerializedName("data")
        val data: DataCharacterModel
)