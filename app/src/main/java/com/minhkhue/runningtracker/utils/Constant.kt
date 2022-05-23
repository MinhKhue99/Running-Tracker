package com.minhkhue.runningtracker.utils

import android.graphics.Color

object Constant {
    const val APP_DATABASE_NAME = "running_tracker.db"
    const val REQUEST_CODE_LOCATION = 9208
    const val ACTION_START_OR_RESUME_SERVICE = "ACTION_START_OR_RESUME_SERVICE"
    const val ACTION_PAUSE_SERVICE = "ACTION_PAUSE_SERVICE"
    const val ACTION_SHOW_TRACKING_FRAGMENT = "ACTION_SHOW_TRACKING_FRAGMENT"

    const val LOCATION_UPDATE_INTERVAL = 5000L
    const val FASTEST_LOCATION_INTERVAL = 2000L

    const val POLYLINE_COLOR = Color.RED
    const val POLYLINE_WIDTH = 8f
    const val MAP_ZOOM = 15f

    const val TIME_UPDATE_INTERVAL = 50L

    const val ACTION_STOP_SERVICE = "ACTION_STOP_SERVICE"
    const val NOTIFICATION_CHANNEL_ID = "TRACKING_CHANNEL"
    const val NOTIFICATION_CHANNEL_NAME = "TRACKING"
    const val NOTIFICATION_ID = 1

    const val SHARED_PREFERENCES_NAME = "sharedPref"
    const val KEY_FIRST_TIME_TOGGLE = "KEY_FIRST_TIME_TOGGLE"
    const val KEY_NAME = "KEY_NAME"
    const val KEY_WEIGHT = "KEY_WEIGHT"

    const val CANCEL_TRACKING_DIALOG_TAG = "CancelDialog"

    const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"
    const val RECOMMENDATION_END_POINT = "random.php"
    const val CATEGORY_END_POINT = "categories.php"
    const val FILTER_END_POINT = "filter.php?"
    const val MEAL_DETAIL_ENDPOINT = "lookup.php?"
    const val SEARCH_MEAL_BY_NAME = "search.php?s="

    const val NEWS_API_KEY = "0d33a2cdc3c247f3998ca9fa3ee32abb"
    const val NEWS_BASE_URL = "https://newsapi.org/"
    const val QUERY_PAGE_SIZE = 20
}