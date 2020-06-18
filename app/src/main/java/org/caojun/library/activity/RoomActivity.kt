package org.caojun.library.activity

import android.content.pm.ApplicationInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.socks.library.KLog
import org.caojun.room.App
import org.caojun.room.AppDatabase
import org.caojun.room.AppSortComparator
import org.caojun.room.CacheUtils
import org.jetbrains.anko.doAsync
import java.util.*

class RoomActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        doAsync {
            val list = ArrayList<App>()
            val packages = packageManager.getInstalledPackages(0)
            for (i in packages.indices) {
                val packageInfo = packages[i]
                if ((packageInfo.applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM) == 0) { //非系统应用
                    if (packageInfo.packageName == packageName) {
                        //不把自身应用加载到列表中
                        continue
                    }
                    // AppInfo 自定义类，包含应用信息
                    val app = App()
                    app.name = packageInfo.applicationInfo.loadLabel(packageManager).toString()//获取应用名称
                    app.packageName = packageInfo.packageName //获取应用包名，可用于卸载和启动应用
//                        app.setVersionName(packageInfo.versionName)//获取应用版本名
//                        app.setVersionCode(packageInfo.versionCode)//获取应用版本号
                    app.icon = packageInfo.applicationInfo.loadIcon(packageManager)//获取应用图标
                    app.cache = CacheUtils.getFolderSize(app.packageName)
                    list.add(app)
                }
            }

            Collections.sort(list, AppSortComparator())

            for (i in list.indices) {
                KLog.d(i.toString(), list[i].name)
                AppDatabase.getDatabase(this@RoomActivity).getDao().insert(list[i])
            }

        }
    }
}