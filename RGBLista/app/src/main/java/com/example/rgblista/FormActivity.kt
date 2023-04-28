package com.example.rgblista

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.SeekBar.OnSeekBarChangeListener

class FormActivity : AppCompatActivity(){

    private lateinit var colorName: EditText
    private lateinit var sbRed: SeekBar
    private lateinit var sbGreen: SeekBar
    private lateinit var sbBlue: SeekBar
    private lateinit var tvRed: TextView
    private lateinit var tvGreen: TextView
    private lateinit var tvBlue: TextView
    private lateinit var llColor: LinearLayout
    private lateinit var llRed: LinearLayout
    private lateinit var llGreen: LinearLayout
    private lateinit var llBlue: LinearLayout
    private lateinit var btnSalvar: Button
    private lateinit var btnCancelar: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        this.colorName = findViewById(R.id.colorName)

        this.sbRed = findViewById(R.id.sbRed)
        this.sbGreen = findViewById(R.id.sbGreen)
        this.sbBlue = findViewById(R.id.sbBlue)

        this.tvRed = findViewById(R.id.tvRed)
        this.tvGreen = findViewById(R.id.tvGreen)
        this.tvBlue = findViewById(R.id.tvBlue)

        this.llColor = findViewById(R.id.llColor)
        this.llRed = findViewById(R.id.llRed)
        this.llGreen = findViewById(R.id.llGreen)
        this.llBlue = findViewById(R.id.llBlue)

        this.btnSalvar = findViewById(R.id.btnFormSalvar)
        this.btnCancelar = findViewById(R.id.btnFormCancelar)

        this.llRed.setBackgroundColor(Color.rgb(255, 0, 0))
        this.llGreen.setBackgroundColor(Color.rgb(0,255,0))
        this.llBlue.setBackgroundColor(Color.rgb(0,0,255))
        this.llColor.setBackgroundColor(this.createColor())

        this.sbRed.setOnSeekBarChangeListener(OnChangeColor())
        this.sbGreen.setOnSeekBarChangeListener(OnChangeColor())
        this.sbBlue.setOnSeekBarChangeListener(OnChangeColor())

        this.btnSalvar.setOnClickListener { salvar() }
        this.btnCancelar.setOnClickListener { cancelar() }

    }

    fun createColor(): Int{
        val red = this@FormActivity.sbRed.progress
        this@FormActivity.llRed.setBackgroundColor(Color.rgb(red, 0, 0))

        val green = this@FormActivity.sbGreen.progress
        this@FormActivity.llGreen.setBackgroundColor(Color.rgb(0, green, 0))

        val blue = this@FormActivity.sbBlue.progress
        this@FormActivity.llBlue.setBackgroundColor(Color.rgb(0, 0, blue))

        return Color.rgb(red, green, blue)
    }

    private fun salvar(){
        val nome = this.colorName.text.toString()
        val color = RGB(nome, this@FormActivity.sbRed.progress, this@FormActivity.sbGreen.progress, this@FormActivity.sbBlue.progress)
        val intent = Intent().apply {
            putExtra("COLOR", color)
        }
        setResult(RESULT_OK, intent)
        finish()
    }

    private fun cancelar(){
        finish()
    }

    inner class OnChangeColor: OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            val red = this@FormActivity.sbRed.progress
            val green = this@FormActivity.sbGreen.progress
            val blue = this@FormActivity.sbBlue.progress

            this@FormActivity.tvRed.text = red.toString()
            this@FormActivity.tvGreen.text = green.toString()
            this@FormActivity.tvBlue.text = blue.toString()

            this@FormActivity.llColor.setBackgroundColor(this@FormActivity.createColor())
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {
            return
        }

        override fun onStopTrackingTouch(seekBar: SeekBar?) {
            return
        }
    }
}