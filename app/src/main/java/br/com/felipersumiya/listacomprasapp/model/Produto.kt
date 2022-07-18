package br.com.felipersumiya.listacomprasapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Produto(

    @PrimaryKey(autoGenerate = true)
    val id:Long = 0L,

    val nome:String,

    val descricao:String,

    val imagemUrl:String?
) {
}