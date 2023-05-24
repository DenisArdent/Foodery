package com.denisardent.foodery.tabs.views

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.denisardent.foodery.R
import com.denisardent.foodery.databinding.FragmentInfoProfileBinding

class InfoProfileFragment:Fragment(R.layout.fragment_info_profile) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentInfoProfileBinding.bind(view)
    }
}