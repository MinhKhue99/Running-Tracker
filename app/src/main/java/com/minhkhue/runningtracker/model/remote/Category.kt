package com.minhkhue.runningtracker.model.remote

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    var strCategory: String?,
    var strCategoryThumb: String?,
    var strCategoryDescription: String?
) : Parcelable
