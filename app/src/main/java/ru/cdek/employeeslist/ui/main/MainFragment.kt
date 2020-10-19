package ru.cdek.employeeslist.ui.main

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
        bindUI()
        return binding.root
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
                val bundle = Bundle()
                bundle.putLong("idSpeciality", it.speciality.specialtyId)
                findNavController().navigate(R.id.employeeFragment, bundle)
            }
        }
    }

    private fun bindUI() {
        lifecycleScope.launch(Dispatchers.Main) {
            val speciality = viewModel.getSpeciality.await()
            speciality.observe(viewLifecycleOwner, Observer { it ->
                if (it == null) return@Observer
                if (it.isNotEmpty()) {
                    initRecyclerView(it)
                }
            })
        }
    }

}