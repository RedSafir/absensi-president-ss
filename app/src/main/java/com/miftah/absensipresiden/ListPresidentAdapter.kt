package com.miftah.absensipresiden

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.miftah.absensipresiden.databinding.ItemRowPresidentBinding

class ListPresidentAdapter(
    private val listPresident: ArrayList<President>
) : RecyclerView.Adapter<ListPresidentAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: IonClickListerner

    inner class ListViewHolder(var binding: ItemRowPresidentBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding =
            ItemRowPresidentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listPresident.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (nama, negara, _, _, _, foto, check) = listPresident[position]

        holder.binding.apply {
            this.txtNamePresident.text = nama
            this.cdCountry.text = negara
            this.cbKehadiran.isChecked = check != 0

            Glide.with(holder.itemView.context)
                .load(foto)
                .into(this.imgPresiden)

            if (this.cbKehadiran.isChecked) {
                this.cardPresident.setBackgroundColor(R.style.Check)
                holder.binding.txtNamePresident.setTextColor(holder.binding.cardPresident.context.getColor(R.color.blue90))
                holder.binding.cdCountry.setTextColor(holder.binding.cardPresident.context.getColor(R.color.blue90))
            } else {
                this.cardPresident.setBackgroundColor(R.style.unCheck)
                holder.binding.txtNamePresident.setTextColor(holder.binding.cardPresident.context.getColor(R.color.blue10))
                holder.binding.cdCountry.setTextColor(holder.binding.cardPresident.context.getColor(R.color.blue10))
            }
        }

        holder.itemView.setOnClickListener{
            onItemClickCallback.onClickView(listPresident[holder.adapterPosition])
        }

        holder.binding.cbKehadiran.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                holder.binding.cardPresident.setBackgroundColor(R.style.Check)
                holder.binding.txtNamePresident.setTextColor(holder.binding.cardPresident.context.getColor(R.color.blue90))
                holder.binding.cdCountry.setTextColor(holder.binding.cardPresident.context.getColor(R.color.blue90))
            } else{
                holder.binding.cardPresident.setBackgroundColor(R.style.unCheck)
                holder.binding.txtNamePresident.setTextColor(holder.binding.cardPresident.context.getColor(R.color.blue10))
                holder.binding.cdCountry.setTextColor(holder.binding.cardPresident.context.getColor(R.color.blue10))
            }
        }
    }

    interface IonClickListerner {
        fun onClickView(data : President)
    }

    fun setOnItemCallback(onItemClickCallback: IonClickListerner) {
        this.onItemClickCallback = onItemClickCallback
    }

}