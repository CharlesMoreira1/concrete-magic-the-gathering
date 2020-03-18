package com.concrete.magicthegathering.feature.listset.presentation.viewmodel

interface ISetViewModel {
    fun fetchListSets(position: Int = 0, isFirstRequest: Boolean = true)
    fun nextSet()
    fun refreshListSets()
}