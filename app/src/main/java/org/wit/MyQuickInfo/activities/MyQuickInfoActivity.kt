package org.wit.MyQuickInfo.activities

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_quickinfo.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.MyQuickInfo.Helpers.readImage
import org.wit.MyQuickInfo.Helpers.readImageFromPath
import org.wit.MyQuickInfo.Helpers.showImagePicker
import org.wit.MyQuickInfo.R
import org.wit.MyQuickInfo.main.MainApp
import org.wit.MyQuickInfo.models.MyQuickInfoModel
import java.util.jar.Manifest


class MyQuickInfoActivity : AppCompatActivity(), AnkoLogger {

    var myquick = MyQuickInfoModel()
    lateinit var app: MainApp
    var edit = false
    val IMAGE_REQUEST = 1
    val REQUEST_IMAGE_CAPTURE = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quickinfo)
        app = application as MainApp

        if (intent.hasExtra("Myquick_edit")) {
            edit = true
            myquick = intent.extras.getParcelable<MyQuickInfoModel>("Myquick_edit")
            PostTitle.setText(myquick.title)
            description.setText(myquick.description)
            link.setText(myquick.link)

            btnAdd.setText(R.string.save_Myquick)

            Myquick_image.setImageBitmap(readImageFromPath(this, myquick.image))
            if (myquick.image != null) {
                chooseImage.setText(R.string.select_myquick_image)
            }


        }




        btnAdd.setOnClickListener() {
            //if any extra fields add here
            myquick.title = PostTitle.text.toString()
            myquick.description = description.text.toString()
            myquick.link = link.text.toString()
           // myquick.date = date.text.toString()
            if (myquick.title.isEmpty()) {
                toast(R.string.title)
            } else {
                if (edit) {
                    app.myquick.update(myquick.copy())
                } else {
                    app.myquick.create(myquick.copy())
                }
            }
            info("add Button Pressed: $title")
            setResult(AppCompatActivity.RESULT_OK)
            finish()
        }

        chooseImage.setOnClickListener {
            info("Select image")
            showImagePicker(this, IMAGE_REQUEST)
        }

        Take_image.setOnClickListener {

            toast("camera does not open")

           /*
                Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
                    takePictureIntent.resolveActivity(packageManager)?.also {
                        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)

            */


        }


             /*    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
                    if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
                        val imageBitmap = data.extras.get("data") as Bitmap
                        Myquick_image.setImageBitmap(imageBitmap)
                    }
                }

*/



        toolbarAdd.title = title
        setSupportActionBar(toolbarAdd)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_myquickinfo, menu)
        if (edit && menu != null) menu.getItem(0).setVisible(true)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_delete -> {
                app.myquick.delete(myquick)
                finish()
            }
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            IMAGE_REQUEST -> {
                if (data != null) {
                    myquick.image = data.getData().toString()
                    Myquick_image.setImageBitmap(readImage(this, resultCode, data))
                }
            }
        }
    }
}





