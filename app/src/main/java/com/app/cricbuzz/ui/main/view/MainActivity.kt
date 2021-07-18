package com.app.cricbuzz.ui.main.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.cricbuzz.R
import com.app.cricbuzz.data.api.ApiHelper
import com.app.cricbuzz.data.api.RetrofitBuilder
import com.app.cricbuzz.ui.main.adapter.RestaurenAdapter
import com.app.cricbuzz.ui.main.viewmodel.RestaurentViewModel
import com.app.cricbuzz.ui.main.viewmodel.RestaurentViewModelFactory


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: RestaurentViewModel
    private lateinit var adapter: RestaurenAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewModel()
        setupObserver()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.restaurant_search_menu, menu)

        val searchItem: MenuItem = menu!!.findItem(R.id.action_search)
        val searchView: SearchView = searchItem.actionView as SearchView

        searchView.imeOptions = EditorInfo.IME_ACTION_DONE

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }
        })
        return true
    }

    private fun setupObserver() {
        viewModel._restaurentList.observe(this, {
            var restaurant = it
            val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
            recyclerView.setHasFixedSize(true)
            val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
            adapter = RestaurenAdapter(restaurant)
            recyclerView.layoutManager = layoutManager
            recyclerView.adapter = adapter
        })

        viewModel.errorMessage.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })

        viewModel.loading.observe(this, Observer {
            if (it) {
                //show progress dialog
            } else {
                // hide progress dialog
            }
        })

        viewModel.getRestaurantsAndMenu()
    }



    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            RestaurentViewModelFactory(
                ApiHelper(RetrofitBuilder.getRetrofitInstance(this)),
                this
            )
        ).get(RestaurentViewModel::class.java)
    }
}

