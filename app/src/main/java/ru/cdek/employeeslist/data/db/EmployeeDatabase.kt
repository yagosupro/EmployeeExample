package ru.cdek.employeeslist.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.cdek.employeeslist.data.db.entity.EmployeeEntry
import ru.cdek.employeeslist.data.db.entity.EmployeeSpecialityJoin
import ru.cdek.employeeslist.data.db.entity.EmployeeWithSpeciality
import ru.cdek.employeeslist.data.db.entity.SpecialityEntry


@Database(
    entities = [EmployeeEntry::class,SpecialityEntry::class, EmployeeSpecialityJoin::class],
    version = 1
)

abstract class EmployeeDatabase: RoomDatabase() {
    abstract fun employeeDAO():EmployeeDAO

    companion object {
        @Volatile
        private var instance: EmployeeDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            EmployeeDatabase::class.java, "EmployeeDatabaseEntries.db"
        ).build()


    }
}