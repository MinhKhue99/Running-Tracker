package com.minhkhue.runningtracker.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Recipes(
    var idMeal: String? = null,
    var strMeal: String? = null,
    var strCategory: String? = null,
    var strArea: String? = null,
    var strInstructions: String? = null,
    var strMealThumb: String? = null,
    var strYoutube: String? = null,
    var strIngredient: String? = null,
    var strMeasure: String? = null,
    var strSource: String? = null
) : Parcelable
