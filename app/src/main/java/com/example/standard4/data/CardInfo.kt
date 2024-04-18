package com.example.standard4.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CardInfo(val id : Long, val name: String, val number: String, val date: String, val price: String): Parcelable
