package org.wit.MyQuickInfo.models

interface MyQuickInfoStore {
    fun findAll(): List<MyQuickInfoModel>
    fun create(myQuickInfo: MyQuickInfoModel)
    fun update(myQuickInfo: MyQuickInfoModel)
    fun delete(myQuickInfo: MyQuickInfoModel)
}
