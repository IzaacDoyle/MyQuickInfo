package org.wit.MyQuickInfo.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import kotlinx.android.synthetic.main.activity_listposts.*

import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivityForResult
import org.wit.MyQuickInfo.*
import org.wit.MyQuickInfo.main.MainApp
import org.wit.MyQuickInfo.models.MyQuickInfoModel

class MyQuickInfoListActivity : AppCompatActivity(), MyQuickListener {

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listposts)
        app = application as MainApp



        //layout and populate for display
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = MyQuickInfoAdapter(app.myquick.findAll(), this)
        loadMyQuickInfo()

        //enable action bar and set title
        toolbarMain.title = "MyQuickInfo"
        setSupportActionBar(toolbarMain)
    }

    private fun loadMyQuickInfo() {
        showMyQuickInfo(app.myquick.findAll())
    }

    fun showMyQuickInfo (myquick: List<MyQuickInfoModel>) {
        recyclerView.adapter = MyQuickInfoAdapter(myquick, this)
        recyclerView.adapter?.notifyDataSetChanged()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_add -> startActivityForResult<MyQuickInfoActivity>(0)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPlacemarkClick(myQuickInfo: MyQuickInfoModel) {
        startActivityForResult(intentFor<MyQuickInfoActivity>().putExtra("MyQuickInfo_edit", myQuickInfo), 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        loadMyQuickInfo()
        super.onActivityResult(requestCode, resultCode, data)
    }

}