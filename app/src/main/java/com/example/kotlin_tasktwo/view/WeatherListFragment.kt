package com.example.kotlin_tasktwo.view
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin_tasktwo.Details.DetailstFragment
import com.example.kotlin_tasktwo.Details.DetailstFragment.Companion.BUNDLE_WEATHER
import com.example.kotlin_tasktwo.R
import com.example.kotlin_tasktwo.Repository.Weather
import com.example.kotlin_tasktwo.databinding.WeatherListFragmentBinding
import com.example.kotlin_tasktwo.viewmodel.AppState
import com.example.kotlin_tasktwo.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.details_fragment.*
import android.view.View as View


class WeatherListFragment : Fragment(), OnItemListClickListiner {

    lateinit var binding: WeatherListFragmentBinding
    private lateinit var viewModel: MainViewModel
    private val adapter = WeatherListAdapter(this)
    private var isRussin = true




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = WeatherListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    //Observer, он выполняет метод renderData, как только LiveData обновляет данные,которые она хранит.
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.RecyclerView.adapter = adapter
        val observer = {data: AppState -> renderData(data)}

        viewModel.getLiveData().observe(viewLifecycleOwner,observer)

        binding.FloatingActionButton.setOnClickListener{
            isRussin = !isRussin
            if(isRussin){
                viewModel.getWeatherRussianFromLocalSource()
                binding.FloatingActionButton.setImageDrawable( ContextCompat.getDrawable(requireContext(),
                    R.drawable.ic_rus))
            }else{
                viewModel.getWeatherWorldFromLocalSource()
                binding.FloatingActionButton.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_earth))
            }

        }
        viewModel.getWeatherRussianFromLocalSource()


    }

    private fun renderData(data: AppState) = when (data) {
        is AppState.Success -> {
            binding.loadingLayout.visibility = View.GONE
            adapter.setData(data .weatherList )
//Snackbar.make(binding.root, "Работает", Snackbar.LENGTH_LONG).show()
        }
        AppState.Loading -> {
            binding.loadingLayout.visibility = View.VISIBLE
        }
        is AppState.Error -> {
            //val throwable = data.error
            binding.loadingLayout.visibility = View.GONE

        }

    }



    companion object {
        fun newInstance() = WeatherListFragment()
    }




    override fun onitemClik(weather: Weather) {
        requireActivity().supportFragmentManager.beginTransaction().add(
            R.id.container,
            DetailstFragment.newInstance(Bundle().apply { putParcelable(BUNDLE_WEATHER, weather) })
        ).addToBackStack("").commit()
    }
    }







