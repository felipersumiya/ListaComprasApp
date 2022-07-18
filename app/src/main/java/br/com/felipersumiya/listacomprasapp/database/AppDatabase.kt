package br.com.felipersumiya.listacomprasapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.felipersumiya.listacomprasapp.database.dao.ProdutoDao
import br.com.felipersumiya.listacomprasapp.model.Produto

@Database(entities = [Produto::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun produtoDao(): ProdutoDao

    companion object{

        @Volatile
        private var db: AppDatabase? = null
        fun instancia(context: Context) : AppDatabase{

            return db ?: Room.databaseBuilder(context, AppDatabase::class.java, "listProdutos.db")
                .addMigrations(MIGRATION_1_2)
                .allowMainThreadQueries()
                .build().also {
                    db = it
                }


        }



    }
}