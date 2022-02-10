package org.iesmurgi.timeanddatepicker;

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.sql.Time
import java.util.*

class TimePickerFragment(val listener:(hourOfDay: Int, minute: Int)->Unit):
    DialogFragment(), TimePickerDialog.OnTimeSetListener{
    /*
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        listener(dayOfMonth,month,year)
    }
    */
    /*
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
    */


    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        listener(hourOfDay,minute)
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        //return super.onCreateDialog(savedInstanceState)
        val c= Calendar.getInstance()
        val hourOfDay=c.get(Calendar.HOUR_OF_DAY)
        val minute=c.get(Calendar.MINUTE)


        val picker=TimePickerDialog(activity as Context,this,hourOfDay,minute,true)
        return picker
    }


}
