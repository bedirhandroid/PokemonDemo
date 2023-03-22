package com.bedirhandroid.pokemontechcase.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.ViewModel
import java.lang.reflect.Method

//init generic binding classes for fragment
fun <T> Class<T>.getBindingMethod(): Method {
    return this.getMethod(
        "inflate",
        LayoutInflater::class.java,
        ViewGroup::class.java,
        Boolean::class.java
    )
}
//init dynamic viewModel classes for fragment
fun <VM : ViewModel> Class<VM>.getViewModelByLazy(owner: Fragment): VM {
    return owner.createViewModelLazy(this.kotlin,{owner.viewModelStore}).value
}