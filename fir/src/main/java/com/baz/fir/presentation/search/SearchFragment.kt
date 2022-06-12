package com.baz.fir.presentation.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.baz.fir.R
import com.baz.fir.databinding.FragmentSearchBinding
import com.baz.fir.presentation.activity.MainActivityViewModel
import com.baz.fir.presentation.home.HomeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var mBinding: FragmentSearchBinding

//    private val viewModel: MainActivityViewModel by activityViewModels()
    private lateinit var adapter: HomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding =FragmentSearchBinding.inflate(layoutInflater)
        return  mBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObserver()
        initRV()
        initListeners()
    }

    private fun initObserver() {
        lifecycleScope.launch {

        }
    }

    private fun initRV() {
        adapter = HomeAdapter()
        mBinding.rvSearch.adapter = adapter
    }

    private fun initListeners() {
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
//            viewModel.doSearching(it.toString())
        }
    }

}