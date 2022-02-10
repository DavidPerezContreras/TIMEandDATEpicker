package org.iesmurgi.timeanddatepicker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import java.util.Calendar
import android.widget.NumberPicker
import android.widget.TextView


class MainActivity : AppCompatActivity(){
    lateinit var edittextFecha:EditText
    lateinit var edittextHora:EditText


    lateinit var numberpickerHora:NumberPicker
    lateinit var numberpickerMinuto:NumberPicker
    lateinit var buttonPedirCita:Button
    lateinit var textviewResultado:TextView
    //SI LE DAS MUY RAPIDO AL EditText SE ABRE DOS VECES,
    // si intento solucionarlo como hice con el CalendarView,
    // aun podria quedar inutilizable, habria que usar el synchronized,
    // una variable estática creo que no sería del todo
    // efectiva ya que se están usando Threads
    // , ???
    //var open:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonPedirCita=findViewById<Button>(R.id.buttonPedirCita)


        edittextFecha=findViewById(R.id.edittextFecha)
        val c= Calendar.getInstance()
        val dia=c.get(Calendar.DAY_OF_MONTH)
        val mes=c.get(Calendar.MONTH)
        val anio=c.get(Calendar.YEAR)
        edittextFecha.setText("${dia}/${mes+1}/${anio}")

        edittextHora=findViewById(R.id.edittextHora)
        edittextHora.setText("${c.get(Calendar.HOUR)}:${c.get(Calendar.MINUTE)}")

        numberpickerHora=findViewById<NumberPicker>(R.id.numberpickerHora)
        numberpickerMinuto=findViewById<NumberPicker>(R.id.numberpickerMinuto)

        textviewResultado=findViewById(R.id.textViewResultado)

        edittextFecha.setOnClickListener(object:View.OnClickListener{
            override fun onClick(v: View?) {
                mostrarDatePickerDialog()
                //open=true
            }

        })

        edittextHora.setOnClickListener(object:View.OnClickListener{
            override fun onClick(v: View?) {
                mostrarTimePickerDialog()
                //open=true
            }

        })





        buttonPedirCita.setOnClickListener(object:View.OnClickListener{
            override fun onClick(v: View?) {
                var minutos:String=""
                when(numberpickerMinuto.value){
                    1->minutos="00"
                    2->minutos="15"
                    3->minutos="30"
                    4->minutos="45"
                }
                textviewResultado.text="Has pedido cita para el ${edittextFecha.text}\n a las ${numberpickerHora.value}:${minutos}"
            }
        })




        //LIMIT RANGE
        ///////////////////////////////////////////
                numberpickerHora.minValue=8
        numberpickerHora.maxValue=14

        numberpickerMinuto.minValue=1
        numberpickerMinuto.maxValue=4

        numberpickerMinuto.displayedValues= mutableListOf<String>("  ","15","30","45").toTypedArray()


        buttonPedirCita.isEnabled = checkCita()



        numberpickerHora.setOnValueChangedListener(object:NumberPicker.OnValueChangeListener{
            override fun onValueChange(picker: NumberPicker?, oldVal: Int, newVal: Int) {
                buttonPedirCita.isEnabled = checkCita()
                if(newVal==8)numberpickerMinuto.displayedValues= mutableListOf<String>("  ","15","30","45").toTypedArray()
                else numberpickerMinuto.displayedValues= mutableListOf<String>("00","15","30","45").toTypedArray()
            }

        })

        numberpickerMinuto.setOnValueChangedListener(object:NumberPicker.OnValueChangeListener{
            override fun onValueChange(picker: NumberPicker?, oldVal: Int, newVal: Int) {
                buttonPedirCita.isEnabled = checkCita()
            }

        })

    }

    fun checkCita(): Boolean {
        var vuelta:Boolean=true
        if(numberpickerHora.value==8&&numberpickerMinuto.value==1)vuelta=false

        return vuelta
    }

////////////////////////////////////////Fin de limit range


    private fun mostrarDatePickerDialog() {
        val datePicker=DatePickerFragment{dia,mes,anio->onDateSelected(dia,mes,anio)}
        datePicker.show(supportFragmentManager,"datePicker")
    }

    private fun onDateSelected(dia: Int, mes: Int, anio: Int) {
        edittextFecha.setText("${dia}/${mes+1}/${anio}")
        //open=false
    }

    private fun mostrarTimePickerDialog() {
        val timePicker=TimePickerFragment{hourOfDay,minute->onTimeSelected(hourOfDay,minute)}


        timePicker.show(supportFragmentManager,"timePicker")
    }
    private fun onTimeSelected(hourOfDay:Int,minute:Int) {
        edittextHora.setText("${hourOfDay}:${minute}")
        //open=false
    }

}