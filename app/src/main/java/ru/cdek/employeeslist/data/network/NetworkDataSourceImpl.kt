package ru.cdek.employeeslist.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.cdek.employeeslist.data.network.response.EmployeeResponse

class NetworkDataSourceImpl(private val api: Api) : NetworkDataSource {

    private val _downloadedEmployees = MutableLiveData<List<EmployeeResponse>>()
    override val downloadedEmployees: LiveData<List<EmployeeResponse>>
        get() = _downloadedEmployees

    override suspend fun fetchData() {
        try {
            val response = api.fetchData().await()
            _downloadedEmployees.postValue(response.employeeResponses)
        } catch (e: Exception) {
            //по хорошему тут обработчик нормальный сделать, не понятно что возвращает сервер
            Log.e("Connectivity", e.message.toString())
        }

    }
}