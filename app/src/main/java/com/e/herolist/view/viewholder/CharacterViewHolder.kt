package com.e.herolist.view.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.e.herolist.R
import com.e.herolist.data.listener.CharacterListener
import com.e.herolist.domain.CharacterModel


class CharacterViewHolder(itemView: View, private val listener: CharacterListener) :
    RecyclerView.ViewHolder(itemView) {

    private var mTextName: TextView = itemView.findViewById(R.id.text_name)
    private var mTextDescription: TextView = itemView.findViewById(R.id.text_description)

    fun bindData(character: CharacterModel) {
        this.mTextName.text = character.name
        this.mTextDescription.text = character.description
       itemView.setOnClickListener { listener.onListClick(character) }
    }
}