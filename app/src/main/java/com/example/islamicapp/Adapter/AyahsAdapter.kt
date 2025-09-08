package com.example.islamicapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.islamicapp.Data.Surah
import com.example.islamicapp.R

class AyahsAdapter(private var ayahsList: Surah) : RecyclerView.Adapter<AyahsAdapter.AyahsItem>() {

    class AyahsItem(itemView : View) :RecyclerView.ViewHolder(itemView){
        val ayahs=itemView.findViewById<TextView>(R.id.Ayahs)
        val number=itemView.findViewById<TextView>(R.id.NumberOfAyahsInList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AyahsItem {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.list_ayahs,parent,false)
        return AyahsItem(view)
    }

    override fun onBindViewHolder(holder: AyahsItem, position: Int) {
        val Ayahs=ayahsList.data.ayahs[position]
        holder.apply {
            ayahs.text=Ayahs.text
            number.text=Ayahs.numberInSurah.toString()
        }
    }

    override fun getItemCount(): Int =ayahsList.data.ayahs.size

    fun setData(newListOfAyahs: Surah) {
        ayahsList = newListOfAyahs
        notifyDataSetChanged()

    }


}