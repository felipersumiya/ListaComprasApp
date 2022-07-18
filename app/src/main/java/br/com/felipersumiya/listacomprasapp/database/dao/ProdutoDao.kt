package br.com.felipersumiya.listacomprasapp.database.dao

import androidx.room.*
import br.com.felipersumiya.listacomprasapp.model.Produto


@Dao
interface ProdutoDao {


    @Query("SELECT * FROM Produto")
    fun getAll() : List<Produto>

    @Query("SELECT * FROM Produto WHERE id= :produtoId")
    fun getById(produtoId:Long) : Produto

    @Insert
    fun insert(produto:Produto)

    @Delete
    fun delete(produto: Produto)

    @Update
    fun update(produto: Produto)
}