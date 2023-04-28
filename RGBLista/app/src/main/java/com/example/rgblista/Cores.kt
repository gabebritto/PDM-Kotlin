package com.example.rgblista

class Cores {
    private var lista: MutableList<RGB> = mutableListOf()

    fun add(cor: RGB){
        this.lista.add(cor)
    }

    fun size(): Int{
        return this.lista.size
    }

    fun get(): MutableList<RGB> {
        return this.lista
    }
}