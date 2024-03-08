package com.luvizotto.app_alcoolgasolina

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    private lateinit var textInputAlcool: TextInputLayout
    private lateinit var editAlcool: TextInputEditText

    private lateinit var textInputGasolina: TextInputLayout
    private lateinit var editGasolina: TextInputEditText

    private lateinit var btnCalcular: Button
    private lateinit var textResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inicializarComponentesInterface()
        btnCalcular.setOnClickListener{
            calcularMelhorPreço()
        }
    }
    private fun inicializarComponentesInterface() {
        textInputAlcool = findViewById(R.id.textInputAlcool)
        editAlcool = textInputAlcool.findViewById(R.id.textInput_Alcool)

        textInputGasolina = findViewById(R.id.textInputGasolina)
        editGasolina = textInputGasolina.findViewById(R.id.textInput_Gasolina)

        btnCalcular = findViewById(R.id.btnCalcular)
        textResultado = findViewById(R.id.ResultadoView)
    }

    private fun calcularMelhorPreço(){

        val preçoAlcool = editAlcool.text.toString()
        val preçoGasolina = editGasolina.text.toString()

        val resultadoValidaçao = validarCampos(preçoAlcool, preçoGasolina)
        if( resultadoValidaçao ){

            //Cálculo do melhor preço
            //se (valorAlcool / valorGasolina) >= 0.7 é melhor Gasolina
            // se não, é melhor utilizar Álcool

            val preçoAlcoolDouble = preçoAlcool.toDouble()
            val preçoGasolinaDouble = preçoGasolina.toDouble()

            val resultado = preçoAlcoolDouble / preçoGasolinaDouble

            if (resultado >= 0.7){
                textResultado.text = "Melhor Utilizar Gasolina"
            }else{
                textResultado.text = "Melhor utilizar Álcool"
            }
        }
    }

    private fun validarCampos (pAlcool: String, pGasolina: String): Boolean{

        textInputAlcool.error = null
        textInputGasolina.error = null

        if( pAlcool.isEmpty()){
            textInputAlcool.error = "Digite o preço do Álcool"
            return false
        } else if( pGasolina.isEmpty() ){
            textInputGasolina.error = "Digite o preço da Gasolina"
            return false
        }
        return true
    }


}