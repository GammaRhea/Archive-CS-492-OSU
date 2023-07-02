package com.example.android.roomyweather.ui


import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.viewModels
import androidx.activity.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.roomyweather.BuildConfig
import com.example.android.roomyweather.R
import com.example.android.roomyweather.data.CityDatabaseEntry
import com.google.android.material.navigation.NavigationView


/*
 * To use your own OpenWeather API key, create a file called `gradle.properties` in your
 * GRADLE_USER_HOME directory (this will usually be `$HOME/.gradle/` in MacOS/Linux and
 * `$USER_HOME/.gradle/` in Windows), and add the following line:
 *
 *   OPENWEATHER_API_KEY="<put_your_own_OpenWeather_API_key_here>"
 *
 * The Gradle build for this project is configured to automatically grab that value and store
 * it in the field `BuildConfig.OPENWEATHER_API_KEY` that's used below.  You can read more
 * about this setup on the following pages:
 *
 *   https://developer.android.com/studio/build/gradle-tips#share-custom-fields-and-resource-values-with-your-app-code
 *
 *   https://docs.gradle.org/current/userguide/build_environment.html#sec:gradle_configuration_properties
 *
 * Alternatively, you can just hard-code your API key below 🤷‍.
 */
//const val OPENWEATHER_APPID = BuildConfig.OPENWEATHER_API_KEY

class MainActivity : AppCompatActivity() {
    private val tag = "MainActivity"

    private val viewModel: SavedForecastViewModel by viewModels()

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var drawerRV: RecyclerView
    private lateinit var drawerAdapter: DrawerListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)

        setupActionBarWithNavController(navController, appBarConfiguration)

        findViewById<NavigationView>(R.id.nav_view).setupWithNavController(navController)
        drawerRV = findViewById(R.id.rv_drawer_list)
        drawerAdapter = DrawerListAdapter(::onDrawerItemClick)

        drawerRV.layoutManager = LinearLayoutManager(this)
        drawerRV.setHasFixedSize(true)
        drawerRV.adapter = drawerAdapter

        viewModel.savedCities.observe(this) {
            Log.d(tag, it.toString())
            this.drawerAdapter.updateCityList(it)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        // can't be used in onCreate()
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun onDrawerItemClick(cityEntry: CityDatabaseEntry) {
        Log.d(tag, cityEntry.savedCity)
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this)
        sharedPrefs.edit().putString(getString(R.string.pref_city_key), cityEntry.savedCity).commit()
        recreate()
    }

}