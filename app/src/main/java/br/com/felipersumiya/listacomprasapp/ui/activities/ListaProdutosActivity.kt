package br.com.felipersumiya.listacomprasapp.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.felipersumiya.listacomprasapp.constants.ConstantsProduto
import br.com.felipersumiya.listacomprasapp.database.AppDatabase
import br.com.felipersumiya.listacomprasapp.database.listProdutos
import br.com.felipersumiya.listacomprasapp.databinding.ActivityListaProdutosBinding
import br.com.felipersumiya.listacomprasapp.ui.activities.recyclerView.ListaProdutosAdapter

const val TAG= "ListaProdutosActivity"

class ListaProdutosActivity : AppCompatActivity() {

    private val adapter = ListaProdutosAdapter(this)

    private val binding by lazy {
        ActivityListaProdutosBinding.inflate(layoutInflater)
    }

    private val produtoDao by lazy{
        val db = AppDatabase.instancia(this)
        db.produtoDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraRecyclerView()
        adicionaDadosNaLista()
        configuraFab()
        Log.i(TAG,"Entrou no onCreate")


    }

    override fun onResume() {
        super.onResume()

        Log.i(TAG, "Entrou no onResume")
        adicionaDadosNaLista()

    }

    private fun configuraFab() {

        val fab = binding.fab
        fab.setOnClickListener{

            Toast.makeText(this, "Evento de clique no FAB!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, CadastrarProdutoActivity::class.java)
            startActivity(intent)
        }

    }

    private fun adicionaDadosNaLista() {

        val listaDB =  produtoDao.getAll()
        adapter.atualizaLista(listaDB)

    }

    private fun configuraRecyclerView() {

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.adapter = adapter

        adapter.quandoClicaNoItem = { produto ->

            Log.i("ListaProdutosActivity","objeto clicado: $produto")
            Toast.makeText(this, "Deu certo !!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, DetalheProdutoActivity::class.java)

            intent.putExtra(ConstantsProduto.PRODUTO_ID,produto.id)
//            val bundleProduto = Bundle()
//            bundleProduto.putLong(ConstantsProduto.PRODUTO_ID, produto.id)
//            bundleProduto.putString(ConstantsProduto.PRODUTO_NOME, produto.nome)
//            bundleProduto.putString(ConstantsProduto.PRODUTO_DESCRICAO, produto.descricao)

            //intent.putExtras(bundleProduto)

            startActivity(intent)
        }

    }


}