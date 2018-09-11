package com.cintory.hyperion.core.internal

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri

/**
 * Created by Cintory on 2018/9/5 14:27
 * Email：Cintory@gmail.com
 */

abstract class EmptyContentProvider : ContentProvider() {

    override fun query(
        uri: Uri?,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        return null
    }

    override fun getType(uri: Uri?): String? {
        return null
    }

    override fun insert(uri: Uri?, values: ContentValues?): Uri? {
        return null
    }

    override fun delete(uri: Uri?, selection: String?, selectionArgs: Array<out String>?): Int {
        return 0
    }

    override fun update(
        uri: Uri?,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        return 0
    }
}