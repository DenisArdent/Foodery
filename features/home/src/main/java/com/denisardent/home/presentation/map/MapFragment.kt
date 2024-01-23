package com.denisardent.home.presentation.map

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.denisardent.home.R
import com.denisardent.home.databinding.FragmentMapBinding
import com.yandex.mapkit.MapKitFactory

class MapFragment: Fragment(R.layout.fragment_map) {
    private lateinit var binding: FragmentMapBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MapKitFactory.initialize(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMapBinding.bind(view)
    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        binding.mapView.onStart()
    }

    override fun onStop() {
        binding.mapView.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }
}