package ru.cdek.employeeslist.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.cdek.employeeslist.data.db.entity.EmployeeEntry
import ru.cdek.employeeslist.data.repository.Repository
import ru.cdek.employeeslist.internal.lazyDeferred

class MainViewModel(
    private val repository: Repository
) : ViewModel() {

    fun buttonClick() {

    }
//    val getEmployeeFromSpeciality by lazyDeferred {
//        repository.getEmployeesFromSpecialityId(101)
//    }

    val employeeWithSpeciality by lazyDeferred {
        repository.getAllEmployeeWithSpeciality()
    }

    val getEmployee by lazyDeferred {
        repository.getEmployees()
    }
    val getSpeciality by lazyDeferred {
        repository.getSpeciality()
    }

    val getSpecialityWithEmployee by lazyDeferred {
        repository.getAllSpecialityWithEmployee()
    }
    val getEmployeeBySpeciality by lazyDeferred {
        repository.getEmployeeBySpeciality(101)
    }
}