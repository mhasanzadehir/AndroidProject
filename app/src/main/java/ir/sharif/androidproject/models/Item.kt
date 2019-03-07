package ir.sharif.androidproject.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Item(val title: String, val subTitle: String, val image: String) : Parcelable
