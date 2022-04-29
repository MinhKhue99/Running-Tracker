package com.minhkhue.runningtracker.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Categories(
    var strCategory: String? = null,
    var strCategoryThumb: String? = null,
    var strCategoryDescription: String? = null
) : Parcelable
