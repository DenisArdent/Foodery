package com.denisardent.foodery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.denisardent.foodery.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var isSigned = false
    private var navController: NavController? = null

    private val viewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition{
                viewModel.isInfoReceived.value ?: true
            }
        }
        val binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        Log.d("ACTIVITY CREATED", "${binding.root::class.java}")

        val destination = getDestination(isSigned)
        Log.d("DESTINATION", "$destination")
        val navHost  = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        val navController = navHost.navController

        val graph = navController.navInflater.inflate(R.navigation.main_nav_graph)
        graph.setStartDestination(destination)

        navController.graph = graph
    }

    private fun getDestination(isSigned: Boolean): Int{
        return if (isSigned){
            R.id.tabsFragment
        } else{
            R.id.signInFragment
        }
    }
}