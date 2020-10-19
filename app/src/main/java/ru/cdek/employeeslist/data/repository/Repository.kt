package ru.cdek.employeeslist.data.repository

import androidx.lifecycle.LiveData
import ru.cdek.employeeslist.data.db.entity.EmployeeEntry
import ru.cdek.employeeslist.data.db.entity.EmployeeWithSpeciality
import ru.cdek.employeeslist.data.db.entity.SpecialityEntry
import ru.cdek.employeeslist.data.db.entity.SpecialityWithEmployee
import ru.cdek.employeeslist.data.network.response.EmployeeResponse

interface Repository {

    suspend fun fetchData()

    fun saveEmployees(employeeResponses: List<EmployeeResponse>)

    suspend fun getEmployees(): LiveData<List<EmployeeEntry>>

//    suspend fun getEmployeesFromSpecialityId(id:Long): LiveData<EmployeeWithSpeciality>

    suspend fun getAllEmployeeWithSpeciality(): LiveData<List<EmployeeWithSpeciality>>

    suspend fun getSpeciality():LiveData<out List<SpecialityEntry>>

    suspend fun getAllSpecialityWithEmployee(): LiveData<List<SpecialityWithEmployee>>

    suspend fun getEmployeeBySpeciality(id:Long):LiveData<List<SpecialityWithEmployee>>
}