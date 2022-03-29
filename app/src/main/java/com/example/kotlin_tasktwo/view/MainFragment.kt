package com.example.kotlin_tasktwo.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.kotlin_tasktwo.R
import com.example.kotlin_tasktwo.databinding.MainFragmentBinding
import com.example.kotlin_tasktwo.view.main.Weather
import com.example.kotlin_tasktwo.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar
import com.example.kotlin_tasktwo.viewmodel.AppState as AppState


class MainFragment : Fragment() {

    lateinit var binding: MainFragmentBinding

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root

        //return inflater.inflate(R.layout.main_fragment, container, false)
    }

    //Observer, он выполняет метод renderData, как только LiveData обновляет данные,которые она хранит.
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer {
            renderData(it)
        })
        viewModel.getWeather()
    }


    /* val observer = Observer<Any> {
            renderData(it as AppState)
        }
        viewModel.getData().observe(viewLifecycleOwner, observer)
    }*/


    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                binding.loadingLayout.visibility = View.GONE
                val weather = appState.weatherData
                setData(weather)
                Snackbar.make(binding.mainView, "Работает", Snackbar.LENGTH_LONG).show()
            }
            AppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                val throwable = appState.error
                Snackbar.make(binding.mainView, "ERROR $throwable", Snackbar.LENGTH_LONG).show()


            }

        }


    }

    private fun setData(weather: Weather) {
        binding.cityName.text = weather.city.name
        binding.cityCoordinates.text = String.format(
            getString(R.string.city_coordinates),
            weather.city.lat.toString(),
            weather.city.lon.toString()
        )
        binding.temperatureValue.text = weather.temperature.toString()
        binding.feelsLikeValue.text = weather.feelsLike.toString()
    }
}


