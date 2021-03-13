package com.example.core.presentation.ui.map_catalog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.core.presentation.ui.items.PetrolSmallInfoUi
import com.example.ui_components.base.BaseViewModel

class MapCatalogViewModel : BaseViewModel() {

    private val _allPetrols = MutableLiveData<List<PetrolSmallInfoUi>>()
    val allPetrols: LiveData<List<PetrolSmallInfoUi>> = _allPetrols

    fun requestAllPetrols(){
        _allPetrols.postValue(
            listOf(
                PetrolSmallInfoUi(),
                PetrolSmallInfoUi(),
                PetrolSmallInfoUi(),
                PetrolSmallInfoUi(),
                PetrolSmallInfoUi(),
                PetrolSmallInfoUi(),
                PetrolSmallInfoUi()
            )
        )
    }

}