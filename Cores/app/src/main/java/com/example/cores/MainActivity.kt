package com.example.cores


import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var strColor: TextView
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.strColor = findViewById(R.id.strColor)
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

        this.llRed.setBackgroundColor(Color.rgb(255, 0, 0))
        this.llGreen.setBackgroundColor(Color.rgb(0,255,0))
        this.llBlue.setBackgroundColor(Color.rgb(0,0,255))
        this.llColor.setBackgroundColor(this.createColor())

        this.sbRed.setOnSeekBarChangeListener(OnChangeColor())
        this.sbGreen.setOnSeekBarChangeListener(OnChangeColor())
        this.sbBlue.setOnSeekBarChangeListener(OnChangeColor())

    }

    fun createColor(): Int{
        val red = this@MainActivity.sbRed.progress
        this@MainActivity.llRed.setBackgroundColor(Color.rgb(red, 0, 0))

        val green = this@MainActivity.sbGreen.progress
        this@MainActivity.llGreen.setBackgroundColor(Color.rgb(0, green, 0))

        val blue = this@MainActivity.sbBlue.progress
        this@MainActivity.llBlue.setBackgroundColor(Color.rgb(0, 0, blue))

        return Color.rgb(red, green, blue)
    }

    fun getColorHex(): String {
        val colorR = this@MainActivity.sbRed.progress
        val colorG = this@MainActivity.sbGreen.progress
        val colorB = this@MainActivity.sbBlue.progress

        var r = Integer.toHexString(((255*colorR)/255))
        if(r.length==1) r = "0"+r
        var g = Integer.toHexString(((255*colorG)/255))
        if(g.length==1) g = "0"+g
        var b = Integer.toHexString(((255*colorB)/255))
        if(b.length==1) b = "0"+b
        return "#" + r + g + b
    }

    inner class OnChangeColor: OnSeekBarChangeListener{
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            val red = this@MainActivity.sbRed.progress
            val green = this@MainActivity.sbGreen.progress
            val blue = this@MainActivity.sbBlue.progress

            this@MainActivity.tvRed.text = red.toString()
            this@MainActivity.tvGreen.text = green.toString()
            this@MainActivity.tvBlue.text = blue.toString()

            this@MainActivity.strColor.text = this@MainActivity.getColorHex()
            this@MainActivity.llColor.setBackgroundColor(this@MainActivity.createColor())
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {
//            TODO("Not yet implemented")
        }

        override fun onStopTrackingTouch(seekBar: SeekBar?) {
//            TODO("Not yet implemented")
        }

    }
}