package com.miftah.absensipresiden

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.miftah.absensipresiden.databinding.ActivityMainBinding
import com.miftah.absensipresiden.databinding.ItemRowPresidentBinding

class MainActivity : AppCompatActivity() {

    private lateinit var rvPresident: RecyclerView
    private lateinit var mainActivity: ActivityMainBinding
    private lateinit var cardPresidentActivity: ItemRowPresidentBinding
    private val listPresident = mutableListOf<President>()

    companion object{
        private const val STATE_LIST_PRESIDENT = "state_list_president"
    }

//    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = ActivityMainBinding.inflate(layoutInflater)
        cardPresidentActivity = ItemRowPresidentBinding.inflate(layoutInflater)

        setContentView(mainActivity.root)

        rvPresident = mainActivity.rvPresident
        rvPresident.setHasFixedSize(true)

        /* Only for Android with API 33 or higher */
//        if (savedInstanceState == null) {
//            listPresident.addAll(getListPresident())
//        }else{
//            listPresident = savedInstanceState.getParcelableArrayList(STATE_LIST_PRESIDENT, President::class.java)
//        }

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
        val listPresidentAdapter = ListPresidentAdapter(ArrayList(listPresident))
        rvPresident.adapter = listPresidentAdapter

        listPresidentAdapter.setOnItemCallback(object : ListPresidentAdapter.IonClickListerner {
            override fun onClickView(data: President) {
                val moveWithObject = Intent(this@MainActivity, PresidentDetil::class.java)
                moveWithObject.putExtra(PresidentDetil.EXTRA_PRESIDENT, data)
                startActivity(moveWithObject)
            }

            override fun onClickCb(index: Int) {
                listPresident[index].check = when (listPresident[index].check) {
                    1 -> 0
                    else -> 1
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_about, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_page -> {
                val moveToBio = Intent(this@MainActivity, ProfileInformationActivity::class.java)
                moveToBio.putExtra(ProfileInformationActivity.EXTRA_NAMA, "Miftah Nugraha")
                moveToBio.putExtra(
                    ProfileInformationActivity.EXTRA_BIO, """
                    Nama ku Miftah Nugraha, berusia awal 20-an, memiliki hasrat yang mendalam untuk berjelajah dan menjelajahi keindahan alam Indonesia. Arka lahir dan dibesarkan di kota besar, tetapi sejak kecil, dia selalu merasa terikat dengan alam dan keindahan alam Indonesia yang beragam.
                """.trimIndent()
                )
                moveToBio.putExtra(
                    ProfileInformationActivity.EXTRA_EMAIL,
                    "miftah.nugraha@mhs.itenas.ac.id"
                )
                moveToBio.putExtra(ProfileInformationActivity.EXTRA_PHOTO, R.drawable.photo_self)
                startActivity(moveToBio)
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList(STATE_LIST_PRESIDENT, ArrayList(listPresident))
        super.onSaveInstanceState(outState)
    }
}