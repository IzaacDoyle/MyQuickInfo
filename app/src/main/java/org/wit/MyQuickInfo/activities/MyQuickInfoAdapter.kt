package org.wit.MyQuickInfo;

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.card_posts.view.*
import org.wit.MyQuickInfo.Helpers.readImage
import org.wit.MyQuickInfo.Helpers.readImageFromPath
import org.wit.MyQuickInfo.Helpers.showImagePicker


import org.wit.MyQuickInfo.models.MyQuickInfoModel


interface MyQuickListener {
    fun onPlacemarkClick(myQuickInfo: MyQuickInfoModel)
}

class MyQuickInfoAdapter constructor(private var myQuickInfos: List<MyQuickInfoModel>,
                                   private val listener: MyQuickListener) : RecyclerView.Adapter<MyQuickInfoAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(LayoutInflater.from(parent?.context).inflate(R.layout.card_posts, parent, false))
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val myquick = myQuickInfos[holder.adapterPosition]
        holder.bind(myquick, listener)
    }

    override fun getItemCount(): Int = myQuickInfos.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(myQuickInfo: MyQuickInfoModel, listener : MyQuickListener) {
            //sets the text and image to the out put display
            itemView.PostTitle.text= myQuickInfo.title
            itemView.description.text = myQuickInfo.description
            itemView.link.text = myQuickInfo.link

            itemView.Myquick_image.setImageBitmap(readImageFromPath(itemView.context, myQuickInfo.image))
           // itemView.Myquick_image.setImageBitmap(readImage(this, resultCode, data)) = myQuickInfo.image

            itemView.setOnClickListener { listener.onPlacemarkClick(myQuickInfo) }
        }
    }
}
