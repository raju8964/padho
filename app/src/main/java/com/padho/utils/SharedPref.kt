package com.fdbanks.utils

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class SharedPref private constructor( context: Context) {
    private val sharedPreferences: SharedPreferences
    private val prefsEditor: SharedPreferences.Editor
   var context=context
    init {
        sharedPreferences = context.getSharedPreferences(context.packageName, Activity.MODE_PRIVATE)
        prefsEditor = sharedPreferences.edit()
    }

    fun getValue(key: String?): String? {
        return sharedPreferences.getString(key, "")
    }

    fun setValue(key: String?, value: String?) {
        prefsEditor.putString(key, value)
        prefsEditor.commit()
    }

    companion object {
        private var mInstance: SharedPref? = null
        fun init(context: Context) {
            mInstance = SharedPref(context)
        }
        val instance: SharedPref?
            get() {
                if (mInstance == null) {
                    throw RuntimeException(
                    )
                }
                return mInstance
            }
    }


}