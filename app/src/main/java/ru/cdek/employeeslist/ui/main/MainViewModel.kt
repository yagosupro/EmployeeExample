package ru.cdek.employeeslist.ui.main

import androidx.lifecycle.ViewModel
import ru.cdek.employeeslist.data.repository.Repository
import ru.cdek.employeeslist.internal.lazyDeferred

class MainViewModel(
    private val repository: Repository
) : ViewModel() {

    val getSpeciality by lazyDeferred {
        repository.getSpeciality()
    }
}