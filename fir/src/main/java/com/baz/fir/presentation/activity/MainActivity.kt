package com.baz.fir.presentation.activity

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.baz.fir.databinding.ActivityMainBinding
import com.baz.fir.util.Utils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private val mViewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isTabletOrDevice=Utils.isTablet(this@MainActivity)
        requestedOrientation = if (isTabletOrDevice){
            ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR
        }else{
            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        Log.d("TAG", "onCreate: "+Utils.isEmulator())


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