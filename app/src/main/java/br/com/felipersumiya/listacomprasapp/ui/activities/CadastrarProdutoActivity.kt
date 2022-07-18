package br.com.felipersumiya.listacomprasapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import br.com.felipersumiya.listacomprasapp.R
import br.com.felipersumiya.listacomprasapp.database.AppDatabase
import br.com.felipersumiya.listacomprasapp.database.dao.ProdutoDao
import br.com.felipersumiya.listacomprasapp.databinding.ActivityCadastrarProdutoBinding
import br.com.felipersumiya.listacomprasapp.model.Produto

class CadastrarProdutoActivity : AppCompatActivity() {


    private val binding by lazy {
        ActivityCadastrarProdutoBinding.inflate(layoutInflater)
    }

    private val produtoDao:ProdutoDao by lazy {

        val db = AppDatabase.instancia(this)
        db.produtoDao()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        val buttonSave = binding.buttonCadastro

        buttonSave.setOnClickListener {

            Toast.makeText(this, "Foi clicado no botão", Toast.LENGTH_LONG).show()
            val editTextNome = binding.editCadastroNome.text.toString()
            val editTexDescricao = binding.editCadastroDescricao.text.toString()


            val produtoNovo = Produto(0L,editTextNome, editTexDescricao,null)
            Log.i("CadastrarProdutoActivity", "produto: $produtoNovo)")

            produtoDao.insert(produtoNovo)
            finish()

        }


    }
}