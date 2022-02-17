package com.app.ifooduicopy

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.app.ifooduicopy.databinding.FragmentRestaurantBinding

class RestaurantFragment: Fragment(R.layout.fragment_restaurant) {

    private var binding: FragmentRestaurantBinding? = null

    private var filters = arrayOf(
        FilterItem(1, "Ordenar", closeIcon = R.drawable.ic_baseline_keyboard_arrow_down_24),
        FilterItem(2, "Pra retirar", icon = R.drawable.ic_baseline_directions_run_24),
        FilterItem(3, "Entrega grátis"),
        FilterItem(4, "Vale-refeição", closeIcon = R.drawable.ic_baseline_keyboard_arrow_down_24),
        FilterItem(5, "Distancia", closeIcon = R.drawable.ic_baseline_keyboard_arrow_down_24),
        FilterItem(6, "Entrega Parceira"),
        FilterItem(7, "Super restaurante"),
        FilterItem(8, "Filtro", closeIcon = R.drawable.ic_baseline_keyboard_arrow_down_24)
    )


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRestaurantBinding.bind(view)

        binding?.let {
            filters.forEach { filter ->
                it.chipGropFilter.addView(filter.toChip(requireContext()))
            }
        }

    }
}