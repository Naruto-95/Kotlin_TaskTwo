package com.example.kotlin_tasktwo.view

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin_tasktwo.R
import com.example.kotlin_tasktwo.databinding.WeatherListFragmentBinding
import com.example.kotlin_tasktwo.details.DetailstFragment
import com.example.kotlin_tasktwo.details.DetailstFragment.Companion.BUNDLE_WEATHER
import com.example.kotlin_tasktwo.repository.City
import com.example.kotlin_tasktwo.repository.Weather
import com.example.kotlin_tasktwo.viewmodel.AppState
import com.example.kotlin_tasktwo.viewmodel.MainViewModel
import java.util.*


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

    private var isDataSetWorld: Boolean = false

    //Observer, он выполняет метод renderData, как только LiveData обновляет данные,которые она хранит.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.RecyclerView.adapter = adapter
        val observer = { data: AppState -> renderData(data) }
        viewModel.getLiveData().observe(viewLifecycleOwner, observer)
        FubCities()
        FubLocation()

    }


    private fun FubLocation() {
        binding.FloatingActionLocation.setOnClickListener {
            checkPermission()
        }

    }


    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission
                    .ACCESS_FINE_LOCATION
            )
            == PackageManager.PERMISSION_GRANTED
        ) {
            getLocation()
        } else if ((shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION))) {
            explain()
        } else {
            requestPermission()
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {
        context?.let {
            val locationManedger = it.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            if (locationManedger.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                val provaiderGps = locationManedger.getProvider(LocationManager.GPS_PROVIDER)
                provaiderGps?.let {
                    locationManedger.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        10000L,
                        100f,
                        LocationListener
                    )
                }
            }
        }


    }

    fun getAddressByLocation(location: Location) {
        val geocoder = Geocoder(requireContext(), Locale("ru","RU"))
        Thread {
           val addressText =  geocoder.getFromLocation(
                location.latitude,
                location.longitude,
                1000000
            )[0].getAddressLine(0)
            requireActivity().runOnUiThread{
                showAddressDialog(addressText,location)
            }

        }.start()


    }

    private fun showAddressDialog(address: String, location: Location) {
        activity?.let {
            AlertDialog.Builder(it)
                .setTitle(getString(R.string.dialog_address_title))
                .setMessage(address)
                .setPositiveButton(getString(R.string.dialog_address_get_weather)) { _, _ ->
                    onitemClik(
                        Weather(
                            City(
                                address,
                                location.latitude,
                                location.longitude
                            )
                        )
                    )
                }
                .setNegativeButton(getString(R.string.dialog_button_close)) { dialog,
                                                                              _ ->
                    dialog.dismiss()
                }
                .create()
                .show()
        }
    }


    private val LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            Log.d("@@@", location.toString())
            getAddressByLocation(location)
        }
    }

    private fun explain() {
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.dialog_rationale_title))
            .setMessage(getString(R.string.dialog_rationale_meaasge))
            .setPositiveButton(getString(R.string.dialog_rationale_give_access)) { _, _ ->
                requestPermission()
            }
            .setNegativeButton(getString(R.string.dialog_rationale_decline)) { dialog, _ -> dialog.dismiss() }
            .create()
            .show()

    }

    private val REQUEST_CODE = 780
    private fun requestPermission() {
        requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_CODE)

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_CODE) {
            for (i in permissions.indices) {
                if (permissions[i] == Manifest.permission.CALL_PHONE && grantResults[i] ==
                    PackageManager.PERMISSION_GRANTED
                ) {
                    getLocation()
                } else {
                    explain()
                }
            }

        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }

    }


    private fun FubCities() {
        binding.FloatingActionButton.setOnClickListener {
            isRussin = !isRussin
            if (isRussin) {
                viewModel.getWeatherRussianFromLocalSource()
                binding.FloatingActionButton.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_rus
                    )
                )

            } else {
                viewModel.getWeatherWorldFromLocalSource()
                binding.FloatingActionButton.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_earth
                    )
                )
            }

        }
        viewModel.getWeatherRussianFromLocalSource()
    }


    private fun renderData(data: AppState) = when (data) {
        is AppState.Success -> {
            binding.loadingLayout.visibility = View.GONE
            adapter.setData(data.weatherList)
//Snackbar.make(binding.root, "Работает", Snackbar.LENGTH_LONG).show()
        }
        AppState.Loading -> {
            binding.loadingLayout.visibility = View.VISIBLE
        }
        is AppState.Error -> {
            //val throwable = data.error
            binding.loadingLayout.visibility = View.GONE

        }

        else -> {}
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







