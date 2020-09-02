package com.example.sundevstest.main.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sundevstest.R
import com.example.sundevstest.pojo.Result
import com.example.sundevstest.util.Util
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_grid_main.view.*

class ComicAdapter(
    private var results: ArrayList<Result>,
    private val listener: ComicClickListener
    ): RecyclerView.Adapter<ComicAdapter.MViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MViewHolder {

        var layout = 0

        if (Util.visualization == Util.LIST){
            layout = R.layout.item_list_main
        }else{
            layout = R.layout.item_grid_main
        }

        val view = LayoutInflater.from(parent.context)
            .inflate(layout, parent, false)
        return MViewHolder(view)
    }

    override fun getItemCount(): Int {
        return results.size
    }

    override fun onBindViewHolder(holder: MViewHolder, position: Int) {
        holder.bind(results[position], listener)
    }

    class MViewHolder(view: View): RecyclerView.ViewHolder(view) {

        fun bind(result: Result, listener: ComicClickListener) =  with(itemView){
            val nombre = if(result.name.isNullOrEmpty()) "" else result.name
            Glide.with(item_main_img.context).load(result.image.original_url).into(item_main_img)
            item_main_name.setText(nombre + " #" + result.issue_number)
            item_main_date.setText(result.date_added)

            item_main_img.setOnClickListener {
                listener.onClickComicListener(result)
            }
        }
    }
}