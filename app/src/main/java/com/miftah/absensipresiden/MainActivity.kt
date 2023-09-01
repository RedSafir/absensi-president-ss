package com.miftah.absensipresiden

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.miftah.absensipresiden.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var rvPresident: RecyclerView
    private lateinit var mainActivity: ActivityMainBinding
    private val listPresident = ArrayList<President>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivity.root)

        rvPresident = mainActivity.rvPresident
        rvPresident.setHasFixedSize(true)

        listPresident.addAll(getListPresident())
        showRecyclerList()

    }

    private fun getListPresident(): ArrayList<President> {
        val dataNama = resources.getStringArray(R.array.data_name)
        val dataNegara = resources.getStringArray(R.array.data_negara)
        val dataMasa = resources.getStringArray(R.array.data_masa)
        val dataPandangan = resources.getStringArray(R.array.data_politik)
        val dataBio = resources.getStringArray(R.array.data_bio)
        val dataFoto = resources.getStringArray(R.array.img_president)
        val dateCheck = resources.getIntArray(R.array.data_checked)
        val dataList = ArrayList<President>()
        for (i in dataNama.indices) {
            val list = President(
                dataNama[i],
                dataNegara[i],
                dataMasa[i],
                dataPandangan[i],
                dataBio[i],
                dataFoto[i],
                dateCheck[i]
            )
            dataList.add(list)
        }

        return dataList
    }

    private fun showRecyclerList() {
        rvPresident.layoutManager = LinearLayoutManager(this)
        val listPresidentAdapter = ListPresidentAdapter(listPresident)
        rvPresident.adapter = listPresidentAdapter
    }
}