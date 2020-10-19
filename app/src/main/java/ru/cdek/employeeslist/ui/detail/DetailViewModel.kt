package ru.cdek.employeeslist.ui.detail

import androidx.lifecycle.ViewModel
import ru.cdek.employeeslist.data.repository.Repository
import ru.cdek.employeeslist.internal.lazyDeferred

class DetailViewModel(
    private val repository: Repository
) : ViewModel() {

    var employeeId:Long = 0

    val getEmployeeById by lazyDeferred {
        repository.getEmployeesById(employeeId)
    }
}