package org.wit.MyQuickInfo.models

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class MyQuickInfoMemStore : MyQuickInfoStore, AnkoLogger {


    val myquick = ArrayList<MyQuickInfoModel>()

    override fun findAll(): List<MyQuickInfoModel> {
        return myquick
    }

    override fun create(myQuickInfo: MyQuickInfoModel) {
        myQuickInfo.id = getId()
        myquick.add(myQuickInfo)
        logAll()
    }

    override fun update(myQuickInfo: MyQuickInfoModel) {
        var foundMyQuickInfo: MyQuickInfoModel? = myquick.find { p -> p.id == myQuickInfo.id }
        if (foundMyQuickInfo != null) {
            foundMyQuickInfo.title = myQuickInfo.title
            foundMyQuickInfo.description = myQuickInfo.description
            foundMyQuickInfo.link = myQuickInfo.link
           // foundMyQuickInfo.date =  myQuickInfo.date
            foundMyQuickInfo.image = myQuickInfo.image
            logAll()
        }
    }


    override fun delete(myQuickInfo: MyQuickInfoModel) {
        myquick.remove(myQuickInfo)
    }

    fun logAll() {
        myquick.forEach { info("${it}") }
    }
}
