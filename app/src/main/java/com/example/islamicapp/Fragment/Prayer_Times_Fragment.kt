package com.example.islamicapp.Fragment


import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.islamicapp.Data.Prayer
import com.example.islamicapp.Factory.PrayerViewModelFactory
import com.example.islamicapp.Repository.RepositoryPrayer
import com.example.islamicapp.ViewModel.PrayerViewModel
import com.example.islamicapp.databinding.FragmentPrayerTimesBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
class Prayer_Times_Fragment : Fragment() {

    private lateinit var binding: FragmentPrayerTimesBinding
    private lateinit var viewModel: PrayerViewModel
    private lateinit var locationClient: FusedLocationProviderClient
    private lateinit var geocoder: Geocoder
    private var lat: Double = 0.0
    private var lon: Double = 0.0
    private var localPrayerData: Prayer? = null
    private var makkahPrayerData: Prayer? = null
    private var madienahPrayerData: Prayer? = null
    private var aqsaPrayerData: Prayer? = null
    private lateinit var Context:Context
   private var addressText:String= ""
    private val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
    private val currentDateString: String = dateFormat.format(Date())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPrayerTimesBinding.inflate(inflater, container, false)
        Context=requireContext()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val repositoryPrayer = RepositoryPrayer()
        val prayerViewModelFactory = PrayerViewModelFactory(repositoryPrayer)
        viewModel = ViewModelProvider(this, prayerViewModelFactory).get(PrayerViewModel::class.java)
        locationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        geocoder = Geocoder(requireContext())

        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            getLocation()
        } else {
            requestPermissions(
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                1001
            )
        }

        viewModel.responsePrayer.observe(viewLifecycleOwner) { response ->
            if (response.isSuccessful) {
               localPrayerData = response.body()
                binding.NameOfPrayer.text = "Fajr"
                binding.TimeOfPrayer.text = localPrayerData?.data?.timings?.Fajr
                 } else {
                Log.d("API", "Error: ${response.errorBody()?.string()}")
            }
        }
        viewModel.getPrayerTimesMakkah(currentDateString,21.422510,39.826168)
        viewModel.responsePrayerMakkah.observe(viewLifecycleOwner) { response ->
            Log.d("ApiMakkah", "Response: ${response.isSuccessful}")
            if (response.isSuccessful) {
                makkahPrayerData = response.body()
                binding.NameOfPrayerMakkah.text="Fajr"
                binding.TimeOfPrayerMakkah.text = makkahPrayerData?.data?.timings?.Fajr
            }
        }

        viewModel.getPrayerTimesMadienah(currentDateString,24.470901,39.612236)
        viewModel.responsePrayerMadienah.observe(viewLifecycleOwner) { response ->
            if (response.isSuccessful) {
                madienahPrayerData = response.body()
                binding.NameOfPrayerMadienah.text="Fajr"
                binding.TimeOfPrayerMadienah.text = madienahPrayerData?.data?.timings?.Fajr
            }
        }

        viewModel.getPrayerTimesAqsa(currentDateString,31.77614,35.23549)
        viewModel.responsePrayerAqsa.observe(viewLifecycleOwner) { response ->
            if (response.isSuccessful) {
               aqsaPrayerData = response.body()
                binding.NameOfPrayerAqsa.text="Fajr"
                binding.TimeOfPrayerAqsa.text = aqsaPrayerData?.data?.timings?.Fajr
                  }
        }

        binding.btnFajr.setOnClickListener {
            binding.NameOfPrayer.text = "Fajr"
            binding.NameOfPrayerMakkah.text="Fajr"
            binding.NameOfPrayerMadienah.text="Fajr"
            binding.NameOfPrayerAqsa.text="Fajr"
            binding.TimeOfPrayer.text = localPrayerData?.data?.timings?.Fajr
            binding.TimeOfPrayerMakkah.text = makkahPrayerData?.data?.timings?.Fajr
            binding.TimeOfPrayerMadienah.text = madienahPrayerData?.data?.timings?.Fajr
            binding.TimeOfPrayerAqsa.text = aqsaPrayerData?.data?.timings?.Fajr
        }


        binding.btnDhahr.setOnClickListener {
            binding.NameOfPrayer.text = "Dhahr"
            binding.NameOfPrayerMakkah.text="Dhahr"
            binding.NameOfPrayerMadienah.text="Dhahr"
            binding.NameOfPrayerAqsa.text="Dhahr"
            binding.TimeOfPrayer.text = localPrayerData?.data?.timings?.Dhuhr
            binding.TimeOfPrayerMakkah.text = makkahPrayerData?.data?.timings?.Dhuhr
            binding.TimeOfPrayerMadienah.text = madienahPrayerData?.data?.timings?.Dhuhr
            binding.TimeOfPrayerAqsa.text = aqsaPrayerData?.data?.timings?.Dhuhr
        }

        binding.btnAsr.setOnClickListener {
            binding.NameOfPrayer.text = "Asr"
            binding.NameOfPrayerMakkah.text="Asr"
            binding.NameOfPrayerMadienah.text="Asr"
            binding.NameOfPrayerAqsa.text="Asr"
            binding.TimeOfPrayer.text = localPrayerData?.data?.timings?.Asr
            binding.TimeOfPrayerMakkah.text = makkahPrayerData?.data?.timings?.Asr
            binding.TimeOfPrayerMadienah.text = madienahPrayerData?.data?.timings?.Asr
            binding.TimeOfPrayerAqsa.text = aqsaPrayerData?.data?.timings?.Asr
        }

        binding.btnMaghrib.setOnClickListener {
            binding.NameOfPrayer.text = "Maghrib"
            binding.NameOfPrayerMakkah.text="Maghrib"
            binding.NameOfPrayerMadienah.text="Maghrib"
            binding.NameOfPrayerAqsa.text="Maghrib"
            binding.TimeOfPrayer.text = localPrayerData?.data?.timings?.Maghrib
            binding.TimeOfPrayerMakkah.text = makkahPrayerData?.data?.timings?.Maghrib
            binding.TimeOfPrayerMadienah.text = madienahPrayerData?.data?.timings?.Maghrib
            binding.TimeOfPrayerAqsa.text = aqsaPrayerData?.data?.timings?.Maghrib
        }

        binding.btnIsha.setOnClickListener {
            binding.NameOfPrayer.text = "Isha"
            binding.NameOfPrayerMakkah.text="Isha"
            binding.NameOfPrayerMadienah.text="Isha"
            binding.NameOfPrayerAqsa.text="Isha"
            binding.TimeOfPrayer.text = localPrayerData?.data?.timings?.Isha
            binding.TimeOfPrayerMakkah.text = makkahPrayerData?.data?.timings?.Isha
            binding.TimeOfPrayerMadienah.text = madienahPrayerData?.data?.timings?.Isha
            binding.TimeOfPrayerAqsa.text = aqsaPrayerData?.data?.timings?.Isha
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {
        val locationRequest = LocationRequest.Builder(
            Priority.PRIORITY_HIGH_ACCURACY, 1000
        ).setMaxUpdates(1).build()

        locationClient.requestLocationUpdates(
            locationRequest,
            object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult) {
                    val location = locationResult.lastLocation
                    if (location != null) {
                        lat = location.latitude
                        lon = location.longitude
                        fetchAddressAndPrayerTimes(lat, lon)
                    } else {
                        Toast.makeText(requireContext(), "فشل تحديد الموقع", Toast.LENGTH_SHORT).show()
                    }
                    locationClient.removeLocationUpdates(this)
                }
            },
            Looper.getMainLooper()
        )
    }

    private fun fetchAddressAndPrayerTimes(lat: Double, lon: Double) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val addressList = geocoder.getFromLocation(lat, lon, 1)
                if (!addressList.isNullOrEmpty()) {
                    val address = addressList[0]
                    val city = address.locality ?: address.subAdminArea ?: address.adminArea ?: "Unknown"
                    val country = address.countryName ?: ""
                    addressText = "$city, $country"

                    withContext(Dispatchers.Main) {
                        binding.Location.text = addressText
                        viewModel.getPrayerTimes(currentDateString, lat, lon)
                    }
                }
            } catch (e: Exception) {
                Log.e("MAIN", "Geocoder error: ${e.message}")
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "خطأ في جلب العنوان", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 1001 &&
            grantResults.isNotEmpty() &&
            grantResults[0] == PackageManager.PERMISSION_GRANTED
        ) {
            getLocation()
        } else {
            Toast.makeText(requireContext(), "تم رفض إذن الموقع", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
        val sharedPreferences = requireContext().getSharedPreferences("UserPreferences", MODE_PRIVATE)
        var Saved=sharedPreferences.getString("Location",null)
        binding.Location.setText(Saved)
    }

    override fun onPause() {
        super.onPause()
        val sharedPreferences = requireContext().getSharedPreferences("UserPreferences", MODE_PRIVATE)
        sharedPreferences.edit{ putString("Location",addressText) }

    }


}
