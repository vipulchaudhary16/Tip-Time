package com.veercreation.tiptime

import android.icu.text.NumberFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.veercreation.tiptime.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener {calculateTip()}
    }

    private fun calculateTip(){
       val stringInTextField = binding.costInput.text.toString()
        val cost  = stringInTextField.toDoubleOrNull()
        if(cost==null){
            binding.tipResult.text = " "
            return
        }
        val tipPercentages = when(binding.tipOption.checkedRadioButtonId){
            R.id.option20Pr -> 0.20
            R.id.option18Pr -> 0.18
            else -> 0.15
        }
        var tip = cost * tipPercentages
        val roundUp = binding.roundUpSwitch.isChecked
        if(roundUp){
            tip = kotlin.math.ceil(tip)
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)

        binding.tipResult.text = getString(R.string.tip_amount_text , formattedTip)
    }
}