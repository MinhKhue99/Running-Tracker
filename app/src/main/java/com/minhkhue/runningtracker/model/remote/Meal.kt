package com.minhkhue.runningtracker.model.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Meal(
    var idMeal: String?,
    var strMeal: String?,
    var strCategory: String?,
    var strArea: String?,
    var strInstructions: String?,
    var strMealThumb: String?,
    var strYoutube: String?,
    var strSource: String?,
    val strIngredient1: String?,
    val strIngredient2: String?,
    val strIngredient3: String?,
    val strIngredient4: String?,
    val strIngredient5: String?,
    val strIngredient6: String?,
    val strIngredient7: String?,
    val strIngredient8: String?,
    val strIngredient9: String?,
    val strIngredient10: String?,
    val strIngredient11: String?,
    val strIngredient12: String?,
    val strIngredient13: String?,
    val strIngredient14: String?,
    val strIngredient15: String?,
    val strIngredient16: String?,
    val strIngredient17: String?,
    val strIngredient18: String?,
    val strIngredient19: String?,
    val strIngredient20: String?,

    val strMeasure1: String?,
    val strMeasure2: String?,
    val strMeasure3: String?,
    val strMeasure4: String?,
    val strMeasure5: String?,
    val strMeasure6: String?,
    val strMeasure7: String?,
    val strMeasure8: String?,
    val strMeasure9: String?,
    val strMeasure10: String?,
    val strMeasure11: String?,
    val strMeasure12: String?,
    val strMeasure13: String?,
    val strMeasure14: String?,
    val strMeasure15: String?,
    val strMeasure16: String?,
    val strMeasure17: String?,
    val strMeasure18: String?,
    val strMeasure19: String?,
    val strMeasure20: String?
) : Parcelable {
    fun getIngredients(): String {
        var ingredients = ""
        if (!strIngredient1.isNullOrEmpty())
            ingredients += "\n \u2022 $strIngredient1"
        if (!strIngredient2.isNullOrEmpty())
            ingredients += "\n \u2022 $strIngredient2"
        if (!strIngredient3.isNullOrEmpty())
            ingredients += "\n \u2022 $strIngredient3"
        if (!strIngredient4.isNullOrEmpty())
            ingredients += "\n \u2022 $strIngredient4"
        if (!strIngredient5.isNullOrEmpty())
            ingredients += "\n \u2022 $strIngredient5"
        if (!strIngredient6.isNullOrEmpty())
            ingredients += "\n \u2022 $strIngredient6"
        if (!strIngredient7.isNullOrEmpty())
            ingredients += "\n \u2022 $strIngredient7"
        if (!strIngredient8.isNullOrEmpty())
            ingredients += "\n \u2022 $strIngredient8"
        if (!strIngredient9.isNullOrEmpty())
            ingredients += "\n \u2022 $strIngredient9"
        if (!strIngredient10.isNullOrEmpty())
            ingredients += "\n \u2022 $strIngredient10"
        if (!strIngredient11.isNullOrEmpty())
            ingredients += "\n \u2022 $strIngredient11"
        if (!strIngredient12.isNullOrEmpty())
            ingredients += "\n \u2022 $strIngredient12"
        if (!strIngredient13.isNullOrEmpty())
            ingredients += "\n \u2022 $strIngredient13"
        if (!strIngredient14.isNullOrEmpty())
            ingredients += "\n \u2022 $strIngredient14"
        if (!strIngredient15.isNullOrEmpty())
            ingredients += "\n \u2022 $strIngredient15"
        if (!strIngredient16.isNullOrEmpty())
            ingredients += "\n \u2022 $strIngredient16"
        if (!strIngredient17.isNullOrEmpty())
            ingredients += "\n \u2022 $strIngredient17"
        if (!strIngredient18.isNullOrEmpty())
            ingredients += "\n \u2022 $strIngredient18"
        if (!strIngredient19.isNullOrEmpty())
            ingredients += "\n \u2022 $strIngredient19"
        if (!strIngredient20.isNullOrEmpty())
            ingredients += "\n \u2022 $strIngredient20"
        return ingredients
    }

    fun getMeasure(): String {
        var measure = ""
        if (!strMeasure1.isNullOrEmpty())
            measure += "\n :  $strMeasure1"
        if (!strMeasure2.isNullOrEmpty())
            measure += "\n :  $strMeasure2"
        if (!strMeasure3.isNullOrEmpty())
            measure += "\n :  $strMeasure3"
        if (!strMeasure4.isNullOrEmpty())
            measure += "\n :  $strMeasure4"
        if (!strMeasure5.isNullOrEmpty())
            measure += "\n :  $strMeasure5"
        if (!strMeasure6.isNullOrEmpty())
            measure += "\n :  $strMeasure6"
        if (!strMeasure7.isNullOrEmpty())
            measure += "\n :  $strMeasure7"
        if (!strMeasure8.isNullOrEmpty())
            measure += "\n :  $strMeasure8"
        if (!strMeasure9.isNullOrEmpty())
            measure += "\n :  $strMeasure9"
        if (!strMeasure10.isNullOrEmpty())
            measure += "\n :  $strMeasure10"
        if (!strMeasure11.isNullOrEmpty())
            measure += "\n :  $strMeasure11"
        if (!strMeasure12.isNullOrEmpty())
            measure += "\n :  $strMeasure12"
        if (!strMeasure13.isNullOrEmpty())
            measure += "\n :  $strMeasure13"
        if (!strMeasure14.isNullOrEmpty())
            measure += "\n :  $strMeasure14"
        if (!strMeasure15.isNullOrEmpty())
            measure += "\n :  $strMeasure15"
        if (!strMeasure16.isNullOrEmpty())
            measure += "\n :  $strMeasure16"
        if (!strMeasure17.isNullOrEmpty())
            measure += "\n :  $strMeasure17"
        if (!strMeasure18.isNullOrEmpty())
            measure += "\n :  $strMeasure18"
        if (!strMeasure19.isNullOrEmpty())
            measure += "\n :  $strMeasure19"
        if (!strMeasure20.isNullOrEmpty())
            measure += "\n :  $strMeasure20"
        return measure
    }
}