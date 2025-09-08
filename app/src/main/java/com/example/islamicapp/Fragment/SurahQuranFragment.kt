package com.example.islamicapp.Fragment

import android.app.FragmentManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.islamicapp.Adapter.SurahAdapter
import com.example.islamicapp.Factory.SurahQuranViewModelFactory
import com.example.islamicapp.R
import com.example.islamicapp.Repository.RepositorySurah
import com.example.islamicapp.ViewModel.SharedViewModel
import com.example.islamicapp.ViewModel.SurahQuranViewModel
import com.example.islamicapp.databinding.FragmentSurahQuranBinding


class SurahQuranFragment : Fragment() {
    private lateinit var binding: FragmentSurahQuranBinding
    private  lateinit var  viewModel: SurahQuranViewModel
    private lateinit var surahAdapter: SurahAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentSurahQuranBinding.inflate(inflater, container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repositorySura= RepositorySurah()
        val surahQuranViewModelFactory= SurahQuranViewModelFactory(repositorySura)
        viewModel= ViewModelProvider(this,surahQuranViewModelFactory).get(SurahQuranViewModel::class.java)
        viewModel.getListOfSurah()
        viewModel.ResponseListOfSurah.observe(viewLifecycleOwner,{response->
            if(response.isSuccessful){
                surahAdapter= SurahAdapter(response.body()!!)
                surahAdapter.setData(response.body()!!)
                binding.SurahRecyclerView.adapter=surahAdapter
            }
            else{
                Log.d("Response",response.code().toString())
                  }
        })


    }



}