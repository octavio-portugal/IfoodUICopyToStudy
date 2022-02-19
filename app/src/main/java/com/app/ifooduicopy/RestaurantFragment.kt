package com.app.ifooduicopy

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import co.tiagoaguiar.atway.ui.adapter.ATAdapter
import com.app.ifooduicopy.databinding.FragmentRestaurantBinding

class RestaurantFragment : Fragment(R.layout.fragment_restaurant) {
    private var binding: FragmentRestaurantBinding? = null

    private val categoryAdapter = ATAdapter({ CategoryView(it) })
    private val bannerAdapter = ATAdapter({ BannerView(it) })

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

        setCategoryMockItems()

        setBannerMockItems()

        binding?.let {

            it.rvCategory.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            it.rvCategory.adapter = categoryAdapter

            it.rvBanners.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            it.rvBanners.adapter = bannerAdapter
            it.rvBanners.addOnScrollListener(object : RecyclerView.OnScrollListener(){
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                        if (newState == RecyclerView.SCROLL_STATE_IDLE){
                            notifyPositionChanged(recyclerView)
                        }
                    }
                })

            addDots(it.dots, bannerAdapter.items.size, 0)

            filters.forEach { filter ->
                it.chipGropFilter.addView(filter.toChip(requireContext()))
            }
        }
    }

    private fun addDots(container: LinearLayout, size: Int, position: Int) {
        container.removeAllViews()

        Array(size) {
            val textView = TextView(context).apply {
                text = getString(R.string.dotted)
                textSize = 25f
                setTextColor(
                    if (position == it) ContextCompat.getColor(context, android.R.color.black)
                    else ContextCompat.getColor(context, android.R.color.darker_gray)
                )
            }
            container.addView(textView)
        }
    }

    private val snapHelper =  LinearSnapHelper()
    private var position: Int? = RecyclerView.NO_POSITION

    private fun notifyPositionChanged(recyclerView: RecyclerView) {
        val layoutManager = recyclerView.layoutManager
        val view = snapHelper.findSnapView(layoutManager)
        val currentPosition = if( view == null) RecyclerView.NO_POSITION else layoutManager?.getPosition(view)

        val positionChanged = this.position != currentPosition
            if(positionChanged){
                addDots(binding!!.dots, bannerAdapter.items.size, currentPosition ?: 0)
            }
        this.position = currentPosition
    }

    private fun setCategoryMockItems() {
        categoryAdapter.items = arrayListOf(
            Category(
                1,
                "https://www.ifood.com.br/static/images/categories/market.png",
                "Mercado",
                0xFFB6D048
            ),
            Category(
                2,
                "https://www.ifood.com.br/static/images/categories/restaurant.png",
                "Restaurante",
                0xFFE91D2D
            ),
            Category(
                3,
                "https://www.ifood.com.br/static/images/categories/drinks.png",
                "Bebidas",
                0xFFFF6D553
            ),
            Category(
                4,
                "https://static-images.ifood.com.br/image/upload/f_auto/webapp/landingV2/express.png",
                "Express",
                0xFFFF0000
            ),
            Category(
                5,
                "https://parceiros.ifood.com.br/static/media/salad.9db040c0.png",
                "Saudável",
                0xFFE91D2D
            ),
            Category(
                6,
                "https://www.ifood.com.br/static/images/categories/drinks.png",
                "Salgado",
                0xFF8C60C5
            )
        )
    }

    private fun setBannerMockItems() {
        bannerAdapter.items = arrayListOf(
            Banner(
                1,
                "https://static-images.ifood.com.br/image/upload/t_high/discoveries/itensBasicosNOV21Principal_zE1X.png"
            ),
            Banner(
                2,
                "https://static-images.ifood.com.br/image/upload/t_high/discoveries/Bebidas40offPrincipal_cljA.png"
            ),
            Banner(
                3,
                "https://static-images.ifood.com.br/image/upload/t_high/discoveries/MerceariaeMatinaisPrincipal_mfDO.png"
            )
        )
    }
}

