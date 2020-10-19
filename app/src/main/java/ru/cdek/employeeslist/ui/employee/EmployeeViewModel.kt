package ru.cdek.employeeslist.ui.employee

import androidx.lifecycle.ViewModel
import ru.cdek.employeeslist.data.repository.Repository
import ru.cdek.employeeslist.internal.lazyDeferred

class EmployeeViewModel(private val repository: Repository):ViewModel() {
    var specialityId:Long = 0

    val getEmployeeBySpecialityId by lazyDeferred {
        repository.getEmployeeBySpeciality(specialityId)
    }
}