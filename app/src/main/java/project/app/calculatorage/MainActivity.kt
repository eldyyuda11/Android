package project.app.calculatorage

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var tvSelectedDate : TextView? = null
    private var tvInMinutes :TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker : Button = findViewById(R.id.btnDate)
        tvSelectedDate = findViewById(R.id.textViewTGL)
        tvInMinutes = findViewById(R.id.textViewMinute)

        btnDatePicker.setOnClickListener{
            clickdatePicker()
        }
    }

    private fun clickdatePicker(){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        Toast.makeText(this,"Silahkan Pilih Tanggal lahir anda",Toast.LENGTH_LONG).show()

        val dpd = DatePickerDialog(this, {
                _, selectedYear, selectedMonth, selectedDayOfMonth ->
                Toast.makeText(this,"Day is $selectedDayOfMonth, Month is ${selectedMonth+1}, Year is $selectedYear, ",
                Toast.LENGTH_LONG).show()
                val selectedDate = "$selectedDayOfMonth-${selectedMonth+1}-$selectedYear"
                tvSelectedDate?.text=selectedDate

                val sdf = SimpleDateFormat("dd-mm-yyyy",Locale.ENGLISH)
                val theDate = sdf.parse(selectedDate)
                theDate?.let {
                    val selectedDateInMinutes = theDate.time / 60000
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let {
                        val currentDateinMinutes= currentDate.time / 60000
                        val different = currentDateinMinutes - selectedDateInMinutes
                        tvInMinutes?.text= different.toString()
                    }
                }},year,month,day)
        dpd.datePicker.maxDate=System.currentTimeMillis() - 86400000
        dpd.show()
    }
}