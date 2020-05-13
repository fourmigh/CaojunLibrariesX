package org.caojun.sinogram

import android.content.Context
import org.caojun.utils.ResourcesUtils

object HanziUtils {

    fun getStringArray(context: Context, hanzi: String): Array<String>? {
        val resId = ResourcesUtils.getStringArrayId(context, hanzi, 0)
        if (resId <= 0) {
            return null
        }
        return try {
            ResourcesUtils.getStringArray(context, resId)
        } catch (e: Exception) {
            null
        }
    }

    private fun getArrayId(context: Context, resName: String, defValue: Int): Int {
        return getResId(context, "array", resName, defValue)
    }

    private fun getResId(context: Context, type: String, resName: String, defValue: Int): Int {
        val value = context.resources.getIdentifier(resName, type, context.packageName)
        return if (value == 0) defValue else value
    }
}