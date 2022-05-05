package com.example.kotlin_tasktwo.lesson_10

import android.Manifest
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Geocoder
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.kotlin_tasktwo.R
import com.example.kotlin_tasktwo.databinding.DetailsFragmentBinding
import com.example.kotlin_tasktwo.databinding.FragmentMapsWrapperBinding

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.material.snackbar.Snackbar
import java.lang.reflect.InvocationTargetException
import java.util.*
import kotlin.collections.ArrayList
import com.google.android.material.snackbar.Snackbar.make as make1

class MapsFragment : Fragment() {
    private lateinit var map: GoogleMap
    private val markes: ArrayList<Marker> = arrayListOf()

    private var _binding: FragmentMapsWrapperBinding? = null
    private val binding: FragmentMapsWrapperBinding
        get() {
            return _binding!!
        }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }

    private fun addMarkerArray(location: LatLng) {
        val marker = setMarker(location, markes.size.toString(), R.drawable.ic_map_pin)
        markes.add(marker)

    }

    private fun setMarker(
        location: LatLng,
        searchText: String,
        resourceId: Int
    ): Marker {
        return map.addMarker(
            MarkerOptions()
                .position(location)
                .title(searchText)
                .icon(BitmapDescriptorFactory.fromResource(resourceId))
        )!!

    }


    private val callback = OnMapReadyCallback { googleMap ->

        map = googleMap
        val samara = LatLng(53.2, 50.1)
        map.addMarker(MarkerOptions().position(samara).title("Samara"))
        map.moveCamera(CameraUpdateFactory.newLatLng(samara))
        map.setOnMapLongClickListener {
            addMarkerArray(it)
            drawLine()
        }
        map.uiSettings.isZoomControlsEnabled = true
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission
                    .ACCESS_FINE_LOCATION
            )
            == PackageManager.PERMISSION_GRANTED
        )
        map.isMyLocationEnabled = true


    }




    private fun drawLine() {
        var previousBefore: Marker? = null
        markes.forEach { current ->
            previousBefore?.let { previous ->
                map.addPolyline(
                    PolylineOptions().add(previous.position, current.position).color(
                        Color.GREEN
                    ).width(10f)
                )

            }
            previousBefore = current
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapsWrapperBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
        AddressSearch()


    }

    private fun AddressSearch() {
        binding.buttonSearch.setOnClickListener {
            try {
                val searchText = binding.searchAddress.text.toString()
                val geocoder = Geocoder(requireContext())
                val result = geocoder.getFromLocationName(searchText, 1)
                val location = LatLng(result[0].latitude, result[0].longitude)

                map.addMarker(
                    MarkerOptions().position(location)
                        .title(searchText)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_map_marker))
                )
                map.moveCamera(
                    CameraUpdateFactory.newLatLngZoom(
                        location, 10f
                    )

                )
            } catch (e:IndexOutOfBoundsException){
          AlertDialog.Builder(requireContext())
              .setTitle(getString(R.string.Invalid_address))
              .setMessage(getString(R.string.Exact_address))
              .setNegativeButton(getString(R.string.Okey)){
                      dialog, _ -> dialog.dismiss()
              }
              .create()
              .show()



            }catch (e: InvocationTargetException){
                Toast.makeText(requireContext(),"Что-то пошло не так",Toast.LENGTH_LONG).show()
            }


        }

    }
}




