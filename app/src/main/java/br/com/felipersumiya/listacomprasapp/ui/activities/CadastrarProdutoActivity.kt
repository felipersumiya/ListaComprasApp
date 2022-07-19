package br.com.felipersumiya.listacomprasapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import br.com.felipersumiya.listacomprasapp.R
import br.com.felipersumiya.listacomprasapp.constants.ConstantsProduto
import br.com.felipersumiya.listacomprasapp.database.AppDatabase
import br.com.felipersumiya.listacomprasapp.database.dao.ProdutoDao
import br.com.felipersumiya.listacomprasapp.databinding.ActivityCadastrarProdutoBinding
import br.com.felipersumiya.listacomprasapp.model.Produto

class CadastrarProdutoActivity : AppCompatActivity() {


    private val binding by lazy {
        ActivityCadastrarProdutoBinding.inflate(layoutInflater)
    }

    private val produtoDao: ProdutoDao by lazy {

        val db = AppDatabase.instancia(this)
        db.produtoDao()

    }

    private var produtoId = 0L

    private lateinit var produto: Produto


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //Inicializar produto caso seja uma alteração

        inicializaAtividade()


        val buttonSave = binding.buttonCadastro

        buttonSave.setOnClickListener {

            Toast.makeText(this, "Foi clicado no botão", Toast.LENGTH_LONG).show()
            val editTextNome = binding.editCadastroNome.text.toString()
            val editTexDescricao = binding.editCadastroDescricao.text.toString()

            if(!::produto.isInitialized){
               //Se o produto não foi inicializado é porque faremos uma inserção de novo produto

                val produtoNovo = Produto(0L, editTextNome, editTexDescricao, null)
                Log.i("CadastrarProdutoActivity", "produto: $produtoNovo)")

                produtoDao.insert(produtoNovo)
                finish()
            }else{
                //Se produto já foi inicializado, então faremos um update

                var produtoAlterado = Produto(produto.id, editTextNome, editTexDescricao,null)
                produtoDao.insert(produtoAlterado)

                Log.i("CadastrarProdutoActivity", "update: produto: $produto")
                finish()

            }


        }


    }

    override fun onResume() {
        super.onResume()
    }

    private fun inicializaAtividade() {

        produtoId = intent.getLongExtra(ConstantsProduto.PRODUTO_ID, 0L)
//Pensar na lógica correta

        if(produtoId != 0L){

            produto = produtoDao.getById(produtoId)

            }


        if(::produto.isInitialized) {

            if (produto.id == produtoId) {
                //altera a tela
                title = "Alterar Produto"
                binding.editCadastroNome.setText(produto.nome)
                binding.editCadastroDescricao.setText(produto.descricao)
            }

        }

    }

}