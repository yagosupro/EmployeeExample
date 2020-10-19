package ru.cdek.employeeslist.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.detail_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.cdek.employeeslist.R
import ru.cdek.employeeslist.databinding.DetailFragmentBinding

class DetailFragment : Fragment() {

    val viewModel: DetailViewModel by viewModel()

    private lateinit var binding: DetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.detail_fragment, container, false)
        binding.viewModel = viewModel
        bindUI()
        return binding.root
    }

    private fun bindUI() {
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.employeeId = arguments?.getLong("idEmployee")!!
            val employee = viewModel.getEmployeeById.await()
            employee.observe(viewLifecycleOwner, Observer { it ->
                if (it == null) return@Observer
                //fill field
                textView_name.text=it[0].employeeEntry.name
                textView_surName.text=it[0].employeeEntry.surName
                textView_speciality.text=it[0].specialitys[0].name
                if (it[0].employeeEntry.birthday!!.isNotEmpty())
                textView_birthday.text=it[0].employeeEntry.birthday + " г"
                if (it[0].employeeEntry.age!!.isNotEmpty())
                textView_age.text=it[0].employeeEntry.age+" лет"

                Glide.with(this@DetailFragment)
                    .load(it[0].employeeEntry.avatarUrl)
                    .into(imageView)



            })
        }
    }
}