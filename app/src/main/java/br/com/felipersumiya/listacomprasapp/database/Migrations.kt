package br.com.felipersumiya.listacomprasapp.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


val MIGRATION_1_2 = object :Migration(1,2){
    override fun migrate(database: SupportSQLiteDatabase) {
       database.execSQL("DELETE FROM Produto")
    }

}