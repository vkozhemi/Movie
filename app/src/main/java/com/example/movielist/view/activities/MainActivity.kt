package com.example.movielist.view.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movielist.R
import com.example.movielist.databinding.ActivityMainBinding
import com.example.movielist.utils.Constants.LOG_TAG


class MainActivity : AppCompatActivity() {

    lateinit var mLinearLayoutManager: LinearLayoutManager
    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mNavController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            Log.d(LOG_TAG, "onCreate")
            mBinding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(mBinding.root)

            mNavController = findNavController(R.id.nav_host_fragment)

            val appBarConfiguration = AppBarConfiguration(
                setOf(
                    R.id.navigation_movie,
                    R.id.navigation_favorite_movie
                )
            )

            setupActionBarWithNavController(mNavController, appBarConfiguration)

            mBinding.navView.setupWithNavController(mNavController)
        } catch (e : Exception) {
            Log.e(LOG_TAG, "Exception: $e")
        }
    }
}