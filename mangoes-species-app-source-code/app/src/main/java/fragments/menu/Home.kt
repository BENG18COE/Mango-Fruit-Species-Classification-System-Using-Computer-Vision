package fragments.menu

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.mangoesquality.databinding.HomeFragmentBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.runOnUiThread
import org.json.JSONObject


class Home: Fragment() {

    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    lateinit var locationManager: LocationManager
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private val  REQUEST_LOCATION = 1
    private var latitude: Double = 0.00
    private var longitude: Double = 0.00

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity())


        if(ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            activity?.requestPermissions(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
                ), REQUEST_LOCATION
            )
        }

//        doAsync {
//
//            fusedLocationProviderClient.lastLocation.addOnSuccessListener {
//
//
//                doAsync {
//
//                    val client = OkHttpClient()
//                    val request = Request.Builder()
//                        .url("https://dark-sky.p.rapidapi.com/${it.latitude},${it.longitude}?units=auto&lang=en")
//                        .get()
//                        .addHeader("X-RapidAPI-Host", "dark-sky.p.rapidapi.com")
//                        .addHeader("X-RapidAPI-Key", "BqpBS9gXgcmshFXGa5h0xEU32EYQp1TVRzXjsnO6CQIcdyfn1F")
//                        .build()
//
//                    val response = client.newCall(request).execute()
//
//                    val weatherData = JSONObject(response.body!!.string())
//                    val currentWeather = JSONObject(weatherData.get("currently").toString())
//
//                    runOnUiThread {
//                        binding.temperature.text = currentWeather.getString("temperature") as String
//                        binding.windValue.text = currentWeather.getString("windSpeed")
//                        binding.humidValue.text = currentWeather.getString("humidity")
//                    }
//                    Log.d("Weather Data", "${currentWeather}")
//                }
//
//
//            }
//        }


//        fusedLocationProviderClient.lastLocation.addOnSuccessListener {
//            Log.d("Weather Data", "${it.longitude}")
//            doAsync {
//                val client = OkHttpClient()
//

//
//            }
//        }



    }
}