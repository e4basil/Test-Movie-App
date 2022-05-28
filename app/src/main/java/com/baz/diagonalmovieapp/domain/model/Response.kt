@file:Suppress("DEPRECATED_ANNOTATION")

package com.baz.diagonalmovieapp.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Response(
    val page: Page
) : Parcelable

@Parcelize
data class ContentItem(
    val name: String,
    val posterImage: String
) : Parcelable

@Parcelize
data class Page(
    val pageNum: Int,
    val pageSize: Int,
    val contentItems: ContentItems,
    val totalContentItems: Int,
    val title: String
) : Parcelable

@Parcelize
data class ContentItems(
    val content: MutableList<ContentItem>
) : Parcelable
