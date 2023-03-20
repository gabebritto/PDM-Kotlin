package com.example.fofoca

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var etFofoca: EditText
    private lateinit var btnSalvar: Button
    private lateinit var rgReal: RadioGroup
    private lateinit var game: Game

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.etFofoca = findViewById(R.id.etFofoca)
        this.btnSalvar = findViewById(R.id.btnSalvar)
        this.rgReal = findViewById(R.id.rgReal)

        this.game = Game()

        this.btnSalvar.setOnClickListener({this.cadastrar()})
    }

    fun cadastrar(){
        val text: String = this.etFofoca.text.toString()
        val groupId: Int = this.rgReal.checkedRadioButtonId

        if(groupId != -1) {
            val real: RadioButton = findViewById(groupId)

            var fofoca = Fofoca(text, real.text == "Verdadeira")
            this.game.cadastrarFofoca(fofoca)
            this.reset()

            Toast.makeText(baseContext, "Fofoca Cadastrada!", Toast.LENGTH_SHORT).show()
        }

        Toast.makeText(baseContext, "Campos Inválidos!", Toast.LENGTH_SHORT).show()
    }

    fun reset() {
        this.etFofoca.text.clear()
        this.rgReal.clearCheck()
    }

}