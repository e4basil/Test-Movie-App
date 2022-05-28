package com.baz.diagonalmovieapp.util

import com.baz.diagonalmovieapp.domain.model.ContentItem
import com.baz.diagonalmovieapp.domain.model.Response

sealed class HomeState {
    object onEmpty : HomeState();
    object onLoading: HomeState();
    class onSuccess(val data: Response):HomeState()
    class onFailure(val error: String):HomeState()
}