package br.com.felipersumiya.listacomprasapp.ui.activities

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.felipersumiya.listacomprasapp.R
import br.com.felipersumiya.listacomprasapp.constants.ConstantsProduto
import br.com.felipersumiya.listacomprasapp.database.AppDatabase
import br.com.felipersumiya.listacomprasapp.databinding.ActivityDetalheProdutoBinding
import br.com.felipersumiya.listacomprasapp.model.Produto

const val TAG2 = "DetalheProdutoActiviy"
class DetalheProdutoActivity :AppCompatActivity() {

    private val binding by lazy {
        ActivityDetalheProdutoBinding.inflate(layoutInflater)
    }

    private val produtoDao by lazy{
        val db = AppDatabase.instancia(this)
        db.produtoDao()
    }

    private var produtoId = 0L
    private lateinit var produto: Produto


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        buscaProduto()

    }

    private fun buscaProduto() {

        produtoId = intent.getLongExtra(ConstantsProduto.PRODUTO_ID,0L)
        Log.i(TAG2, "produtoId: $produtoId")

        var produtoCarregado = produtoDao.getById(produtoId)


        if(produtoCarregado!=null){
            Log.i(TAG2, "produto carregado: $produtoCarregado")
            preencheCampos(produtoCarregado)
        }

    }

    private fun preencheCampos(produtoCarregado:Produto) {

        binding.textDetalheNome.text = produtoCarregado.nome
        binding.textDetalheDescricao.text = produtoCarregado.descricao

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_detalhe_produto, menu)
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){


            R.id.menu_detalhe_editar ->{

                Toast.makeText(this, "Clicou no menu para editar", Toast.LENGTH_LONG).show()


            }

            R.id.menu_detalhe_remove -> {

                Toast.makeText(this, "Clicou no menu para remover", Toast.LENGTH_LONG).show()
            }



        }



        return super.onOptionsItemSelected(item)


    }

//    private fun carregaDados() {
//
//        // mudar para pegar do banco de dados
//
//        val bundleProdutoRetorno = intent.extras
//
//        bundleProdutoRetorno?.let { bundle->
//
//            binding.textDetalheNome.text = bundle.getString(ConstantsProduto.PRODUTO_NOME)
//            binding.textDetalheDescricao.text = bundle.getString(ConstantsProduto.PRODUTO_DESCRICAO)
//
//        }
//
//    }

}