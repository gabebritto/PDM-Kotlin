package com.example.fofoca

class Game {
    var fofocas: MutableList<Fofoca> = mutableListOf()
    var fofocaAtual: Fofoca?
    var status: Status

    init {
        this.fofocaAtual = null
        this.status = Status.EXECUTANDO
    }

    fun cadastrarFofoca(fofoca: Fofoca) {
        this.fofocas.add(fofoca)
    }

    fun checkFofocas(): Boolean {
        var ultima = this.fofocas.last()
        return this.fofocaAtual!!.equals(ultima)
    }

    fun validateFofoca(real: Boolean): Boolean {
        if (this.fofocaAtual != null) {
            return this.fofocaAtual!!.real == real
        }
        return false
    }

    fun updateFofocaAtual() {
        if(!this.checkFofocas()) {
            val indexAtual: Int = this.fofocas.indexOf(this.fofocaAtual)
            this.fofocaAtual = this.fofocas[indexAtual+1]
        }

    }

    fun fofocar(real: Boolean): Int{
        this.updateFofocaAtual()

        if (!this.validateFofoca(real)) {
            this.status = Status.ACABOU
            return 0
        }
        else{
            if (this.checkFofocas()){
                this.status = Status.ACABOU
            }
            return 1
        }
    }

}