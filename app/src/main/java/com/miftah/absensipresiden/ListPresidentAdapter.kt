package com.miftah.absensipresiden

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.miftah.absensipresiden.databinding.ItemRowPresidentBinding

class ListPresidentAdapter(
    private val listPresident: ArrayList<President>
) : RecyclerView.Adapter<ListPresidentAdapter.ListViewHolder>() {

    inner class ListViewHolder(var binding: ItemRowPresidentBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding =
            ItemRowPresidentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listPresident.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (nama, negara, partai, pandangan, bio, foto, check) = listPresident[position]

        holder.binding.apply {
            this.txtNamePresident.text = nama
            this.cdCountry.text = negara
            this.cbKehadiran.isChecked = check != 0

            Glide.with(holder.itemView.context)
                .load(foto)
                .into(this.imgPresiden)
        }

    }


}