package com.example.bmiwithdb.bmi
import android.content.Context
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.bmiwithdb.R
import kotlinx.coroutines.launch
import com.example.android.bmiCalcWithDB.database.BMIDatabaseDao
import com.example.android.bmiCalcWithDB.database.BMI

/*
    view model for bmi calculator
 */
class calculatorViewModel {
    val database: BMIDatabaseDao,
    application: Application) : AndroidViewModel(application) {

        private var currentBMI = MutableLiveData<BMI?>()

        private val allBMI = database.getAllBMI()

        init {
            initializeBMI()
        }
        private fun initializeBMI() {
            viewModelScope.launch {
                currentBMI.value = getBMIFromDatabase()
            }
        }
        fun onCalc(v: View) {
            v.clearFocus()
            // get the name from the EditText
            val nameEditText: EditText = findViewById<EditText>(R.id.editTextId_name)
            val name: String = nameEditText.getText().toString()
            val heightEditText: EditText = findViewById(R.id.editTextId_height) as EditText
            val heightStr: String = heightEditText.getText().toString()
            val height = heightStr.toInt().toDouble()
            val weightEditText: EditText = findViewById(R.id.editTextId_weight) as EditText
            val weightStr: String = weightEditText.getText().toString()
            val weight = weightStr.toInt().toDouble()
            //let's now calculate the BMI
            var bmi = weight / height / height * 703.0
            bmi = Math.round(bmi * 100).toDouble()
            bmi /= 100
            val bmiStr = bmi.toString()
            // compose a message from the above information
            val msg = " Here is your information: \n $name\n Your BMI is $bmiStr"
            //set the message in the TextView
            val infoTextView: TextView = findViewById(R.id.textViewId_info) as TextView
            infoTextView.text = msg
            // display a toast message
            Toast.makeText(this, "Submit successfully!", Toast.LENGTH_LONG).show()
            nameEditText.setText("")
            heightEditText.setText("")
            weightEditText.setText("")
            nameEditText.requestFocus()
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(v.windowToken, 0)
            Log.d(TAG,"Submit")
        }

        companion object {
            private const val TAG = "MyApp"
        }
    }
fun onSave(v: View){
    viewModelScope.launch(
        val newBMI = BMI()
        insert(BMI)
    )
}
}

