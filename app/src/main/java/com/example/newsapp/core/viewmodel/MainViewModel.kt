package com.example.newsapp.core.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.core.navigation.Route
import com.example.newsapp.features.onboarding.domain.usecase.OnBoardingUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val onBoardingUseCases: OnBoardingUseCases,

    ) : ViewModel() {


    private val _state = MutableStateFlow(MainAppState())
    val state: StateFlow<MainAppState>
        get() = _state

    init {
        initNavigation()
    }

    private fun initNavigation() {
        viewModelScope.launch {
            val seen = onBoardingUseCases.getOnBoardingUseCases().first()
            if (seen) {
                _state.update {
                    it.copy(initialRoute = Route.NewsLayout.route)
                }
            }
        }
    }
}

data class MainAppState(val initialRoute: String = Route.MainNavigation.route)