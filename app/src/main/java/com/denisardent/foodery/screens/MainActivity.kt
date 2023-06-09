package com.denisardent.foodery.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import com.denisardent.foodery.App
import com.denisardent.foodery.R
import com.denisardent.foodery.databinding.ActivityMainBinding
import com.denisardent.foodery.utils.ViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private val viewModel: MainViewModel by viewModels{ ViewModelFactory(applicationContext as App) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)

        // showing splash screen until activity get information about user status
        installSplashScreen().apply {
            this.setKeepOnScreenCondition{
                viewModel.state.value?.isLoading ?:true
            }
        }

        //find navController and setup start destination
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        navController = navHostFragment.navController
        val graph = navController.navInflater.inflate(R.navigation.main_nav_graph)
        viewModel.state.observe(this){
            if (!it.isLoading){
                prepareNavGraph(graph, it.isSignedIn)
                setContentView(binding.root)
            }
        }
    }

    private fun prepareNavGraph(graph: NavGraph, isSignedIn: Boolean){
        if (isSignedIn){
            graph.setStartDestination(R.id.tabsFragment)
        } else{
            graph.setStartDestination(R.id.signInFragment)
        }
        navController.graph = graph
    }
}