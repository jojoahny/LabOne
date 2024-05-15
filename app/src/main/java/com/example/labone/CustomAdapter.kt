package com.example.labone

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(var list: UsersResponse?) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val Name:TextView=itemview.findViewById(R.id.txt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_recycle, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.Name.text= list!![position].id.toString()+"\n"+ list!![position].name+"\n"+ list!![position].username + "\n"+ list!![position].address.city
    }

}