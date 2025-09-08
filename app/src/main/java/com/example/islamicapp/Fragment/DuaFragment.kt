package com.example.islamicapp.Fragment



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.islamicapp.Adapter.DuaAdapter
import com.example.islamicapp.Data.Dua
import com.example.islamicapp.R
import com.example.islamicapp.databinding.FragmentDuaBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DuaFragment : Fragment() {
    private lateinit var binding: FragmentDuaBinding
    private lateinit var duaAdapter: DuaAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentDuaBinding.inflate(inflater, container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val jsonString = loadJSONFromRaw(R.raw.dua)
        val gson = Gson()
        val listType = object : TypeToken<List<Dua>>() {}.type
        val duaList: List<Dua> = gson.fromJson(jsonString, listType)
        duaAdapter = DuaAdapter(duaList)
        binding.DuaRecyclerView.adapter=duaAdapter


   }
   private fun  loadJSONFromRaw(resourceId: Int): String {
        return resources.openRawResource(resourceId).bufferedReader().use { it.readText() }
    }

}