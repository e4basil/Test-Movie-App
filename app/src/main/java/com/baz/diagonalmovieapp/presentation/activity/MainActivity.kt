package com.baz.diagonalmovieapp.presentation.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.baz.diagonalmovieapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private val mViewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

//        init()
    }

    // init
//    private fun init(){
//        setSupportActionBar(mBinding.vwToolbar.toolbar)
//        supportActionBar?.setDisplayShowTitleEnabled(false)
//
//        mBinding.vwToolbar.imageBack.setOnClickListener {
//            onBackPressed()
//        }
//        mBinding.vwToolbar.imageSearch.setOnClickListener {
//            mBinding.navHostFragment.findNavController().navigate(R.id.searchFragment)
//        }
//
//    }
}