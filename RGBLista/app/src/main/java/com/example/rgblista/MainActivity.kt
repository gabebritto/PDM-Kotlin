package com.example.rgblista

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var rvLista: RecyclerView
    private lateinit var fabAdd: FloatingActionButton
    private lateinit var textToSpeech: TextToSpeech

    //Lista de Registros
    private var lista = mutableListOf<RGB>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.rvLista = findViewById(R.id.rvLista)
        this.fabAdd = findViewById(R.id.fabAddItem)

        this.rvLista.adapter = RGBAdapter(this.lista)
        (this.rvLista.adapter as RGBAdapter).onItemClickRecyclerView = OnItemClick()

        this.textToSpeech = TextToSpeech(this, null)
        ItemTouchHelper(OnSwipe()).attachToRecyclerView(this.rvLista)

        var formResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if (it.resultCode == RESULT_OK){
                val cor = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    it.data?.getSerializableExtra("COLOR", RGB::class.java)
                } else {
                    it.data?.getSerializableExtra("COLOR")
                } as RGB

                (this@MainActivity.rvLista.adapter as RGBAdapter).add(cor)
                Toast.makeText(this, "Cadastrada com sucesso!", Toast.LENGTH_SHORT).show()
            }
        }

        this.fabAdd.setOnClickListener{
            val intent = Intent(this, FormActivity::class.java)
            formResult.launch(intent)
        }
    }

    inner class OnItemClick : OnItemClickRecyclerView {
        override fun onItemClick(position: Int) {
            val rgb = this@MainActivity.lista.get(position)
            this@MainActivity.textToSpeech.speak(rgb.name, TextToSpeech.QUEUE_FLUSH, null, null)
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
            (this@MainActivity.rvLista.adapter as RGBAdapter).move(viewHolder.adapterPosition, target.adapterPosition)
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            when(direction) {
                ItemTouchHelper.END -> openDeleteDialog(viewHolder.adapterPosition)
                ItemTouchHelper.START -> shareHexCode(this@MainActivity.lista[viewHolder.adapterPosition])
            }
            this@MainActivity.rvLista.adapter?.notifyItemChanged(viewHolder.adapterPosition)
        }

        private fun isThereAnAppToResolveIntent(): Boolean {
            return intent.resolveActivity(packageManager) != null
        }

        private fun shareHexCode(color: RGB) {
            val intent = Intent(Intent.ACTION_SEND).apply {
                    setType("text/plain")
                    putExtra(Intent.EXTRA_TEXT, color.getHexCode())
                }
            if (isThereAnAppToResolveIntent()) {
                startActivity(intent)
            }
        }

        private fun openDeleteDialog(position: Int) {

            val builder = AlertDialog.Builder(this@MainActivity).apply {
                setMessage("Deseja excluir?")
                setPositiveButton("Excluir"){_,_ ->
                    (this@MainActivity.rvLista.adapter as RGBAdapter).del(position)
                }
                setNegativeButton("Cancelar"){_,_ ->
                    Toast.makeText(this@MainActivity, "Deletar cancelado!", Toast.LENGTH_SHORT).show()
                }
            }
            builder.create().show()
        }

    }

}