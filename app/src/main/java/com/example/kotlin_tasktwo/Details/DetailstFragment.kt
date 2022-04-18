package com.example.kotlin_tasktwo.Details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kotlin_tasktwo.R
import com.example.kotlin_tasktwo.Repository.DTO.WeatherDTO
import com.example.kotlin_tasktwo.Repository.LoaderWeather
import com.example.kotlin_tasktwo.Repository.OnErrorListener
import com.example.kotlin_tasktwo.Repository.OnServerResponse
import com.example.kotlin_tasktwo.Repository.Weather
import com.example.kotlin_tasktwo.databinding.DetailsFragmentBinding
import com.example.kotlin_tasktwo.viewmodel.AppState
import com.example.kotlin_tasktwo.viewmodel.AppStateError
import com.google.android.material.snackbar.Snackbar


//2:39

class DetailstFragment : Fragment(),OnServerResponse, OnErrorListener {

    private lateinit var binding: DetailsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
lateinit var currentCityName:String
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view,savedInstanceState)
        val observer = {appStateError: AppStateError -> Errorr(appStateError) }
        OnErrorListener(observer)
       arguments?.getParcelable<Weather>(BUNDLE_WEATHER)?.let {
           currentCityName = it.city.name
           LoaderWeather(this@DetailstFragment,this@DetailstFragment )
               .LoadWeather(it.city.lat, it.city.lon)

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
}

    override fun onError(appStateError: AppStateError) =
        Errorr(appStateError)
}

















