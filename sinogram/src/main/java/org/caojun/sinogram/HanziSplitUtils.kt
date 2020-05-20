package org.caojun.sinogram

import android.content.Context
import org.caojun.utils.ResourcesUtils

/**
 * 汉字拆分
 */
object HanziSplitUtils {

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

    fun getHanzi(context: Context, vararg hanzis: String): Array<String>? {
        val sb = StringBuffer()
        for (i in hanzis.indices) {
            sb.append(hanzis[i])
        }
        return getStringArray(context, sb.toString())
    }
}