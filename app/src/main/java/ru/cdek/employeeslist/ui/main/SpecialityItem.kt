package ru.cdek.employeeslist.ui.main

import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_speciality.*
import kotlinx.android.synthetic.main.item_speciality.view.*
import ru.cdek.employeeslist.R
import ru.cdek.employeeslist.data.db.entity.SpecialityEntry

class SpecialityItem(val speciality: SpecialityEntry) : Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            textView_name.text=speciality.name
        }
    }

    override fun getLayout() = R.layout.item_speciality
}