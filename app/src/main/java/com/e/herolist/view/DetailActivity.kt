package com.e.herolist.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.herolist.R
import com.e.herolist.view.adapter.CharacterAdapter
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private lateinit var vDescription: String
    private lateinit var vName: String
    private val mAdapter = CharacterAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val recycler = findViewById<RecyclerView>(R.id.recycler_comic)
        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recycler.adapter = mAdapter

        loadDataFromActivity()
        setComponents()


    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun loadDataFromActivity() {
        val bundle = intent.extras
        if (bundle != null) {
            vDescription = bundle.getString("description").toString()
            vName = bundle.getString("name").toString()
        }
    }

    private fun setComponents() {
        name.text = vName
        description_detail.text =  vDescription
    }


}
