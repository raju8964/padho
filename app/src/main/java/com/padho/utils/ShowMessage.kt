package com.fdbanks.utils

import android.content.Context
import android.widget.Toast
import dagger.hilt.android.qualifiers.ApplicationContext

object  ShowMessage {
     fun message(@ApplicationContext context: Context,value: String) {
        Toast.makeText(
            context,
            "$value",
            Toast.LENGTH_LONG
        ).show()
    }
}