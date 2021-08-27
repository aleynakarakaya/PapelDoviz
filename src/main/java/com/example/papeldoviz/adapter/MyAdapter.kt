package com.example.papeldoviz.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.papeldoviz.R
import com.example.papeldoviz.fragment.DetailFragment
import com.example.papeldoviz.servis.MyDataItem
import kotlinx.android.synthetic.main.row_item.view.*

class MyAdapter(
        val context: Context,
        private val userList: List<MyDataItem>,
        private val localSupportFragmentManager: FragmentManager
) : RecyclerView.Adapter<RcViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RcViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.row_item, parent, false)
        return RcViewHolder(itemView, localSupportFragmentManager)
    }

    override fun getItemCount(): Int {
        return userList.size
    }


    override fun onBindViewHolder(holder: RcViewHolder, position: Int) {

        holder.bind(userList[position])

    }
    //holder.itemView.image_movie?.gorselIndir(userList.get(position).logo_url, placeholderYap(holder.itemView.context))

}

class RcViewHolder(localItemView: View,
                   private val sprfrg: FragmentManager?) : RecyclerView.ViewHolder(localItemView) {
    fun bind(myDataItem: MyDataItem?) {
        Log.wtf("Tag Tag Tag", myDataItem.toString())

        itemView.apply {
            coinName.text = myDataItem?.name
            coinFiatCurrency.text = myDataItem?.currency
            Glide.with(itemView.context).load(myDataItem?.logo_url).placeholder(R.drawable.ic_launcher_background).into(coinLogo)
        }

        val name = itemView.findViewById<TextView>(R.id.coinName)
        val logo = itemView.findViewById<ImageView>(R.id.coinLogo)
        val currency = itemView.findViewById<TextView>(R.id.coinFiatCurrency)
        val layout = itemView.findViewById<CardView>(R.id.cView)

        layout.setOnClickListener {
            sprfrg?.beginTransaction()?.add(
                    R.id.baseFragmentContainer,
                    DetailFragment.newInstance(
                            coinId = myDataItem?.id,
                            coinLogo = myDataItem?.logo_url,
                            coinName = myDataItem?.name,
                            coinFiatCurrency = myDataItem?.currency)
            )?.addToBackStack(null)?.commit()
        }


        //sprfrg?.popBackStack("attach", FragmentManager.POP_BACK_STACK_INCLUSIVE)


        name.apply {
            this.text = myDataItem?.name
        }

        currency.apply {
            this.text = myDataItem?.currency
        }

        Glide.with(itemView.context).load(myDataItem?.logo_url).placeholder(R.drawable.ic_launcher_background).into(logo)


    }
}
