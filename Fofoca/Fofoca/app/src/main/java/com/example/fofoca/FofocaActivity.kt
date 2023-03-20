package com.example.fofoca

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import java.lang.Thread.sleep
import kotlin.concurrent.thread

class FofocaActivity : AppCompatActivity() {
    private lateinit var tvFofoca: TextView
    private lateinit var btnResponder: Button
    private lateinit var rgFofocaReal: RadioGroup
    private lateinit var pgTimer: ProgressBar
    private lateinit var game: Game

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fofoca)

        this.tvFofoca = findViewById(R.id.tvFofoca)
        this.btnResponder = findViewById(R.id.btnResponder)
        this.rgFofocaReal = findViewById(R.id.rgFofocaReal)
        this.pgTimer = findViewById(R.id.pgTimer)
        this.game = Game()

        this.game.cadastrarFofoca(Fofoca("O pão é branco", true))
        this.game.cadastrarFofoca(Fofoca("Hoje é segunda", true))
        this.game.cadastrarFofoca(Fofoca("Teste", false))

        thread(start = true) {
            while (this.game.status == Status.EXECUTANDO) {
                this.pgTimer.progress++
                sleep(100)

                if(this.pgTimer.progress == 100) {
                    this.pgTimer.progress = 0

                }
            }
        }
    }

    fun fofocar() {
        val groupId = this.rgFofocaReal.checkedRadioButtonId
        if (groupId != -1) {
            val value: RadioButton = findViewById(groupId)
            this.game.fofocar(value.text == "Verdadeira")


        }

        Toast.makeText(baseContext, "Marque uma Opção!", Toast.LENGTH_SHORT).show()

    }

    fun checkGameStatus() {
        if(this.game.status != Status.EXECUTANDO) Toast.makeText(baseContext, "Acabaram as Fofocas!", Toast.LENGTH_LONG)
    }


}




