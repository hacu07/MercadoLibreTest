package com.example.mercadolibretest.itemDetail.view

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.example.mercadolibretest.R
import com.example.mercadolibretest.itemSearch.dto.Item
import com.example.mercadolibretest.itemSearch.view.MainActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class ItemDetailActivity : AppCompatActivity() {

    companion object{
        const val BUNDLE_NAME = "item.detail.activity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)

        initViews()
    }

    private fun initViews() {
        val item = intent.extras?.getSerializable(MainActivity.EXTRAS_NAME) as Item

        loadHeader(item)
        loadSalesInfo(item)
        loadSellerInfo(item)
        loadAttributes(item)
    }

    private fun loadAttributes(item: Item) {
        val content = findViewById<LinearLayout>(R.id.attibutesContent)
        for (attribute in item?.attributes){
            val textView = TextView(this)
            textView.layoutParams =  LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT)
            textView.setText("${attribute.name} = ${attribute.value_name}")
            content.addView(textView)
        }
    }

    private fun loadSellerInfo(item: Item){
        item.let {
            val sellerProfileItemDetail = findViewById<TextInputEditText>(R.id.sellerProfileItemDetail)
            val ratingPositiveItemDetail = findViewById<TextInputEditText>(R.id.RatingPositiveItemDetail)
            val ratingNegativeItemDetail = findViewById<TextInputEditText>(R.id.RatingNegativeItemDetail)
            val ratingNeutralItemDetail = findViewById<TextInputEditText>(R.id.RatingNeutralItemDetail)
            val tilSellerProfile = findViewById<TextInputLayout>(R.id.tilSellerProfile)

            sellerProfileItemDetail.setText(it!!.seller?.permalink)
            ratingPositiveItemDetail.setText(it!!.seller?.seller_reputation?.transactions?.ratings?.positive?.toString())
            ratingNegativeItemDetail.setText(it!!.seller?.seller_reputation?.transactions?.ratings?.negative?.toString())
            ratingNeutralItemDetail.setText(it!!.seller?.seller_reputation?.transactions?.ratings?.neutral?.toString())

            sellerProfileItemDetail.setOnClickListener {view ->
                val url = Uri.parse(it!!.seller?.permalink)
                val intent = Intent(Intent.ACTION_VIEW, url)
                startActivity(intent)
            }
        }
    }

    private fun loadSalesInfo(item: Item) {
        item.let {
            val qtyAvailableItemDetail = findViewById<TextInputEditText>(R.id.qtyAvailableItemDetail)
            val qtySoldItemDetail = findViewById<TextInputEditText>(R.id.qtySoldItemDetail)

            qtyAvailableItemDetail.setText(it!!.available_quantity.toString())
            qtySoldItemDetail.setText(it!!.sold_quantity.toString())
        }
    }

    private fun loadHeader(item: Item?) {
        item.let {
            val imageItemDetail = findViewById<ImageView>(R.id.imageItemDetail)
            val titleItemDetail = findViewById<TextView>(R.id.titleItemDetail)
            val priceItemDetail = findViewById<TextView>(R.id.priceItemDetail)

            Glide.with(this)
                .load(it!!.thumbnail)
                .error(android.R.drawable.ic_menu_camera)
                .into(imageItemDetail)

            titleItemDetail.setText(it!!.title)
            priceItemDetail.setText("Precio: $"+it!!.price.toInt().toString())

        }
    }
}