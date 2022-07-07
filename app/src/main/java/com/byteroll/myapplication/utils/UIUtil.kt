package com.byteroll.myapplication.utils

import android.app.Activity
import android.graphics.Color
import android.view.View

class UIUtil {
    companion object{
        fun setUIFlags(activity: Activity){
            with(activity){
                window.statusBarColor = Color.TRANSPARENT
                window.decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            }
        }
    }
}