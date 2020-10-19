package ru.cdek.employeeslist.data.network.response

import com.google.gson.annotations.SerializedName

data class EmployeesResponse (
    @SerializedName("response")
    val employeeResponses:List<EmployeeResponse>?
)

data class EmployeeResponse (
    @field:SerializedName("f_name") var name: String?,
    @field:SerializedName("l_name") var surName: String?,
    @field:SerializedName("birthday") var birthday: String?,
    @field:SerializedName("avatr_url") var avatarUrl: String?,
    @field:SerializedName("specialty") var specialityResponse:List<SpecialityResponse>?
)

data class SpecialityResponse(
    @field:SerializedName("specialty_id") var specialtyId: Long?,
    @field:SerializedName("name") var name: String?
)
