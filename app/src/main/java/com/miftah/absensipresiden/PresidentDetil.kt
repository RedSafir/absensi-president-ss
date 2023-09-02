package com.miftah.absensipresiden

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.miftah.absensipresiden.databinding.ActivityPresidentDetilBinding

class PresidentDetil : AppCompatActivity() {

    private lateinit var presidentDetilBinding: ActivityPresidentDetilBinding

    companion object {
        const val EXTRA_PRESIDENT = "extra_president"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        presidentDetilBinding = ActivityPresidentDetilBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(presidentDetilBinding.root)

        val president = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<President>(EXTRA_PRESIDENT, President::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<President>(EXTRA_PRESIDENT)
        }

        if (president != null) {
            val (nama, negara, masa, pandangan, bio, foto, check) = president

            presidentDetilBinding.apply {
                this.txtDetilNama.text = nama
                this.txtNegaraMemerintah.text = negara
                this.txtMasa.text = masa
                this.txtPandanganPolitik.text = pandangan
                this.txtBio.text = bio

                Glide.with(this@PresidentDetil)
                    .load(foto)
                    .into(this.imgDetil)
            }

            presidentDetilBinding.actionShare.setOnClickListener {
                val textToShare = """
                Nama President : $nama
                Negara Assal : $negara
                Masa Jabatan : $masa
                Pandangan : $pandangan
                Bio : $bio
            """.trimIndent()

                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_TEXT, textToShare)

                val chooserTitle = "Pilih aplikasi untuk berbagi"
                val chooser = Intent.createChooser(shareIntent, chooserTitle)

                startActivity(chooser)
            }
        }
    }

}