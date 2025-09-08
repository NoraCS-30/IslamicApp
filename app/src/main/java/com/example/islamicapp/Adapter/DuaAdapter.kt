package com.example.islamicapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.islamicapp.Data.Dua

import com.example.islamicapp.R


class DuaAdapter(private var DuaList: List<Dua>) : RecyclerView.Adapter<DuaAdapter.DuaItem>() {
    class DuaItem(itemView : View) : RecyclerView.ViewHolder(itemView){
        val dua=itemView.findViewById<TextView>(R.id.Dua)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DuaItem {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.list_dua,parent,false)
        return DuaItem(view)

    }

    override fun getItemCount(): Int =DuaList.size

    override fun onBindViewHolder(holder: DuaItem, position: Int) {
        val Dua=DuaList[position]
        holder.apply {
            dua.text=Dua.content
        }
    }

    fun setData(newListOfDua: List<Dua>) {
        DuaList = newListOfDua
        notifyDataSetChanged()

    }
}