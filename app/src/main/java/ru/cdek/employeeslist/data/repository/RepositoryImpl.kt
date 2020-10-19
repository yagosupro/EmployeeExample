package ru.cdek.employeeslist.data.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.cdek.employeeslist.data.db.EmployeeDAO
import ru.cdek.employeeslist.data.db.entity.*
import ru.cdek.employeeslist.data.network.NetworkDataSource
import ru.cdek.employeeslist.data.network.response.EmployeeResponse
import ru.cdek.employeeslist.internal.convertToValidData
import java.util.*

class RepositoryImpl(
    private val networkDataSource: NetworkDataSource,
    private val employeeDAO: EmployeeDAO
) : Repository {

    init {
        GlobalScope.launch(Dispatchers.IO) {
            fetchData()
        }
        networkDataSource.apply {
            downloadedEmployees.observeForever { employees ->
                saveEmployees(employees)
            }
        }
    }

    override suspend fun fetchData() {
        networkDataSource.fetchData()
    }

    override fun saveEmployees(employeeResponses: List<EmployeeResponse>) {
        GlobalScope.launch(Dispatchers.IO) {
            val employeesEntryList = convertToValidData(employeeResponses)
            employeeDAO.clearAll()
            saveAll(employeesEntryList)
        }
    }

    override suspend fun getEmployeesById(id: Long): LiveData<List<EmployeeWithSpeciality>>{
        return withContext(Dispatchers.IO) {
            employeeDAO.getEmployeeById(id)
        }
    }


    override suspend fun getSpeciality(): LiveData<out List<SpecialityEntry>> {
        return withContext(Dispatchers.IO) {
            employeeDAO.getAllSpeciality()
        }

    }

    override suspend fun getEmployeeBySpeciality(idSpeciality:Long): LiveData<List<SpecialityWithEmployee>> {
        return withContext(Dispatchers.IO) {
            employeeDAO.getEmployeeBySpeciality(idSpeciality)
        }
    }

    suspend fun saveAll(employeesEntryList: ArrayList<EmployeeEntry>) {
        var joinList = ArrayList<EmployeeSpecialityJoin>()

        for (employee in employeesEntryList) {
            var employeeId = employeeDAO.saveEmployees(employee)
            for (speciality in employee.speciality!!) {
                var specialityId = employeeDAO.saveSpeciality(speciality)
                joinList.add(EmployeeSpecialityJoin(employeeId, specialityId))
            }
        }

        println(joinList)
        employeeDAO.insertEmployeeSpecialityJoin(joinList)
    }
}