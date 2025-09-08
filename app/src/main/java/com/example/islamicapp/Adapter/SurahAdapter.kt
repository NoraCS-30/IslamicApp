package com.example.islamicapp.Adapter


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.islamicapp.Data.ListOfSurah
import com.example.islamicapp.R
import com.example.islamicapp.ViewModel.SharedViewModel

class SurahAdapter(private var surahList: ListOfSurah) : RecyclerView.Adapter<SurahAdapter.SurahItem>() {

    class SurahItem(itemView : View) :RecyclerView.ViewHolder(itemView){
       val nameOfSurahAR=itemView.findViewById<TextView>(R.id.name_of_surah_ar)
        val nameOfSurahEN=itemView.findViewById<TextView>(R.id.name_Of_surah_english)
        val typeOfSurah=itemView.findViewById<TextView>(R.id.type_of_surah)
        val contOfAyah=itemView.findViewById<TextView>(R.id.count_ayah_of_surah)
        val numberSurah=itemView.findViewById<TextView>(R.id.number_Of_Surah)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SurahItem {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.list_surah,parent,false)
        return SurahItem(view)
    }

    override fun getItemCount(): Int =surahList.data.size

    override fun onBindViewHolder(holder: SurahItem, position: Int) {
        val Surahs=surahList.data[position]
        holder.apply {
            nameOfSurahAR.text=Surahs.name
            nameOfSurahEN.text=Surahs.englishName
            typeOfSurah.text=Surahs.revelationType
            numberSurah.text=Surahs.number.toString()
            contOfAyah.text=Surahs.numberOfAyahs.toString()
       }
        holder.itemView.setOnClickListener {
            val bundle= Bundle()
            bundle.putInt("number",Surahs.number)
            holder.itemView.findNavController().navigate(R.id.action_surahQuranFragment_to_ayahsFragment,bundle)
        }

    }

    fun setData(newListOfSurah: ListOfSurah) {
        surahList = newListOfSurah
        notifyDataSetChanged()

    }

}