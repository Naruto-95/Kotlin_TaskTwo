package com.example.kotlin_tasktwo.Details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kotlin_tasktwo.R
import com.example.kotlin_tasktwo.Repository.Weather
import com.example.kotlin_tasktwo.databinding.DetailsFragmentBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.details_fragment.*


class DetailstFragment : Fragment() {

    private lateinit var binding: DetailsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view,savedInstanceState)
       arguments?.getParcelable<Weather>(BUNDLE_WEATHER)?.let {
               weather -> weather.city.also { city ->
            binding.cityName.text = city.name
            binding.cityCoordinates.text = String.format(
                getString(R.string.city_coordinates),
                city.lat.toString(),
                city.lon.toString(),
                mainView.showSnackBar("Работает",2500)

            )
            binding.temperatureValue.text = weather.temperature.toString()
            binding.feelsLikeValue.text = weather.feelsLike.toString()
       }
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







}




