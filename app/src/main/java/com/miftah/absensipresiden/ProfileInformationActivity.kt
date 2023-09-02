package com.miftah.absensipresiden

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.miftah.absensipresiden.databinding.ActivityProfileInformationBinding

class ProfileInformationActivity : AppCompatActivity() {

    private lateinit var activityProfileInformation: ActivityProfileInformationBinding
    companion object{
        const val EXTRA_NAMA = "extra_nama"
        const val EXTRA_EMAIL = "extra_email"
        const val EXTRA_BIO = "extra_bio"
        const val EXTRA_PHOTO = "extra_photo"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityProfileInformation = ActivityProfileInformationBinding.inflate(layoutInflater)
        setContentView(activityProfileInformation.root)

        val namaData = intent.getStringExtra(EXTRA_NAMA)
        val emailData = intent.getStringExtra(EXTRA_EMAIL)
        val bioData = intent.getStringExtra(EXTRA_BIO)
        val fotoData = intent.getIntExtra(EXTRA_PHOTO, 0)

        activityProfileInformation.apply {
            txtNamaAbout.text = namaData
            txtEmail.text = emailData
            txtDeskripsi.text = bioData
            imgAbout.setImageResource(fotoData)
        }
    }
}