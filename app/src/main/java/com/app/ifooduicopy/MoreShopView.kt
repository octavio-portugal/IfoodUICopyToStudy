package com.app.ifooduicopy

import android.view.ViewGroup
import co.tiagoaguiar.atway.ui.adapter.ATViewHolder
import com.app.ifooduicopy.databinding.MoreShopsItemBinding
import com.squareup.picasso.Picasso

class MoreShopView(viewGroup: ViewGroup) : ATViewHolder<MoreShop, MoreShopsItemBinding>(
    MoreShopsItemBinding::inflate,
    viewGroup
){
    override fun bind(item: MoreShop) {

        Picasso.get()
            .load(item.shop_url)
            .into(binding.imgShop)

        binding.txtShopTitle.text = item.shop_name
        binding.txtStar.text = item.rate.toString()
        binding.txtShopCategoryAndDistance.text = itemView.context.getString(R.string.shop_category_and_distance, item.category, item.distance)
        binding.txtPrice.text = itemView.context.getString(R.string.shop_price, item.time, item.price)
    }
}