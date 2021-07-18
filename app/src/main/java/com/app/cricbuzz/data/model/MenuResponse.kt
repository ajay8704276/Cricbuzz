package com.app.cricbuzz.data.model

import com.google.gson.annotations.SerializedName


data class MenuResponse(
    @SerializedName("menus") var menus: List<Menus>
)

data class Menus(
    @SerializedName("restaurantId") var restaurantId: Int,
    @SerializedName("categories") var categories: List<Categories>
)

data class Categories(
    @SerializedName("id") var id: String,
    @SerializedName("name") var name: String,
    @SerializedName("menu-items") var menu_items: List<MenuItems>
)

data class MenuItems(
    @SerializedName("id") var id: String,
    @SerializedName("name") var name: String,
    @SerializedName("description") var description: String,
    @SerializedName("price") var price: String,
    @SerializedName("images") var images: List<String>
)
