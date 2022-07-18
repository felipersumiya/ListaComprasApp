package br.com.felipersumiya.listacomprasapp.ui.activities.recyclerView

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.felipersumiya.listacomprasapp.databinding.ActivityProdutoBinding
import br.com.felipersumiya.listacomprasapp.model.Produto

class ListaProdutosAdapter(
    private val context :Context,
    var quandoClicaNoItem: (produto:Produto)-> Unit={}) : RecyclerView.Adapter<ListaProdutosViewHolder>() {

    private val produtos: MutableList<Produto> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListaProdutosViewHolder {

        val inflater = LayoutInflater.from(context)
        val binding = ActivityProdutoBinding.inflate(inflater, parent, false)
        return ListaProdutosViewHolder(binding, quandoClicaNoItem)

    }

    override fun onBindViewHolder(holder: ListaProdutosViewHolder, position: Int) {

        val produto = produtos[position]
        holder.vincula(produto)
    }

    override fun getItemCount(): Int {

        return produtos.size
    }

    fun atualizaLista(produtos: List<Produto>) {

        this.produtos.clear()
        this.produtos.addAll(produtos)
        notifyDataSetChanged()

    }


 }