package com.example.papeldoviz.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.papeldoviz.R
import com.example.papeldoviz.activity.ListActivity
import com.example.papeldoviz.fragment.CustomListFragment
import com.example.papeldoviz.fragment.DetailFragment
import com.example.papeldoviz.servis.MyDataItem
import com.example.papeldoviz.util.gorselIndir
import com.example.papeldoviz.util.placeholderYap
import kotlinx.android.synthetic.main.fragment_detail.view.*
import kotlinx.android.synthetic.main.row_item.view.*

class MyAdapter (val context: Context, val userList: List<MyDataItem>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {




    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var userId : TextView
        var title: TextView


        init {
            userId = itemView.userId
            title = itemView.title
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.row_item, parent, false)
        return ViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.userId.text = userList?.get(position)?.currency.toString()
        holder.title.text = userList?.get(position)?.price

        holder.itemView.setOnClickListener {
            Toast.makeText(context,"aleyna bir android developer olmak zorundadır",Toast.LENGTH_LONG).show()
        }

        //glide
        holder.itemView.image_movie?.gorselIndir(userList.get(position).logo_url, placeholderYap(holder.itemView.context))
        //Glide.with(context).load(userList[position].logo_url).placeholder(R.drawable.ic_launcher_background).into(holder.itemView.image_movie)

        /*detail fragmenta geçiş
        holder.itemView.setOnClickListener {
            ListActivity().supportFragmentManager.beginTransaction().add(
                R.id.fragmentContainer2,
                DetailFragment.newInstance(
                    coinId = userList?.get(position)?.id
                )
            ).addToBackStack(null).commit()
        }*/

    }

    override fun getItemCount(): Int {
            return userList.size
    }

}