package com.muzadev.travel.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson
import com.muzadev.travel.R
import com.muzadev.travel.api.ApiRepo
import com.muzadev.travel.model.Place
import com.muzadev.travel.presenter.MainView
import com.muzadev.travel.presenter.Presenter
import kotlinx.android.synthetic.main.activity_maps.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, MainView {
    private val placeNames = mutableListOf<String>()
    private lateinit var mMap: GoogleMap
    private var spIndex = 0
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var presenter: Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        presenter = Presenter(this, ApiRepo(), Gson())
        presenter.getPlaces()
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        btnAddMore.setOnClickListener {
            val spinner = Spinner(this)
            spinner.adapter = adapter
            addSpinner(spinner)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-7.792662, 110.367339)

        mMap.addMarker(MarkerOptions().position(sydney).title("Mailoboro Mall"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    private fun addSpinner(sp: Spinner) {
        spinnerContainer.addView(sp, spIndex)
        spIndex++
    }

    override fun showPlaceName(places: List<Place>) {
        for (place in places) {
            placeNames.add(place.name)
        }
        adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, placeNames)
        adapter.notifyDataSetChanged()
    }
}
