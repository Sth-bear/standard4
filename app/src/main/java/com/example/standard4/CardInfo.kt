package com.example.standard4

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CardInfo(val name: String, val number: String, val date: String, val price: String): Parcelable
