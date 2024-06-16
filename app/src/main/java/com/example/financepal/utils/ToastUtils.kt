package com.example.financepal.utils

import android.content.Context
import android.widget.Toast
import com.example.myapplication.BuildConfig

object ToastUtils {
    fun showShortToast(mContext: Context, msg : String){
        if(BuildConfig.DEBUG){
            Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show()
        }

    }

    fun showLongToast(mContext: Context, msg : String){
        if(BuildConfig.DEBUG){
            Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show()
        }

    }

}