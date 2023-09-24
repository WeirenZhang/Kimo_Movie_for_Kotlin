package com.weiren.zhang.movie_kotlin

import android.app.Application
import android.content.Context
import androidx.startup.Initializer
import com.alibaba.android.arouter.launcher.ARouter
//import com.didichuxing.doraemonkit.DoraemonKit
import com.tencent.mmkv.MMKV
import java.util.Collections.emptyList

/**
 * App初始化入口
 */
class AppInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        if (BuildConfig.DEBUG) {
            ARouter.openDebug()
            ARouter.openLog();
        }
        MMKV.initialize(context)
        //DoraemonKit.install(context.applicationContext as Application, "")
        ARouter.init(context.applicationContext as Application)
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}