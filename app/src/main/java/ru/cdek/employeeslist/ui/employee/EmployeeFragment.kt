package ru.cdek.employeeslist.ui.employee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.cdek.employeeslist.R
import ru.cdek.employeeslist.data.db.entity.SpecialityWithEmployee
import ru.cdek.employeeslist.databinding.EmployeeFragmentBinding
import ru.cdek.employeeslist.ui.main.SpecialityItem

class EmployeeFragment : Fragment() {

    val viewModel: EmployeeViewModel by viewModel()

    private lateinit var binding: EmployeeFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.employee_fragment, container, false)
        binding.viewModel = viewModel
        bindUI( )
        return binding.root
    }

    private fun bindUI() {
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.specialityId= arguments?.getLong("idSpeciality")!!
            val speciality = viewModel.getEmployeeBySpecialityId.await()
            speciality.observe(viewLifecycleOwner, Observer { it ->
                if (it == null) return@Observer
                initRecyclerView(it)
            })
        }
    }

    private fun initRecyclerView(items: List<SpecialityWithEmployee>) {
        val groupAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(items[0].employees.map { EmployeeItem(it) })
        }

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@EmployeeFragment.context)
            adapter = groupAdapter
        }

        groupAdapter.setOnItemClickListener { item, view ->
            (item as? EmployeeItem)?.let {
                val bundle=Bundle()
                bundle.putLong("idEmployee",it.employee.employeeId)
                println()
                findNavController().navigate(R.id.detailFragment,bundle)
            }
        }
    }
}

