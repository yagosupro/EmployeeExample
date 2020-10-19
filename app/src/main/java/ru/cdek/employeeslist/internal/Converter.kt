package ru.cdek.employeeslist.internal

import android.annotation.SuppressLint
import ru.cdek.employeeslist.data.db.entity.EmployeeEntry
import ru.cdek.employeeslist.data.db.entity.SpecialityEntry
import ru.cdek.employeeslist.data.network.response.EmployeeResponse
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


fun changeRegistr(string: String): String {
        val array = string.toCharArray()
        array.forEachIndexed { index, char ->
            when {
                (index == 0) -> array[index] = char.toUpperCase()
                else -> array[index] = char.toLowerCase()
            }
        }
        return String(array)
    }

@SuppressLint("SimpleDateFormat")
fun formatDate(birthday: String?): String? {
    return try {
        val parser = SimpleDateFormat("yyyy-dd-MM")
        val formatter = SimpleDateFormat("dd.MM.yyyy г")
        formatter.format(parser.parse(birthday))
    } catch (e: Exception) {
        ""
    }
}

@SuppressLint("SimpleDateFormat")
fun calculateAge(birthday: String?): String {
    return try {
        val parser = SimpleDateFormat("yyyy-dd-MM")
        val birthdayDate: Date = parser.parse(birthday)
        val now = Date()
        val formatter: DateFormat = SimpleDateFormat("yyyyMMdd")
        val d1: Int = formatter.format(birthdayDate).toInt()
        val d2: Int = formatter.format(now).toInt()
        val age = (d2 - d1) / 10000
        age.toString()
    } catch (e: Exception) {
        ""
    }
}

fun convertToValidData(employeeResponses: List<EmployeeResponse>): ArrayList<EmployeeEntry> {
    val employeesEntryList: ArrayList<EmployeeEntry> = ArrayList<EmployeeEntry>()
    employeeResponses.forEach { employee ->
        val employeeEntry: EmployeeEntry = EmployeeEntry()
        employeeEntry.name = employee.name?.let { changeRegistr(it) }
        employeeEntry.surName = employee.surName?.let { changeRegistr(it) }
        employeeEntry.birthday = formatDate(employee.birthday)
        employeeEntry.avatarUrl = employee.avatarUrl
        employeeEntry.age = calculateAge(employee.birthday)

        employee.specialityResponse?.forEach() { speciality ->
            val specialityEntry: SpecialityEntry? = SpecialityEntry()
            specialityEntry!!.specialtyId= speciality.specialtyId!!
            specialityEntry.name=speciality.name
            employeeEntry.speciality?.add(specialityEntry!!)
        }
        employeesEntryList.add(employeeEntry)
    }
    return employeesEntryList

}
