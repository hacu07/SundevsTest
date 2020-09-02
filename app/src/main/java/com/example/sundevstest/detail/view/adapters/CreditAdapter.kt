package com.example.sundevstest.detail.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sundevstest.R
import com.example.sundevstest.pojo.Credit
import kotlinx.android.synthetic.main.activity_detail.view.*
import kotlinx.android.synthetic.main.item_credit.view.*

class CreditAdapter(private var credits: ArrayList<Credit>): RecyclerView.Adapter<CreditAdapter.MViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_credit, parent, false)
        return MViewHolder(view)
    }

    override fun getItemCount(): Int {
        return credits.size
    }

    override fun onBindViewHolder(holder: MViewHolder, position: Int) {
        holder.bind(credits[position])
    }

    fun update(credit: Credit) {
        if (credits.contains(credit)){
            val index = credits.indexOf(credit)
            credits.set(index,credit)
            notifyItemChanged(index)
        }
    }

    class MViewHolder(view: View): RecyclerView.ViewHolder(view) {

        fun bind(credit: Credit) =  with(itemView){
            item_detail_name.setText(credit.name)

            if (credit.image != null){
                Glide.with(item_detail_img.context).load(credit.image.original_url).into(item_detail_img)
            }
        }
    }
}