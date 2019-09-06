package org.wit.MyQuickInfo.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class MyQuickInfoModel (var id: Long =0,
                             var title: String = "",
                             var description: String = "",
                            var link: String = "",
                            // var date: String = "",
                             var image: String = ""
) : Parcelable {
}