package com.example.core.presentation.di

import com.example.core.presentation.ui.map_catalog.MapCatalogViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val coreViewModelModule = module {

    viewModel { MapCatalogViewModel() }

}