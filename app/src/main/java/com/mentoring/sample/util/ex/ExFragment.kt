package com.mentoring.sample.util.ex

import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

@MainThread
public inline fun <reified VM : ViewModel> Fragment.myViewModels(
    noinline ownerProducer: () -> ViewModelStoreOwner = { this },
    noinline factoryProducer: (() -> VM)? = null
): Lazy<VM> = createViewModelLazy(VM::class, { ownerProducer().viewModelStore }, {
    object: ViewModelProvider.Factory {
        override fun <VM : ViewModel?> create(modelClass: Class<VM>): VM {
            return factoryProducer?.invoke() as VM
        }
    }
})