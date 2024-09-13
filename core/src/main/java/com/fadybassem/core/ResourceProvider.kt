package com.fadybassem.core

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ResourceProvider @Inject constructor(
    @ApplicationContext private val context: Context,
) {

    fun getString(resID: Int): String {
        return context.getString(resID)
    }

    fun getString(resID: Int, vararg formatArgs: Any): String {
        return context.getString(resID, *formatArgs)
    }

    fun getStringArray(resID: Int): Array<String> {
        return context.resources.getStringArray(resID)
    }
}
