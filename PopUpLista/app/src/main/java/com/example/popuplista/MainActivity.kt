package com.example.popuplista

import android.content.DialogInterface
import android.content.DialogInterface.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.recyclerview.widget.ItemTouchHelper

class MainActivity : AppCompatActivity() {
    private lateinit var rvLista: RecyclerView
    private lateinit var fabAdd: FloatingActionButton
    private lateinit var etName: EditText
    private lateinit var textToSpeech: TextToSpeech

    //Lista de Registros
    private var lista = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.rvLista = findViewById(R.id.rvLista)
        this.fabAdd = findViewById(R.id.fabAddItem)

        this.fabAdd.setOnClickListener{ add() }

        this.rvLista.adapter = MyAdapter(this.lista)
        (this.rvLista.adapter as MyAdapter).onItemClickRecyclerView = OnItemClick()

        this.textToSpeech = TextToSpeech(this, null)
        ItemTouchHelper(OnSwipe()).attachToRecyclerView(this.rvLista)
    }

    private fun add(){
        this.etName = EditText(this)
        val builder = AlertDialog.Builder(this).apply {
            setMessage("Digite aqui")
            setView(this@MainActivity.etName)
            setPositiveButton("Salvar", OnClick())
            setNegativeButton("Cancelar", null)
        }
        builder.create().show()
    }

    inner class OnClick : DialogInterface.OnClickListener {
        override fun onClick(p0: DialogInterface?, p1: Int) {
            val name = this@MainActivity.etName.text.toString()
            (this@MainActivity.rvLista.adapter as MyAdapter).add(name)
        }
    }

    inner class OnItemClick : OnItemClickRecyclerView {
        override fun onItemClick(position: Int) {
            val name = this@MainActivity.lista.get(position)
            this@MainActivity.textToSpeech.speak(name, TextToSpeech.QUEUE_FLUSH, null, null)
        }
    }

    inner class OnSwipe : ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.DOWN or ItemTouchHelper.UP,
        ItemTouchHelper.START or ItemTouchHelper.END
    ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            (this@MainActivity.rvLista.adapter as MyAdapter).move(viewHolder.adapterPosition, target.adapterPosition)
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            (this@MainActivity.rvLista.adapter as MyAdapter).del(viewHolder.adapterPosition)
        }

    }

}