package com.baz.diagonalmovieapp.presentation.activity

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baz.diagonalmovieapp.domain.model.Response
import com.baz.diagonalmovieapp.domain.repository.MoviesRepository
import com.baz.diagonalmovieapp.util.HomeState
import com.baz.diagonalmovieapp.util.Resource
import com.baz.diagonalmovieapp.util.SearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private var repository: MoviesRepository
) : ViewModel() {

    /**
     * search
     */

    private val mutableSearchList = MutableStateFlow<SearchState>(SearchState.onEmpty)
    val searchList: StateFlow<SearchState> = mutableSearchList
    private var searchJob: Job? = null

    /**
     * listing
     */

    private val mutableListingData = MutableStateFlow<HomeState>(HomeState.onEmpty)
    val listingData: StateFlow<HomeState> = mutableListingData

    private var page = 1
    private var listingPaginationData: Response? = null

    init {
        getList()
    }

    fun getList() {
        if (page > 3) {
            return
        }

        viewModelScope.launch(dispatcher) {

            when (val response = repository.getMovieListing(page)) {
                is Resource.Success -> {

                    if (listingPaginationData == null) {
                        listingPaginationData = response.data
                    } else {
                        val oldArticles = listingPaginationData?.page?.contentItems?.content
                        val newArticles = response.data?.page?.contentItems?.content

                        Log.d(
                            "TAG",
                            "oldArticles: " + oldArticles?.size + "..newArticles.. " + newArticles?.size
                        )
                        oldArticles?.addAll(newArticles!!)
                    }

                    mutableListingData.value =
                        HomeState.onSuccess(listingPaginationData!!)

//                    if (page==3)
//                        return@launch
                    page++

                }

                else -> Unit

            }

        }
    }


    fun <T> merge(first: List<T>, second: List<T>): List<T> {
        val list: MutableList<T> = ArrayList()
        list.addAll(first)
        list.addAll(second)
        return list
    }

    fun doSearching(queryData: String) {
        if (queryData.isEmpty() || queryData.length <= 2) {
            mutableSearchList.value = SearchState.onNoResult
            return
        }
        // cancel previous searching
        searchJob?.cancel()

        searchJob = viewModelScope.launch {
            delay(500L)

            val searchList = listingPaginationData?.page?.contentItems?.content?.filter {
                it.name.lowercase() == queryData.lowercase() || it.name.lowercase()
                    .contains(queryData.lowercase())
            }
            if (searchList?.isEmpty() == true) {
                mutableSearchList.value = SearchState.onNoResult
            } else {
                mutableSearchList.value = SearchState.onSuccess(searchList)
            }
        }

    }
}