package org.iesmurgi.timeanddatepicker;

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class DatePickerFragment(val listener:(dia:Int,mes:Int,anio:Int)->Unit):
    DialogFragment(), DatePickerDialog.OnDateSetListener{
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        listener(dayOfMonth,month,year)
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        //return super.onCreateDialog(savedInstanceState)
        val c= Calendar.getInstance()
        val anio=c.get(Calendar.YEAR)
        val mes=c.get(Calendar.MONTH)
        val dia=c.get(Calendar.DAY_OF_MONTH)

        val picker=DatePickerDialog(activity as Context,this,anio,mes,dia)
        picker.datePicker.setMinDate(System.currentTimeMillis());
        return picker
    }


}
