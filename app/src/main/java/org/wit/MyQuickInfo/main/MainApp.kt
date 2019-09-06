package org.wit.MyQuickInfo.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.MyQuickInfo.models.MyQuickInfoMemStore
import org.wit.MyQuickInfo.models.MyQuickInfoStore
import org.wit.MyQuickInfo.models.MyQuickJsonStore

class MainApp : Application(), AnkoLogger {

    lateinit var myquick: MyQuickInfoStore

    override fun onCreate() {
        super.onCreate()

        myquick = MyQuickJsonStore(applicationContext)
        myquick = MyQuickInfoMemStore()
        info("Placemark started")




    }

}