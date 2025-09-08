package com.example.islamicapp.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.islamicapp.Adapter.AyahsAdapter
import com.example.islamicapp.Factory.AyahsViewModelFactory
import com.example.islamicapp.R
import com.example.islamicapp.Repository.RepositorySurah
import com.example.islamicapp.ViewModel.AyahsViewModel
import com.example.islamicapp.ViewModel.SharedViewModel
import com.example.islamicapp.databinding.FragmentAyahsBinding
import com.example.islamicapp.databinding.FragmentContinueToReadBinding

class ContinueToReadFragment : Fragment() {
    private lateinit var binding: FragmentContinueToReadBinding
    private  lateinit var  viewModel: AyahsViewModel
    private lateinit var ayahsAdapter: AyahsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentContinueToReadBinding.inflate(inflater, container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val repositorySura= RepositorySurah()
        val ayahsViewModelFactory= AyahsViewModelFactory(repositorySura)
        viewModel= ViewModelProvider(this,ayahsViewModelFactory).get(AyahsViewModel::class.java)
        val number=arguments?.getInt("ID")

        viewModel.getSurah(Integer.parseInt(number.toString()))
        viewModel.ResponseSurah.observe(viewLifecycleOwner,{response->
            if(response.isSuccessful){
                binding.nameOfSurahARInFragmentAyhas.text=response.body()!!.data.name
                binding.nameOfSurahEnglishInFragmentAyhas.text=response.body()!!.data.englishName
                binding.typeSurahInFragmentAyhas.text=response.body()!!.data.revelationType
                binding.countAyahsInFragmentAyhas.text=response.body()!!.data.numberOfAyahs.toString()
                ayahsAdapter=AyahsAdapter(response.body()!!)
                ayahsAdapter.setData(response.body()!!)
                binding.recyclerViewAyahs.adapter=ayahsAdapter

            }
            else{
                Log.d("Response",response.code().toString())
            }
        })

    }
}