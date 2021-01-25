package com.e.herolist.data.listener
import androidx.recyclerview.widget.RecyclerView
import com.e.herolist.domain.CharacterModel

abstract class CharacterListener : RecyclerView.OnScrollListener() {
    abstract fun onListClick(character: CharacterModel)
}