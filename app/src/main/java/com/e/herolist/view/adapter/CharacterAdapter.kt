package com.e.herolist.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.e.herolist.R
import com.e.herolist.data.listener.CharacterListener
import com.e.herolist.domain.CharacterModel
import com.e.herolist.view.viewholder.CharacterViewHolder


class CharacterAdapter : RecyclerView.Adapter<CharacterViewHolder>() {

    private var mList: MutableList<CharacterModel> = arrayListOf()
    private lateinit var mListener: CharacterListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val item =
            LayoutInflater.from(parent.context).inflate(R.layout.character_list, parent, false)
        return CharacterViewHolder(item, mListener)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bindData(mList[position])
    }

    override fun getItemCount(): Int {
        return mList.count()
    }

    fun attachListener(listener: CharacterListener) {
        mListener = listener
    }

    fun updateList(list: MutableList<CharacterModel>) {
        mList.addAll(list)
        notifyDataSetChanged()
    }


}