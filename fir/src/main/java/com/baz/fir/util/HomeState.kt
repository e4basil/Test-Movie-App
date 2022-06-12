package com.baz.fir.util

import com.baz.fir.domain.model.ContentItem

sealed class HomeState {
    object onEmpty : HomeState();
    object onLoading : HomeState();
    class onSuccess(val data: List<ContentItem>) : HomeState()
    class onFailure(val error: String) : HomeState()
}