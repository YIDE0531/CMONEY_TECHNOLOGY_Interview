package com.nuu.cmoney_technology_interview

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.nuu.cmoney_technology_interview.databinding.ActivityThumbnailBinding
import com.nuu.cmoney_technology_interview.viewmodel.ThumbnailViewModel

class ThumbnailActivity : AppCompatActivity() {
    private lateinit var thumbnailViewModel: ThumbnailViewModel
    private lateinit var binding: ActivityThumbnailBinding
    private lateinit var context: Context
    private lateinit var thumbnailAdapter: ThumbnailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThumbnailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        thumbnailViewModel = ViewModelProvider(this).get(ThumbnailViewModel::class.java)
        binding.viewModel = thumbnailViewModel
        binding.lifecycleOwner = this
        context = this

        thumbnailAdapter = ThumbnailAdapter(thumbnailViewModel, context)
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 4)
            adapter = thumbnailAdapter
        }

        thumbnailViewModel.thumbInfoAllArray.observe(this, {
            thumbnailAdapter.updateThumbInfoAllArray(it)
            binding.llProgressBar.visibility = View.GONE
            setRecyclerViewScrollListener()
        })
    }

    private fun setRecyclerViewScrollListener() {
        val scrollListener = object : RecyclerView.OnScrollListener() {
//            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                super.onScrollStateChanged(recyclerView, newState)
//                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//                    if (!binding.recyclerView.canScrollVertically(1)) {
//                        thumbnailAdapter.updateList()
//                        Snackbar.make(binding.recyclerView, "加載中請稍後", Snackbar.LENGTH_SHORT).show()
//                    }
//                    thumbnailAdapter.updateList2()
//                }
////                else if (newState == 1) {
////                    thumbnailAdapter.isSrcoll = true
////                }else if (newState == 2) {
////                    thumbnailAdapter.isSrcoll = true
////                }
//            }

//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//                if (binding.recyclerView.layoutManager is LinearLayoutManager) {
//                    val lastVisibleItem = (binding.recyclerView.layoutManager as LinearLayoutManager?)!!.findLastVisibleItemPosition()
//                    val totalItemCount = binding.recyclerView.adapter!!.itemCount
//                    if (loadMoreListener != null && !isLoadingMore && lastVisibleItem >= totalItemCount -
//                            2 && dy > 0) {
//                        loadMoreListener!!.loadMore()
//                        isLoadingMore = true
//                    }
//                }
//            }
        }
        binding.recyclerView.addOnScrollListener(scrollListener)
    }
}