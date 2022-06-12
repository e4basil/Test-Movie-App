package com.baz.fir.util

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.util.DisplayMetrics
import android.util.Log
import com.baz.fir.R
import java.io.IOException
import kotlin.math.log


class Utils {

    companion object {

        fun isEmulator(): Boolean {
            return Build.FINGERPRINT.startsWith("generic")
                    || Build.FINGERPRINT.startsWith("unknown")
                    || Build.MODEL.contains("google_sdk")
                    || Build.MODEL.contains("Emulator")
                    || Build.MODEL.contains("Android SDK built for x86")
                    || Build.MANUFACTURER.contains("Genymotion")
                    || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"))
                    || "google_sdk" == Build.PRODUCT;
        }

        fun isTablet(activityContext: Context): Boolean {
            return if (activityContext.resources.getBoolean(R.bool.isTab)) {
                Log.d("TAG", "isTabletDevice: tablet")
                true
            } else {
                Log.d("TAG", "isTabletDevice: mobile")
                false
            }
        }

         fun isTabletDevice(activityContext: Context): Boolean {

             if(activityContext.resources.getBoolean(R.bool.isTab)) {
                 Log.d("TAG", "isTabletDevice: tablet")
             } else {
                 Log.d("TAG", "isTabletDevice: mobile")
             }

            val device_large = activityContext.resources.configuration.screenLayout and
                    Configuration.SCREENLAYOUT_SIZE_MASK >=
                    Configuration.SCREENLAYOUT_SIZE_LARGE
            if (device_large) {
                val metrics = DisplayMetrics()
                val activity = activityContext as Activity
                activity.windowManager.defaultDisplay.getMetrics(metrics)
                if (metrics.densityDpi == DisplayMetrics.DENSITY_DEFAULT ||
                    metrics.densityDpi == DisplayMetrics.DENSITY_HIGH ||
                    metrics.densityDpi == DisplayMetrics.DENSITY_MEDIUM ||
                    metrics.densityDpi == DisplayMetrics.DENSITY_TV ||
                    metrics.densityDpi == DisplayMetrics.DENSITY_XHIGH) {
                    Log.d("TAG", "isTabletDevice: true")
//                    AppInstance.getLogger().logD("DeviceHelper", "IsTabletDevice-True")
                    return true
                }
            }

            Log.d("TAG", "isTabletDevice: false")
            return false
        }

        fun readJsonDataFromAsset(context: Context, fileName: String): String? {
            val jsonString: String
            try {
                jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
            } catch (ioException: IOException) {
                ioException.printStackTrace()
                return null
            }
            return jsonString
        }


//         fun handleError(context:Context,error: ErrorType): String {
//            return when (error) {
//                ErrorType.ContentUnavailable -> context.resources.getString(R.string.no_results_found)
//                ErrorType.FileError -> context.resources.getString(R.string.file_error)
//                is ErrorType.GenericError -> context.resources.getString(R.string.generic_error)
//            }
//        }
    }
}