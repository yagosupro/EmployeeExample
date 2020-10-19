package ru.cdek.employeeslist.data.db.entity

import androidx.room.*
import androidx.room.ForeignKey.CASCADE

@Entity(
    tableName = "employee_table",
    indices = [Index("employee_id")]
)
class EmployeeEntry {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "employee_id")
    var employeeId: Long = 0

    @ColumnInfo(name = "name")
    var name: String? = null

    @ColumnInfo(name = "sur_name")
    var surName: String? = null

    @ColumnInfo(name = "birthday")
    var birthday: String? = ""

    @ColumnInfo(name = "avatar_url")
    var avatarUrl: String? = null

    @ColumnInfo(name = "age")
    var age: String? = null

    @Ignore
    var speciality: ArrayList<SpecialityEntry>? = ArrayList<SpecialityEntry>()
}


@Entity(tableName = "speciality_table",indices = [Index("speciality_id")])
class SpecialityEntry {
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "speciality_id")
    var specialtyId: Long = 0

    @ColumnInfo(name = "speciality_name")
    var name: String? = null
}

@Entity(tableName = "employee_speciality_join",
    primaryKeys = ["fk_employee_id", "fk_speciality_id"],
    indices = [
        Index("fk_employee_id"),
        Index("fk_speciality_id")
    ],
    foreignKeys = [
        ForeignKey(
            entity = EmployeeEntry::class,
            childColumns = arrayOf("fk_employee_id"),
            parentColumns = arrayOf("employee_id")
        ), ForeignKey(
            entity = SpecialityEntry::class,
            childColumns = arrayOf("fk_speciality_id"),
            parentColumns = arrayOf("speciality_id")
        )
    ]
)
class EmployeeSpecialityJoin(
    @ColumnInfo(name = "fk_employee_id") val fkEmployeeId: Long,
    @ColumnInfo(name = "fk_speciality_id") val fkSpecialityId: Long
)


class EmployeeWithSpeciality {

    @Embedded
    lateinit var employeeEntry: EmployeeEntry

    @Relation(
        entityColumn = "speciality_id",
        parentColumn = "employee_id",
        associateBy = Junction(
            EmployeeSpecialityJoin::class,
            entityColumn = "fk_speciality_id",
            parentColumn = "fk_employee_id"

        )
    )
    var specialitys: List<SpecialityEntry> = arrayListOf()
}
class SpecialityWithEmployee {

    @Embedded
    lateinit var speciality: SpecialityEntry

    @Relation(
        entityColumn = "employee_id",
        parentColumn = "speciality_id",
        associateBy = Junction(
            EmployeeSpecialityJoin::class,
            parentColumn = "fk_speciality_id",
            entityColumn = "fk_employee_id"
        )
    )
    var employees: List<EmployeeEntry> = arrayListOf()
}
