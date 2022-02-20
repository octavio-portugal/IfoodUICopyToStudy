package com.app.ifooduicopy

import android.view.ViewGroup
import co.tiagoaguiar.atway.ui.adapter.ATViewHolder
import com.app.ifooduicopy.databinding.ShopItemBinding
import com.squareup.picasso.Picasso

class ShopView(viewGroup: ViewGroup) : ATViewHolder<Shop, ShopItemBinding>(
    ShopItemBinding::inflate,
    viewGroup
){
    override fun bind(item: Shop) {

        Picasso.get()
            .load(item.shop_url)
            .into(binding.imgShop)

        binding.txtShop.text = item.shop_text
    }
}