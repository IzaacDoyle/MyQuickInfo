package org.wit.MyQuickInfo.models

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import org.wit.MyQuickInfo.Helpers.exists
import org.wit.MyQuickInfo.Helpers.read
import org.wit.MyQuickInfo.Helpers.write
import java.util.*

val JSON_FILE = "MyQuickInfo.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<ArrayList<MyQuickInfoModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class MyQuickJsonStore : MyQuickInfoStore, AnkoLogger {
    override fun delete(myQuickInfo: MyQuickInfoModel) {
        myquick.remove(myQuickInfo)
        serialize()
    }

    val context: Context
    var myquick = mutableListOf<MyQuickInfoModel>()

    constructor (context: Context) {
        this.context = context
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<MyQuickInfoModel> {
        return myquick
    }

    override fun create(myQuickInfoModel: MyQuickInfoModel) {
        myQuickInfoModel.id = generateRandomId()
        myquick.add(myQuickInfoModel)
        serialize()
    }


    override fun update(myQuickInfoModel: MyQuickInfoModel) {
        // todo
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(myquick, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        myquick = Gson().fromJson(jsonString, listType)
    }
}