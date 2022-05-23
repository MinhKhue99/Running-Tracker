package com.minhkhue.runningtracker.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.minhkhue.runningtracker.model.remote.CategoryResponse
import com.minhkhue.runningtracker.model.remote.MealResponse
import com.minhkhue.runningtracker.repository.RecipeRepository
import com.minhkhue.runningtracker.utils.NetworkHelper
import com.minhkhue.runningtracker.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository,
    private val networkHelper: NetworkHelper
) :
    ViewModel() {

    private val _responseMeals = MutableLiveData<Resource<MealResponse>>()
    val responseMeals: MutableLiveData<Resource<MealResponse>> get() = _responseMeals

    fun getResponseMeals() = viewModelScope.launch {
        _responseMeals.postValue(Resource.loading(null))
        if (networkHelper.isNetworkConnected()) {
           launch(Dispatchers.IO) {
               recipeRepository.getRecommendMeals().let {
                   if (it.isSuccessful) {
                       _responseMeals.postValue(Resource.success(it.body()))
                   } else {
                       _responseMeals.postValue(Resource.error(it.message().toString(), null))
                   }
               }
           }
        } else {
            _responseMeals.postValue(Resource.error("No Internet connected", null))
        }
    }

    private val _responseCategories = MutableLiveData<Resource<CategoryResponse>>()
    val responseCategories: MutableLiveData<Resource<CategoryResponse>> get() = _responseCategories

    fun getResponseCategories() = viewModelScope.launch {
        _responseCategories.postValue(Resource.loading(null))
        if (networkHelper.isNetworkConnected()) {
            launch(Dispatchers.IO) {
                recipeRepository.getAllCategories().let {
                    if (it.isSuccessful) {
                        _responseCategories.postValue(Resource.success(it.body()))
                    } else {
                        _responseCategories.postValue(Resource.error(it.message().toString(), null))
                    }
                }
            }
        } else {
            _responseCategories.postValue(Resource.error("No Internet connected", null))
        }
    }

    private val _responseMealsByCategory = MutableLiveData<Resource<MealResponse>>()
    val responseMealsByCategory: MutableLiveData<Resource<MealResponse>> get() = _responseMealsByCategory

    fun getAllMealByCategory(searchQuery: String) = viewModelScope.launch {
        _responseMealsByCategory.postValue(Resource.loading(null))
        if (networkHelper.isNetworkConnected()) {
            launch(Dispatchers.IO) {
                recipeRepository.getAllMealByCategory(searchQuery).let {
                    if (it.isSuccessful) {
                        _responseMealsByCategory.postValue(Resource.success(it.body()))
                    } else {
                        _responseMealsByCategory.postValue(
                            Resource.error(
                                it.message().toString(),
                                null
                            )
                        )
                    }
                }
            }
        } else {
            _responseMealsByCategory.postValue(Resource.error("No Internet connected", null))
        }
    }

    private val _recipe = MutableLiveData<Resource<MealResponse>>()
    val recipe: LiveData<Resource<MealResponse>> get() = _recipe
    fun getRecipeDetails(mealId:Int) = viewModelScope.launch {
        _recipe.postValue(Resource.loading(null))
        if (networkHelper.isNetworkConnected()) {
            launch(Dispatchers.IO) {
                mealId.let { value ->
                    recipeRepository.getRecipeDetails(value).let {
                        if (it.isSuccessful){
                            _recipe.postValue(Resource.success(it.body()))
                        }else{
                            _recipe.postValue(Resource.error(it.message().toString(),null))
                        }
                    }
                }
            }
        }else{
            _recipe.postValue(Resource.error("No Internet Connection",null))
        }
    }
}