package ru.cdek.employeeslist.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.cdek.employeeslist.data.db.entity.*

@Dao
interface EmployeeDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveEmployees(employees: EmployeeEntry):Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveEmployees(employees: List<EmployeeEntry>)

    @Query("SELECT * FROM employee_table")
    fun getAllEmployee(): LiveData<List<EmployeeEntry>>

    @Query("DELETE FROM employee_table")
    fun clearEmployee()

    @Query("DELETE FROM employee_speciality_join")
    fun clearEmployeeSpecialityJoin()

    @Query("DELETE FROM speciality_table")
    fun clearSpeciality()


    @Transaction
    suspend fun clearAll(){
        clearEmployeeSpecialityJoin()
        clearSpeciality()
        clearEmployee()
    }



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveSpeciality(specialityEntry: List<SpecialityEntry>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveSpeciality(specialityEntry: SpecialityEntry):Long

    @Query("SELECT * FROM speciality_table")
    fun getAllSpeciality(): LiveData<List<SpecialityEntry>>

    @Insert
    fun insertEmployeeSpecialityJoin(employeeSpecialityJoin: EmployeeSpecialityJoin)

    @Insert
    fun insertEmployeeSpecialityJoin(employeeSpecialityJoin: List<EmployeeSpecialityJoin>)

    @Query("SELECT * FROM employee_speciality_join")
    fun getAllJoins(): LiveData<List<EmployeeSpecialityJoin>>


    @Query("SELECT * FROM employee_table")
    fun getAllEmployeeWithSpeciality(): LiveData<List<EmployeeWithSpeciality>>

    @Query("SELECT * FROM employee_table WHERE employee_id=:id")
    fun getEmployeeById(id:Long):LiveData<List<EmployeeWithSpeciality>>

    @Query("SELECT * FROM speciality_table")
    fun getAllSpecialityFromEmployee(): LiveData<List<SpecialityWithEmployee>>

    @Query("SELECT * FROM speciality_table WHERE speciality_id=:specialtyId")
    fun getEmployeeBySpeciality(specialtyId:Long): LiveData<List<SpecialityWithEmployee>>
}