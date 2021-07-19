package com.example.mercadolibretest.itemSearch.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mercadolibretest.R
import com.example.mercadolibretest.itemDetail.view.ItemDetailActivity
import com.example.mercadolibretest.itemSearch.ItemSearchPresenter
import com.example.mercadolibretest.itemSearch.ItemSearchPresenterClass
import com.example.mercadolibretest.itemSearch.dto.Item
import com.example.mercadolibretest.itemSearch.view.adapter.ItemsAdapter
import com.example.mercadolibretest.itemSearch.view.adapter.OnItemClickListener
import com.example.mercadolibretest.utils.Util

class MainActivity : AppCompatActivity(), ItemSearchView, OnItemClickListener,
        SearchView.OnQueryTextListener, SearchView.OnCloseListener {

    companion object{
        const val EXTRAS_NAME = "main.activity.extras"
    }

    private lateinit var svItem: SearchView
    private lateinit var rvItems: RecyclerView
    private lateinit var tvInfoMessage: TextView
    private lateinit var progress_circular: ProgressBar

    private lateinit var mPresenter: ItemSearchPresenter
    private lateinit var mItemsAdapter: ItemsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        mPresenter = ItemSearchPresenterClass(this)
        mPresenter.onCreate()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    private fun initViews() {
        svItem = findViewById(R.id.svItem)
        rvItems = findViewById(R.id.rvItems)
        tvInfoMessage = findViewById(R.id.tvInfoMessage)
        progress_circular = findViewById(R.id.progress_circular)

        rvItems.layoutManager = LinearLayoutManager(this)
        svItem.setOnQueryTextListener(this)
        svItem.setOnCloseListener(this)
    }

    override fun onDestroy() {
        mPresenter.onDestroy()
        super.onDestroy()
    }

    override fun showProgress() {
        progress_circular.visibility = View.VISIBLE
        hideMessageView()
        hideRecycler()
        disableViewElements()
    }

    override fun hideProgress() {
        progress_circular.visibility = View.GONE
        enableViewElements()
    }

    override fun showRecycler() {
        rvItems.visibility = View.VISIBLE
        hideMessageView()
    }

    override fun hideRecycler() {
        rvItems.visibility = View.GONE
    }

    override fun showMessage(message: Int) {
        tvInfoMessage.setText(message)
        tvInfoMessage.visibility = View.VISIBLE
        hideRecycler()
    }

    override fun loadItems(items: ArrayList<Item>) {
        mItemsAdapter = ItemsAdapter(items,this,this)
        rvItems.adapter = mItemsAdapter
        showRecycler()
    }

    override fun clearItems() {
        mItemsAdapter.let {
            it?.setItems(ArrayList())
        }
        showMessage(R.string.empty_text_message)
    }

    override fun hideMessageView() {
        tvInfoMessage.visibility = View.GONE
    }

    override fun showToast(message: Int) {
        Util.showToast(this,message)
    }

    override fun enableViewElements() {
        svItem.isEnabled = true
        rvItems.isEnabled = true
    }

    override fun disableViewElements() {
        svItem.isEnabled = false
        rvItems.isEnabled = false
    }

    override fun onClickItem(item: Item) {
        var bundle = Bundle()
        bundle.putSerializable(ItemDetailActivity.BUNDLE_NAME,item)
        val intent = Intent(this, ItemDetailActivity::class.java).apply {
            putExtra(EXTRAS_NAME,item)
        }
        startActivity(intent)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query.let {
            mPresenter.searchItems(query!!)
        }
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }

    override fun onClose(): Boolean {
        showMessage(R.string.empty_text_message)
        return false
    }
}