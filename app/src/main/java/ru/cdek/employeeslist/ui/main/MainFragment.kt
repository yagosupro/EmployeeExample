package ru.cdek.employeeslist.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.cdek.employeeslist.R
import ru.cdek.employeeslist.data.db.entity.SpecialityEntry
import ru.cdek.employeeslist.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    val viewModel: MainViewModel by viewModel()

    private lateinit var binding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        bindUI()

    }


    private fun initRecyclerView(items: List<SpecialityEntry>) {
        val groupAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(items.map { SpecialityItem(it) })
        }

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainFragment.context)
            adapter = groupAdapter
        }

        groupAdapter.setOnItemClickListener { item, view ->
            (item as? SpecialityItem)?.let {
                println(it.speciality.specialtyId)
            }
        }
    }

    fun bindUI() {
        lifecycleScope.launch(Dispatchers.Main) {
            val speciality = viewModel.getSpeciality.await()
            speciality.observe(viewLifecycleOwner, Observer { it ->
                if (it == null) return@Observer
                initRecyclerView(it)
                //101
            })
            var idSpeciality = 101
            val employeeJoin = viewModel.employeeWithSpeciality.await()
            employeeJoin.observe(viewLifecycleOwner, Observer { it ->
                if (it == null) return@Observer
                println(it)
            })

            val specialityJoin = viewModel.getSpecialityWithEmployee.await()
            specialityJoin.observe(viewLifecycleOwner, Observer { it ->
                if (it == null) return@Observer
                println(it)
            })
            val employeeById = viewModel.getEmployeeBySpeciality.await()
            employeeById.observe(viewLifecycleOwner, Observer { it ->
                if (it == null) return@Observer
                println(it)
            })
            val employee = viewModel.getEmployee.await()
            employee.observe(viewLifecycleOwner, Observer { it ->
                if (it == null) return@Observer

                //101
            })
        }
    }

}