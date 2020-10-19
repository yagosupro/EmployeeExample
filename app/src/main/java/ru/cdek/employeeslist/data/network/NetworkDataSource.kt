package ru.cdek.employeeslist.data.network

import androidx.lifecycle.LiveData
import ru.cdek.employeeslist.data.network.response.EmployeeResponse

interface NetworkDataSource {
    val downloadedEmployees: LiveData<List<EmployeeResponse>>

    suspend fun fetchData()
}