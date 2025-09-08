package com.example.islamicapp.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.islamicapp.R
import com.example.islamicapp.databinding.FragmentSurahQuranBinding
import com.example.islamicapp.databinding.FragmentTasbihBinding

class TasbihFragment : Fragment() {

private lateinit var binding:FragmentTasbihBinding
    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentTasbihBinding.inflate(inflater, container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
           binding.increase.setOnClickListener {
               increase()
           }
           binding.reset.setOnClickListener {
               reset()
           }
    }

    private fun increase(){
        var number=binding.number.text.toString().toInt()
        binding.number.text=(number+1).toString()

    }

    private fun reset(){
        binding.number.text=0.toString()

    }
}