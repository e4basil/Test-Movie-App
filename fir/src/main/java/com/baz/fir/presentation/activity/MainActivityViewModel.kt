package com.baz.fir.presentation.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.baz.fir.domain.model.ContentItem
import com.baz.fir.util.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
//    private var repository: MoviesRepository
) : ViewModel() {

    private val mutableListingData = MutableStateFlow<HomeState>(HomeState.onEmpty)
    val listingData: StateFlow<HomeState> = mutableListingData

    private var mutableLiveData: MutableLiveData<List<ContentItem>>? = null
    var getData : LiveData<List<ContentItem>>?=  mutableLiveData

    val content = arrayListOf(
        ContentItem("1","The Birds", "https://randomuser.me/api/portraits/med/men/93.jpg"),
        ContentItem("2","Rear Window", "https://randomuser.me/api/portraits/med/women/93.jpg"),
        ContentItem("3","Family Pot", "https://randomuser.me/api/portraits/med/women/93.jpg"),
        ContentItem("4","Family Pot", "https://randomuser.me/api/portraits/med/women/93.jpg"),
        ContentItem("5","Rear Window", "https://randomuser.me/api/portraits/med/women/93.jpg"),
        ContentItem("6","The Birds", "https://randomuser.me/api/portraits/med/women/93.jpg"),
        ContentItem("7","Rear Window", "https://randomuser.me/api/portraits/med/women/93.jpg"),
        ContentItem("8","Rear Window", "https://randomuser.me/api/portraits/med/women/93.jpg"),
        ContentItem("9","Rear Window", "https://randomuser.me/api/portraits/med/women/93.jpg"),
        ContentItem("10","Rear Window", "https://randomuser.me/api/portraits/med/women/93.jpg"),
        ContentItem("11","Rear Window", "https://randomuser.me/api/portraits/med/women/93.jpg"),
        ContentItem("12","Rear Window", "https://randomuser.me/api/portraits/med/women/93.jpg"),
        ContentItem("13","Rear Window", "https://randomuser.me/api/portraits/med/women/93.jpg"),
        ContentItem("14","Rear Window", "https://randomuser.me/api/portraits/med/women/93.jpg"),
        ContentItem("15","Rear Window", "https://randomuser.me/api/portraits/med/women/93.jpg"),
        ContentItem("16","Rear Window", "https://randomuser.me/api/portraits/med/women/93.jpg"),
        ContentItem("17","Rear Window", "https://randomuser.me/api/portraits/med/women/93.jpg"),
        ContentItem("18","Rear Window", "https://randomuser.me/api/portraits/med/women/93.jpg"),
        ContentItem("19","Rear Window", "https://randomuser.me/api/portraits/med/women/93.jpg"),
        ContentItem("20","Rear Window", "https://randomuser.me/api/portraits/med/women/93.jpg"),

        )

   fun getList(){
       if (mutableLiveData == null) {
           mutableLiveData = MutableLiveData<List<ContentItem>>()
           initMovieList()
       }

//       viewModelScope.launch(dispatcher) {
//           delay(500L)
//
//           mutableListingData.value =
//               HomeState.onSuccess(content)
//       }
    }
    private fun initMovieList() {
//        val movieList: MutableList<ContentItem> = ArrayList<ContentItem>()
//        movieList.add(Movie("Captain America", "8"))
//        movieList.add(Movie("Iron Man", "7"))
//        movieList.add(Movie("Thor", "6"))
        mutableLiveData!!.value = content
    }


    fun doSearching(query: String) {
    }


}