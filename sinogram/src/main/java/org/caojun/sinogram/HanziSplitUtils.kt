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
}