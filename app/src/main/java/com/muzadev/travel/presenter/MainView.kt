package com.muzadev.travel.presenter

import com.muzadev.travel.model.Place

/**
 * Created by zulfakar on 13/10/18.
 * For educational purposes
 */
interface MainView {
    fun showPlaceName(places: List<Place>)
}