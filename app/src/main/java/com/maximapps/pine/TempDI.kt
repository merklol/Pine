package com.maximapps.pine

import androidx.lifecycle.ViewModel

class VM : ViewModel()

//    val v: VM = daggerViewModel(object : ViewModelProvider.Factory {
//        override fun <T : ViewModel> create(modelClass: Class<T>): T {
//            return super.create(modelClass)
//        }
//    })

//inline fun <reified T : ViewModel> daggerViewModel(factory: ViewModelProvider.Factory) =
//    viewModel(modelClass = T::class.java, factory = factory)

