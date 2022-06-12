package com.baz.fir.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.baz.fir.R
import com.baz.fir.databinding.FragmentHomeBinding
import com.baz.fir.presentation.activity.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainActivityViewModel by activityViewModels()
    private lateinit var mAdapter: HomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadRV()
        setupClickEvents()
        setUpObserver()
        viewModel.getList()
    }

    private fun setUpObserver() {

//        viewModel.getData?.observe(viewLifecycleOwner
//        ) { it ->
//            run {
//                (binding.recyclerView.adapter as HomeAdapter).submitList(it)
////                (binding.recyclerView.adapter as HomeAdapter).notifyDataSetChanged()
//            }
//        }

//        lifecycleScope.launch {
//            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
//
//                viewModel.listingData
//                    .collect {
//                        when (it) {
//                            is HomeState.onEmpty -> {
//
//                            }
//                            is HomeState.onLoading -> {
//
//                            }
//                            is HomeState.onSuccess -> {
//                                (binding.recyclerView.adapter as HomeAdapter).submitList(it.data.toList())
//                                (binding.recyclerView.adapter as HomeAdapter).notifyDataSetChanged()
//                            }
//                            is HomeState.onFailure -> {
//                            }
//                        }
//                    }
//
//            }
//        }
    }

    private fun loadRV() {
        mAdapter = HomeAdapter()
        binding.recyclerView.layoutManager = GridLayoutManager(this@HomeFragment.requireContext(), 3)
        binding.recyclerView.adapter = mAdapter
    }

    private fun setupClickEvents() {
        binding.ivBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.ivSearch.setOnClickListener {
            findNavController().navigate(R.id.searchFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}