package com.example.rgblista

import android.graphics.Color

class RGB (val name: String, val red: Int, val green: Int, val blue: Int): java.io.Serializable {

    fun getHexCode(): String {
        return Integer.toHexString(Color.rgb(red, this.green, this.blue))
    }
}