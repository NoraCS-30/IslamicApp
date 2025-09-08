package com.example.islamicapp.Fragment



import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.islamicapp.Factory.HomePageViewModelFactory
import com.example.islamicapp.R
import com.example.islamicapp.Repository.RepositorySurah
import com.example.islamicapp.ViewModel.HomePageViewModel
import com.example.islamicapp.ViewModel.SharedViewModel
import com.example.islamicapp.databinding.FragmentHomeBinding
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private  lateinit var  viewModel: HomePageViewModel
    private val sharedViewModel: SharedViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentHomeBinding.inflate(inflater, container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val repositorySura= RepositorySurah()
        val homeViewModelFactory=HomePageViewModelFactory(repositorySura)
        viewModel= ViewModelProvider(this,homeViewModelFactory).get(HomePageViewModel::class.java)
        getDate()

        binding.layoutIconQuran.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_surahQuranFragment)
        }

        binding.layoutIconDua.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_duaFragment)
        }

        binding.layoutIconTime.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_prayerFragment)
        }
        binding.layoutIconTasbih.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_tasbihFragment)
        }
        binding.go.setOnClickListener {
            val bundle = Bundle()
            var id=sharedViewModel.selectedSurah
            bundle.putInt("ID", id.value!!)
            findNavController().navigate(R.id.action_homeFragment_to_continueToRead, bundle)
        }

        if(sharedViewModel.selectedSurah.value==null){
            lastRead(1)
        }else{
            sharedViewModel.selectedSurah.observe(viewLifecycleOwner) { number ->
             lastRead(number)
            }
        }
    }


    private fun getDate() {
        val dateFormat: DateFormat = SimpleDateFormat(" d MMMM yyyy", Locale("ar"))
        val dayFormat: DateFormat = SimpleDateFormat("EEEE", Locale("ar"))
        val currentDay: String = dayFormat.format(Date())
        val currentDateString: String = dateFormat.format(Date())
        binding.data.text=currentDateString
        binding.day.text=currentDay

    }

    private fun lastRead(id:Int){
        viewModel.getSurah(id)
        viewModel.ResponseSurah.observe(viewLifecycleOwner,{response->
            if(response.isSuccessful){
                binding.SuraAR.text=response.body()?.data?.name
                binding.SuraEN.text=response.body()?.data?.englishName
            }
        })

    }

}