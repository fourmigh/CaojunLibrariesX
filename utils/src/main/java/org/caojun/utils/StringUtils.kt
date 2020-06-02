package org.caojun.utils

import android.content.Context

object StringUtils {

    /**
     * 生成复制后的文件名
     * @param originalName 原文件名
     * @param names 所有文件名
     */
    fun getCopyName(context: Context, originalName: String, names: ArrayList<String>): String {
        val array = names.toTypedArray()
        return getCopyName(context, originalName, array)
    }

    /**
     * 生成复制后的文件名
     * @param originalName 原文件名
     * @param names 所有文件名
     */
    fun getCopyName(context: Context, originalName: String, names: Array<String>): String {
        val copyName = context.getString(R.string.copy_name, originalName)
        var number = 1
        for (i in names.indices) {
            if (number == 1) {
                if (copyName in names) {
                    number ++
                }
            } else {
                val name = context.getString(R.string.copy_name_copy, copyName, number)
                if (name in names) {
                    number ++
                }
            }
        }
        if (number > 1) {
            return context.getString(R.string.copy_name_copy, copyName, number)
        }
        return copyName
    }
}