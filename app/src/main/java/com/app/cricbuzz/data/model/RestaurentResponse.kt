package com.app.cricbuzz.data.model

import com.google.gson.annotations.SerializedName

/**
 * Restaurant response data class
 */
data class RestaurentResponse(
    @SerializedName("restaurants") var restaurants: List<Restaurants>
)

data class Restaurants(

    @SerializedName("id") var id: Int,
    @SerializedName("name") var name: String,
    @SerializedName("neighborhood") var neighborhood: String,
    @SerializedName("photograph") var photograph: String,
    @SerializedName("address") var address: String,
    @SerializedName("latlng") var latlng: Latlng,
    @SerializedName("cuisine_type") var cuisineType: String,
    @SerializedName("operating_hours") var operatingHours: OperatingHours,
    @SerializedName("reviews") var reviews: List<Reviews>,
    var menus:Menus

)

data class Latlng(

    @SerializedName("lat") var lat: Double,
    @SerializedName("lng") var lng: Double

)

data class Reviews(

    @SerializedName("name") var name: String,
    @SerializedName("date") var date: String,
    @SerializedName("rating") var rating: Int,
    @SerializedName("comments") var comments: String

)

data class OperatingHours(

    @SerializedName("Monday") var Monday: String,
    @SerializedName("Tuesday") var Tuesday: String,
    @SerializedName("Wednesday") var Wednesday: String,
    @SerializedName("Thursday") var Thursday: String,
    @SerializedName("Friday") var Friday: String,
    @SerializedName("Saturday") var Saturday: String,
    @SerializedName("Sunday") var Sunday: String

)