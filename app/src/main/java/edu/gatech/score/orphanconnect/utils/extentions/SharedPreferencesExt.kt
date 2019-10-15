package edu.gatech.score.orphanconnect.utils.extentions

import android.content.SharedPreferences

fun SharedPreferences.getDouble(key: String, defValue: Double): Double {
    return Double.Companion.fromBits(this.getLong(key, defValue.toRawBits()))
}

fun SharedPreferences.Editor.putDouble(key: String, value: Double): SharedPreferences.Editor {
    return this.putLong(key, value.toRawBits())
}
