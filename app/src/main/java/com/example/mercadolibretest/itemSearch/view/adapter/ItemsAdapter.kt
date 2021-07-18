package com.example.mercadolibretest.itemSearch.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mercadolibretest.R
import com.example.mercadolibretest.itemSearch.dto.Item

class ItemsAdapter(
    private var items: ArrayList<Item>,
    private val listener: OnItemClickListener,
    private val context: Context
) : RecyclerView.Adapter<ItemsAdapter.ItemsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsAdapter.ItemsViewHolder {
        val inflater =  LayoutInflater.from(parent.context).inflate(
                R.layout.product_item,parent,false)
        return ItemsViewHolder(inflater)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ItemsAdapter.ItemsViewHolder, position: Int) {
        return holder.bind(items[position],listener,context)
    }

    fun setItems(items: ArrayList<Item>){
        this.items = items
        notifyDataSetChanged()
    }

    class ItemsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private lateinit var item_image: ImageView
        private lateinit var item_name: TextView
        private lateinit var item_price: TextView
        private lateinit var item_available: TextView
        private lateinit var cvItem: CardView

        init {
            item_image = itemView.findViewById(R.id.item_image)
            item_price = itemView.findViewById(R.id.item_price)
            item_name = itemView.findViewById(R.id.item_name)
            item_available = itemView.findViewById(R.id.item_available)
            cvItem = itemView.findViewById(R.id.cvItem)
        }

        fun bind(item: Item, listener: OnItemClickListener, context: Context) = with(itemView){
            if (!item.thumbnail.isNullOrEmpty()){
                Glide.with(context)
                        .load(item.thumbnail)
                        .error(android.R.drawable.ic_menu_camera)
                        .into(item_image)
            }

            item_name.setText(item.title)
            item_price.setText(item.price.toString())
            item_available.setText(item.available_quantity.toString())

            cvItem.setOnClickListener {
                listener.onClickItem(item)
            }
        }
    }
}