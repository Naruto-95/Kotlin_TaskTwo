package com.example.kotlin_tasktwo.lesson_10

import android.graphics.Color
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.kotlin_tasktwo.R
import com.example.kotlin_tasktwo.databinding.DetailsFragmentBinding
import com.example.kotlin_tasktwo.databinding.FragmentMapsWrapperBinding

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

class MapsFragment : Fragment() {
    private lateinit var map:GoogleMap
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

    private fun addMarkerArray(location:LatLng){
        val marker = setMarker(location,markes.size.toString(),R.drawable.ic_map_pin)
        markes.add(marker)

    }

    private fun setMarker(
        location: LatLng,
        searchText: String,
        resourceId: Int):Marker  {
return map.addMarker(MarkerOptions()
    .position(location)
    .title(searchText)
    .icon(BitmapDescriptorFactory.fromResource(resourceId)))!!

}


    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        map = googleMap
        val sydney = LatLng(-34.0, 151.0)
        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        map.setOnMapLongClickListener {
            addMarkerArray(it)
            drawLine()
        }

    }

    private fun drawLine(){
        var previousBefore:Marker? = null
        markes.forEach{current->
            previousBefore?.let { previous->
                map.addPolyline(PolylineOptions().add(previous.position,current.position).color(
                    Color.GREEN).width(10f))

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
    }
}


