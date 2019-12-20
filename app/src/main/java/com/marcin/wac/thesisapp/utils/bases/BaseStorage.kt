package com.marcin.wac.thesisapp.utils.bases

import android.content.Context
import com.google.gson.Gson

abstract class BaseStorage(private val context: Context) {
    abstract fun getStorageName(): String

    fun getSessionStorage() = context.getSharedPreferences(getStorageName(), Context.MODE_PRIVATE)

    fun getInt(key: String) = getSessionStorage().getInt(key, 0)
    fun getString(key: String, defValue: String) = getSessionStorage().getString(key, defValue)
    fun getLong(key: String) = getSessionStorage().getLong(key, 0L)
    fun getBoolean(key: String) = getSessionStorage().getBoolean(key, false)

    fun putObject(key: String, obj: Any?) {
        val editor = getSessionStorage().edit()
        val json = Gson().toJson(obj)
        editor.putString(key, json)
        editor.commit()
    }

    inline fun <reified T> getObject(key: String): T? {
        val json = getSessionStorage().getString(key, null)
        return if(json == null) null else Gson().fromJson<T>(json, T::class.java)
    }

    fun putInt(key: String, value: Int) {
        val editor = getSessionStorage().edit()

        editor.putInt(key, value)
        editor.commit()
    }

    fun putLong(key: String, value: Long) {
        val editor = getSessionStorage().edit()

        editor.putLong(key, value)
        editor.commit()
    }

    fun putString(key: String, value: String) {
        val editor = getSessionStorage().edit()

        editor.putString(key, value)
        editor.commit()
    }

    fun putBoolean(key: String, value: Boolean) {
        val editor = getSessionStorage().edit()

        editor.putBoolean(key, value)
        editor.commit()
    }

    fun removeKeys(vararg keys: String) {
        val editor = getSessionStorage().edit()
        keys.forEach { editor.remove(it) }
        editor.commit()
    }
}