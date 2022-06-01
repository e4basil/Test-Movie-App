package com.baz.diagonalmovieapp.presentation.home

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.baz.diagonalmovieapp.R
import com.baz.diagonalmovieapp.databinding.FragmentHomeListingBinding
import com.baz.diagonalmovieapp.domain.model.Response
import com.baz.diagonalmovieapp.presentation.activity.MainActivityViewModel
import com.baz.diagonalmovieapp.util.HomeState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeListingFragment : Fragment() {

    private val mViewModel: MainActivityViewModel by activityViewModels()
    private lateinit var mBinding: FragmentHomeListingBinding
    private lateinit var mAdapter: HomeAdapter
    lateinit var mHomeData: Response
    var isLoading = false
    var isLastPage = false
    var isScrolling = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentHomeListingBinding.inflate(inflater, container, false)
        return mBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpObserver()
        setupRecyclerview()
        setupClickEvents()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
    }


    private fun setUpObserver() {

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                mViewModel.listingData
                    .collect {
                        when (it) {
                            is HomeState.onEmpty -> {

                            }
                            is HomeState.onLoading -> {

                            }
                            is HomeState.onSuccess -> {
                                mHomeData = it.data
                                val list = it.data.page.contentItems.content
                                (mBinding.recyclerView.adapter as HomeAdapter).submitList(list)
                                mAdapter.notifyDataSetChanged()
                            }
                            is HomeState.onFailure -> {
                            }
                        }
                    }

            }
        }
    }

    private fun setupRecyclerview() {
        mBinding.recyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                with(outRect) {
                    this.left = 16
                    this.right = 16
                    this.bottom = 90
                }
            }
        })

        mBinding.recyclerView.addOnScrollListener(scrollListener)

        mAdapter = HomeAdapter()
        mBinding.recyclerView.adapter = mAdapter
    }


    private fun setupClickEvents() {
        mBinding.ivBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
        mBinding.ivSearch.setOnClickListener {
            findNavController().navigate(R.id.searchFragment)
        }
    }


    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = recyclerView.layoutManager as GridLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            val isNotLoadingAndNotLastPage = !isLoading && !isLastPage
            val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
            val isNotAtBeginning = firstVisibleItemPosition >= 0

            Log.d("TAG", "onScrolled: " + mHomeData.page.pageSize!!)
            val isTotalMoreThanVisible = totalItemCount >= mHomeData.page.pageSize!!
            val shouldPaginate = isNotLoadingAndNotLastPage && isAtLastItem && isNotAtBeginning &&
                    isTotalMoreThanVisible && isScrolling
            if (shouldPaginate) {
                mViewModel.getList()
                isScrolling = false
            } else {
                mBinding.recyclerView.setPadding(0, 0, 0, 0)
            }
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }
    }
}