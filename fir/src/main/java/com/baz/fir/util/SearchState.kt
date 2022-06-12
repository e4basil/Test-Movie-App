package com.baz.fir.util


sealed class SearchState {
    object onEmpty : SearchState();
    object onNoResult : SearchState();
    object onLoading: SearchState();
//    class onSuccess(val data: List<ContentItem?>?): SearchState();
//    class onFailure(val error: String): SearchState();
}