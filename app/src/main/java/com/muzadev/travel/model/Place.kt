package com.muzadev.travel.model

/**
 * Created by zulfakar on 13/10/18.
 * For educational purposes
 */
data class Place(val name: String, val lat: Long, val lng: Long)

data class PlaceResponse(val places: List<Place>)