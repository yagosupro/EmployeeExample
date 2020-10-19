package ru.cdek.employeeslist.di

import io.reactivex.Single
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.cdek.employeeslist.data.db.EmployeeDAO
import ru.cdek.employeeslist.data.db.EmployeeDatabase
import ru.cdek.employeeslist.data.network.Api
import ru.cdek.employeeslist.data.network.NetworkDataSource
import ru.cdek.employeeslist.data.network.NetworkDataSourceImpl
import ru.cdek.employeeslist.data.repository.Repository
import ru.cdek.employeeslist.data.repository.RepositoryImpl
import ru.cdek.employeeslist.ui.detail.DetailViewModel
import ru.cdek.employeeslist.ui.employee.EmployeeViewModel
import ru.cdek.employeeslist.ui.main.MainViewModel

private val myModule = module {


    viewModel {
        MainViewModel(get())

    }
    viewModel { EmployeeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    single<Repository> {
        RepositoryImpl(get(), get())
    }
    single { Api() }
    single<NetworkDataSource> { NetworkDataSourceImpl(get()) }
    single { EmployeeDatabase(get()) }
    single { get<EmployeeDatabase>().employeeDAO() }
//    single { get<EmployeeDatabase>().specialityDAO() }


}

val modules = listOf(myModule)