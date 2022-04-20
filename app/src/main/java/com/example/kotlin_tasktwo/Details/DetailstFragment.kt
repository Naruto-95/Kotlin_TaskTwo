package com.example.kotlin_tasktwo.Details

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.kotlin_tasktwo.R
import com.example.kotlin_tasktwo.Repository.DTO.WeatherDTO
import com.example.kotlin_tasktwo.Repository.OnErrorListener
import com.example.kotlin_tasktwo.Repository.OnServerResponse
import com.example.kotlin_tasktwo.Repository.Weather
import com.example.kotlin_tasktwo.databinding.DetailsFragmentBinding
import com.example.kotlin_tasktwo.databinding.TheardFragmentBinding
import com.example.kotlin_tasktwo.utils.*
import com.example.kotlin_tasktwo.viewmodel.AppStateError
import com.google.android.material.snackbar.Snackbar


//2:39

class DetailstFragment : Fragment(),OnServerResponse, OnErrorListener {

    private var _binding: DetailsFragmentBinding? = null
    private val binding: DetailsFragmentBinding
        get(){
            return _binding!!
        }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        LocalBroadcastManager.getInstance(requireContext()).unregisterReceiver(receiver)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
lateinit var currentCityName:String
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view,savedInstanceState)


        LocalBroadcastManager.getInstance(requireContext() )
         .registerReceiver(receiver,IntentFilter(KYA_WEATHER_WAVE))
        val observer = {appStateError: AppStateError -> Errorr(appStateError) }
        OnErrorListener(observer)
       arguments?.getParcelable<Weather>(BUNDLE_WEATHER)?.let {
           currentCityName = it.city.name
           //LoaderWeather(this@DetailstFragment,this@DetailstFragment )
            //   .LoadWeather(it.city.lat, it.city.lon)
           requireActivity().startService(Intent(requireContext(),DetalisService::class.java).apply {
               putExtra(KAY_BUN_LAT,it.city.lat)
               putExtra(KAY_BUN_LON, it.city.lon )
           })



       }

    }

   private fun renderData(weather: WeatherDTO){
       with(binding){
           cityName.text = currentCityName
           cityCoordinates.text = String.format(getString(R.string.city_coordinates),
               weather.info.lat.toString(),
               weather.info.lon.toString(),
               mainView.showSnackBar("Работает",2000))

           temperatureValue.text = weather.fact.temperature.toString()
           feelsLikeValue.text = weather.fact.feelsLike.toString()
       }
       }


// extension-функция
   private fun View.showSnackBar(
        text: String,
        length: Int = Snackbar.LENGTH_INDEFINITE
    ) {
        Snackbar.make(this, text,  length).show()
    }

    companion object {
        const val BUNDLE_WEATHER = "weather"
        fun newInstance(bundle: Bundle): DetailstFragment {
            val fragment = DetailstFragment()
            fragment.arguments = bundle
            return fragment
        }

    }

    override fun onResponse(weather: WeatherDTO) {
        renderData(weather)
    }

fun Errorr(data: AppStateError)=when (data) {

    is AppStateError.ErrorSrv -> {
        binding.loadingLayout.visibility = View.GONE
        Snackbar.make(binding.root, " Сервер не отвечает", Snackbar.LENGTH_LONG).show()
    }

    is AppStateError.ErrorCl -> {
        Snackbar.make(binding.root, " Что то случилось ", Snackbar.LENGTH_LONG).show()
        binding.loadingLayout.visibility = View.GONE

    }
    else -> {}
}
    //Создал на месте ресивер
    val receiver  = object :BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            intent?.let { intent ->
                intent.getParcelableExtra<WeatherDTO>(KYA_WEATHER)?.let {
                    onResponse(it)
                }

            }
        }
        }

    override fun onError(appStateError: AppStateError) {
        Errorr(appStateError)
    }
}



















