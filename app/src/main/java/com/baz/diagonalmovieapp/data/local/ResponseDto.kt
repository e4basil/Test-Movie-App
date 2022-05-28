package com.baz.diagonalmovieapp.data.local

import com.baz.diagonalmovieapp.domain.model.ContentItem
import com.baz.diagonalmovieapp.domain.model.ContentItems
import com.baz.diagonalmovieapp.domain.model.Page
import com.baz.diagonalmovieapp.domain.model.Response
import com.google.gson.annotations.SerializedName

data class ResponseDto(
    @SerializedName("page")
    val page: PageDto
) {
    fun toResponse(): Response {
        return Response(
            page = page.toPage()
        )
    }
}

data class PageDto(
    @SerializedName("content-items")
    val contentItems: ContentItemsDto,
    @SerializedName("page-num")
    val pageNum: Int,
    @SerializedName("page-size")
    val pageSize: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("total-content-items")
    val totalContentItems: Int
) {
    fun toPage(): Page {
        return Page(
            contentItems = contentItems.toContentItems(),
            pageNum = pageNum,
            pageSize = pageSize,
            title = title,
            totalContentItems = totalContentItems
        )
    }
}

data class ContentItemsDto(
    @SerializedName("content")
    val content: List<ContentDto>
) {
    fun toContentItems(): ContentItems {
        return ContentItems(
            content = content.map { it.toContent() } as MutableList<ContentItem>
        )
    }
}

data class ContentDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("poster-image")
    val posterImage: String
) {
    fun toContent(): ContentItem {
        return ContentItem(
            name = name,
            posterImage = posterImage
        )
    }
}