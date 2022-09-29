package com.example.algoritmodeluhn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlin.math.log
import kotlin.random.Random
import kotlin.Int as Int

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val primera = findViewById<EditText>(R.id.txtNumber1)
        val segunda = findViewById<EditText>(R.id.txtNumber2)
        val tercera = findViewById<EditText>(R.id.txtNumber3)
        val cuarta = findViewById<EditText>(R.id.txtNumber4)
        val verificador = findViewById<EditText>(R.id.txtVerificador)

        val limpiar = findViewById<Button>(R.id.btnLimpiar)
        val aleatorio = findViewById<Button>(R.id.btnAleatorios)
        val calcular = findViewById<Button>(R.id.btnCalcular)

        limpiar.setOnClickListener {
            clean(primera,segunda,tercera,cuarta,verificador)
        }
        aleatorio.setOnClickListener {
            rellenarAleatorio(primera,segunda,tercera,cuarta,verificador)
        }
        calcular.setOnClickListener {
            calcularLuhn(primera,segunda,tercera,cuarta,verificador)
        }
    }

    fun calcularLuhn(n1:EditText, n2:EditText,n3:EditText,n4:EditText,n5:EditText){

        println("Calculando...")

        val primera = n1.text.toString()
        val segunda = n2.text.toString()
        val tercera = n3.text.toString()
        val cuarta  = n4.text.toString()
        var auxiliar = primera

        if ( primera.equals("") || primera.equals("")|| primera.equals("") || primera.equals("") ){
            Toast.makeText(applicationContext,"Asegurate de ingresar los 15 digitos correctamente, o utiliza la opción de ALEATORIO",Toast.LENGTH_SHORT).show()
            return
        }

        Toast.makeText(applicationContext,"Calculando...",Toast.LENGTH_LONG).show()

        var arrNON = arrayListOf<Int>()
        var arrPAR = arrayListOf<Int>()

        var cont = 0; var a = 0; var b = 0; var c = 0; var d = 0

        try{
            while(cont < 4){
                when(cont){
                    0,1,2 ->{
                        if(cont == 1)
                            auxiliar = segunda
                        else if(cont == 2)
                            auxiliar = tercera

                        a = auxiliar.substring(0,1).toInt()
                        b = auxiliar.substring(1,2).toInt()
                        c = auxiliar.substring(2,3).toInt()
                        d = auxiliar.substring(3,4).toInt()

                        arrNON.add(a); arrPAR.add(b)
                        arrNON.add(c); arrPAR.add(d)
                    //    Log.d("Impares", arrNON.toString());
                    //    Log.w("Pares", arrPAR.toString());
                    }
                }
                if(cont == 3 ){
                    auxiliar = cuarta

                    a = auxiliar.substring(0,1).toInt()
                    b = auxiliar.substring(1,2).toInt()
                    c = auxiliar.substring(2,3).toInt()

                    arrNON.add(a); arrPAR.add(b)
                    arrNON.add(c)
                    Log.d("Impares", arrNON.toString());
                    Log.w("Impares", arrPAR.toString());
                }
                cont++
            }
        }catch (e: Exception){
            Toast.makeText(applicationContext,"Asegurate de ingresar los 15 digitos correctamente, o utiliza la opción de Aleatorio.",Toast.LENGTH_LONG).show()
        }

        var auxARR = arrayListOf<Int>()
        var sumaPAR = 0; var sumaNON = 0;

        for(index in arrNON){
            var index2 = index*2
            if(index2 > 9){
                index2 -= 9
            }
            auxARR.add(index2)
        }
        sumaNON = auxARR.sum()
        sumaPAR = arrPAR.sum()

        val suma = sumaNON + sumaPAR

        var validador = 0
        var modulo = suma%10
        var suma1 = modulo - 10

        if(suma1 < 0)
            validador = suma1*(-1);
        else
            validador = suma1

        if(validador == 10)
            validador = 0


        println(validador)
        n5.setText(validador.toString())
    }

    private fun clean(n1:EditText, n2:EditText,n3:EditText,n4:EditText,n5:EditText){
        n1.setText("");
        n2.setText("");
        n3.setText("");
        n4.setText("");
        n5.setText("");
    }

    fun rellenarAleatorio(n1:EditText, n2:EditText,n3:EditText,n4:EditText,n5:EditText){
        println("rellenando...")
        val randomValues = List(3) { Random.nextInt(1000, 9999) }
        val randomValues1 = Random.nextInt(100, 999)

        n1.setText(randomValues[0].toString())
        n2.setText(randomValues[1].toString())
        n3.setText(randomValues[2].toString())
        n4.setText(randomValues1.toString())
    }
}