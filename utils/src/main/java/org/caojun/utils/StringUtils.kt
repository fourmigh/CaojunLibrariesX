package org.caojun.utils

import android.content.Context
import java.lang.StringBuilder

object StringUtils {

    /**
     * 生成复制后的文件名
     * @param context
     * @param originalName 原文件名
     * @param names 所有文件名
     */
    fun getCopyName(context: Context, originalName: String, names: ArrayList<String>): String {
        val array = names.toTypedArray()
        return getCopyName(context, originalName, array)
    }

    /**
     * 生成复制后的文件名
     * @param context
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

    /**
     * 简略显示字符串数组
     * @param context
     * @param texts 字符串ArrayList
     * @param maxNumber 显示最大个数（数组超过maxNumber时最后加上“等/and so on”）
     */
    fun mergeStringArray(context: Context, texts: ArrayList<String>, maxNumber: Int = 2): String {
        val array = texts.toTypedArray()
        return mergeStringArray(context, array, maxNumber)
    }

    /**
     * 简略显示字符串数组
     * @param context
     * @param texts 字符串数组
     * @param maxNumber 显示最大个数（数组超过maxNumber时最后加上“等/and so on”）
     */
    fun mergeStringArray(context: Context, texts: Array<String>, maxNumber: Int = 2): String {
        if (texts.isEmpty()) {
            return ""
        }
        val sb = StringBuilder()

        for (i in texts.indices) {
            if (sb.isNotEmpty()) {
                sb.append(context.getString(R.string.punctuation_mark))
            }
            sb.append(texts[i])
            if (i >= maxNumber - 1) {
                sb.append(context.getString(R.string.and_so_on))
                break
            }
        }

        return sb.toString()
    }
}