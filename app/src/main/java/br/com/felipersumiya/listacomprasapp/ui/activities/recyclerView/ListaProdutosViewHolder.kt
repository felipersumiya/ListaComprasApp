package br.com.felipersumiya.listacomprasapp.ui.activities.recyclerView

import android.content.Context
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import br.com.felipersumiya.listacomprasapp.databinding.ActivityProdutoBinding
import br.com.felipersumiya.listacomprasapp.model.Produto

class ListaProdutosViewHolder(
    private val binding: ActivityProdutoBinding,
    var quandoClicaNoItem: (produto:Produto)-> Unit={}
    ): RecyclerView.ViewHolder(binding.root) {


    fun vincula(produto:Produto){

        val textNome = binding.textNomeProduto
        val textDescricao = binding.textDescricao

        textNome.text = produto.nome
        textDescricao.text = produto.descricao

        textNome.setOnClickListener{

            quandoClicaNoItem(produto)
            Toast.makeText(it.context, "teste: Fui clicado", Toast.LENGTH_SHORT).show()
            Log.i("ViewHolder", "fui clicado no nome!! ${produto.nome}")

        }


    }
}


