package com.app.cricbuzz.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.cricbuzz.R
import com.app.cricbuzz.data.model.Restaurants
import com.app.cricbuzz.utils.snackbar

/**
 * Adapter class to show the data in recycler view
 *
 * Perform search operation based on string input
 *
 */
class RestaurenAdapter(restaurantData: List<Restaurants>) :
    RecyclerView.Adapter<RestaurenAdapter.RestaurantViewHolder>(), Filterable {

    private var restaurantList: MutableList<Restaurants>
    private val restaurantListFull: MutableList<Restaurants>
    private lateinit var view: View

    init {
        this.restaurantList = restaurantData as MutableList<Restaurants>
        restaurantListFull = ArrayList<Restaurants>(restaurantData)
    }


    class RestaurantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var restaurant_name_tv: TextView
        var restaurant_address_tv: TextView
        var restaurant_cuisine_tv: TextView

        init {
            restaurant_name_tv = itemView.findViewById(R.id.rest_name)
            restaurant_address_tv = itemView.findViewById(R.id.rest_address)
            restaurant_cuisine_tv = itemView.findViewById(R.id.rest_cuisine)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        view =
            LayoutInflater.from(parent.context).inflate(R.layout.restarent_item, parent, false)
        return RestaurantViewHolder(view)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val currRestaurant = restaurantList[position]
        holder.restaurant_name_tv.text = currRestaurant.name
        holder.restaurant_address_tv.text = currRestaurant.address
        holder.restaurant_cuisine_tv.text = currRestaurant.cuisineType

    }

    override fun getItemCount(): Int = restaurantList.size


    override fun getFilter(): Filter {
        return restaurantFilter
    }

    /**
     * Performing filtering of object
     *
     * 1 - based on menu items present in menu response then show the restaurant
     *
     * 2 - based on cusine type / restaurant name present in restaurant response
     *
     * then show the restaurant associated with it
     */
    private val restaurantFilter: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList: MutableList<Restaurants> = ArrayList<Restaurants>()
            if (constraint == null || constraint.length == 0) {
                filteredList.addAll(restaurantListFull)
            } else {
                val filterPattern = constraint.toString().toLowerCase().trim { it <= ' ' }
                for (i in 0..restaurantListFull.size - 1) {

                    if (restaurantListFull[i].menus != null) {
                        var categories = restaurantListFull[i].menus.categories
                        for (j in 0..categories.size - 1) {
                            var menuItems = categories[j].menu_items
                            for (k in 0..menuItems.size - 1) {
                                if (menuItems[k].name.toLowerCase().contains(filterPattern)) {
                                    filteredList.add(restaurantListFull[i])
                                    break
                                }
                            }
                            break
                        }
                    }
                    if (restaurantListFull[i].name.toLowerCase().contains(filterPattern) ||
                        restaurantListFull[i].cuisineType.toLowerCase().contains(filterPattern)
                    ) {
                        filteredList.add(restaurantListFull[i])
                    }


                }
            }

            if (filteredList.size == 0) {
                view.snackbar("No Restaurant found")
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        /**
         * Publish the filtered result on main thread
         */
        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            restaurantList.clear()
            restaurantList = if (results == null || results.values == null)
                ArrayList<Restaurants>()
            else
                results.values as MutableList<Restaurants>
            notifyDataSetChanged()
        }
    }
}
