package com.e.herolist.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.herolist.R
import com.e.herolist.domain.CharacterModel
import com.e.herolist.data.listener.CharacterListener
import com.e.herolist.view.adapter.CharacterAdapter
import com.e.herolist.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var mViewModel: MainViewModel
    private lateinit var mListener: CharacterListener
    private val mAdapter = CharacterAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val recycler = findViewById<RecyclerView>(R.id.recycler_character)
        recycler.layoutManager = GridLayoutManager(this,2)
        recycler.adapter = mAdapter

        mListener = getListener(recycler)
        mListener.let {
            recycler.addOnScrollListener(it)
        }

        mViewModel.characters.observe(this, {
            mAdapter.updateList(it.data.results.toMutableList())

        })
    }

    private fun getListener(recycler: RecyclerView): CharacterListener {
        return object : CharacterListener() {
            override fun onListClick(character: CharacterModel) {
                val intent = Intent(applicationContext, DetailActivity::class.java)
                val bundle = Bundle()
                bundle.putString("description", character.description)
                bundle.putString("name", character.name)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        mAdapter.attachListener(mListener)
        mViewModel.list()
    }

}