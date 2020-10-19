package ru.cdek.employeeslist.ui.employee

import android.view.View
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_employee.*
import ru.cdek.employeeslist.R
import ru.cdek.employeeslist.data.db.entity.EmployeeEntry

class EmployeeItem(val employee: EmployeeEntry) : Item() {

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            textView_name.text = employee.name
            textView_surname.text = employee.surName
            if (employee.age!!.isNotEmpty()) {
                textView_age.visibility = View.VISIBLE
                textView_age.text = employee.age
            }
        }
    }

    override fun getLayout() = R.layout.item_employee
}