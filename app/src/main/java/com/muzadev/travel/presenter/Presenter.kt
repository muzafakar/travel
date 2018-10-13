package com.muzadev.travel.presenter

import android.util.Log
import com.google.gson.Gson
import com.muzadev.travel.api.ApiRepo
import com.muzadev.travel.api.PlaceAPI
import com.muzadev.travel.model.Place
import com.muzadev.travel.model.PlaceResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Created by zulfakar on 13/10/18.
 * For educational purposes
 */
class Presenter(private val view: MainView,
                private val apiRepo: ApiRepo,
                private val gson: Gson) {
    fun getPlaces() {
        doAsync {
            val data: List<Place> = gson.fromJson(apiRepo.doRequest(PlaceAPI.getPlaces())
                    , PlaceResponse::class.java).places
            Log.d("Presenter", "${data.size}")

            uiThread {
                view.showPlaceName(data)
            }
        }
    }
}