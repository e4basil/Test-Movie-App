package com.baz.diagonalmovieapp.presentation.search

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.baz.diagonalmovieapp.R
import com.baz.diagonalmovieapp.databinding.FragmentSearchListingBinding
import com.baz.diagonalmovieapp.domain.model.ContentItem
import com.baz.diagonalmovieapp.presentation.activity.MainActivityViewModel
import com.baz.diagonalmovieapp.presentation.home.HomeAdapter
import com.baz.diagonalmovieapp.presentation.home.HomeListingFragment
import com.baz.diagonalmovieapp.util.SearchState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var mBinding: FragmentSearchListingBinding

    private val viewModel: MainActivityViewModel by activityViewModels()
    private lateinit var adapter: HomeAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = FragmentSearchListingBinding.inflate(inflater)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpObserver()
        setupRecyclerview()
        setupListeners()
    }

    private fun setUpObserver() {
        lifecycleScope.launch {
            viewModel.searchList
                .flowWithLifecycle(lifecycle = lifecycle, Lifecycle.State.RESUMED)
                .collect {
                    when (it) {

                        is SearchState.onEmpty -> {
                            mBinding.tvNoResult.visibility = View.GONE
                            mBinding.rvSearch.visibility = View.GONE
                        }
                        is SearchState.onNoResult -> {
                            mBinding.tvNoResult.visibility = View.VISIBLE
                            mBinding.rvSearch.visibility = View.GONE

                        }
                        is SearchState.onLoading -> {

                        }
                        is SearchState.onSuccess -> {
                            mBinding.tvNoResult.visibility = View.GONE
                            mBinding.rvSearch.visibility = View.VISIBLE
                            (mBinding.rvSearch.adapter as HomeAdapter).submitList(it?.data)
                            adapter.notifyDataSetChanged()
                        }
                        is SearchState.onFailure -> {
                            mBinding.tvNoResult.visibility = View.VISIBLE
                            mBinding.rvSearch.visibility = View.GONE
                        }
                    }
                }
        }
    }

    private fun setupListeners() {

        mBinding.ivCancel.setOnClickListener {
            mBinding.etSearch.setText("")
            mBinding.tvNoResult.visibility = View.GONE
        }
        mBinding.etSearch.requestFocus();
        mBinding.etSearch.doAfterTextChanged {
            if (it.toString() != "") {
                mBinding.ivCancel.visibility = View.VISIBLE
                mBinding.tvNoResult.visibility = View.VISIBLE
            } else {
                mBinding.ivCancel.visibility = View.GONE
                mBinding.tvNoResult.visibility = View.GONE
            }
            viewModel.doSearching(it.toString())
        }

    }

    private fun setupRecyclerview() {
        mBinding.rvSearch.addItemDecoration(object : RecyclerView.ItemDecoration() {
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

        adapter = HomeAdapter(listener = object : HomeListingFragment.OnItemClickListener {
            override fun onItemClick(item: ContentItem) {
//                findNavController().navigate(R.id.searchFragment)
            }
        })
        mBinding.rvSearch.adapter = adapter

    }
}