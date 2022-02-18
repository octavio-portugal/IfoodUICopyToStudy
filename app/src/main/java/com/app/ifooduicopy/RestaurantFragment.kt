package com.app.ifooduicopy

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import co.tiagoaguiar.atway.ui.adapter.ATAdapter
import com.app.ifooduicopy.databinding.FragmentRestaurantBinding

class RestaurantFragment : Fragment(R.layout.fragment_restaurant) {

    private var binding: FragmentRestaurantBinding? = null

    private val categoryAdapter = ATAdapter({
        CategoryView(it)
    })

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

        adjustItemFromBinding()

        setCategoryMockItems()
    }

    private fun adjustItemFromBinding() {
        binding?.let {

            it.rvCategory.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            it.rvCategory.adapter = categoryAdapter

            filters.forEach { filter ->
                it.chipGropFilter.addView(filter.toChip(requireContext()))
            }
        }
    }

    private fun setCategoryMockItems() {
        categoryAdapter.items = arrayListOf(
            Category(1, "https://www.ifood.com.br/static/images/categories/market.png", "Mercado", 0xFFB6D048),
            Category(2, "https://www.ifood.com.br/static/images/categories/restaurant.png","Restaurante",0xFFE91D2D),
            Category(3, "https://www.ifood.com.br/static/images/categories/drinks.png","Bebidas", 0xFF6D553),
            Category(4,"https://static-images.ifood.com.br/image/upload/f_auto/webapp/landingV2/express.png","Express",0xFFFF0000),
            Category(5,"https://parceiros.ifood.com.br/static/media/salad.9db040c0.png","Saudável",0xFFE91D2D),
            Category(6,"https://www.ifood.com.br/static/images/categories/drinks.png","Salgado",0xFF8C60C5),
        )
    }
}
