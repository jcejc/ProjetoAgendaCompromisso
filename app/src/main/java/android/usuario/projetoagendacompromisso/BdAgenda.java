package android.usuario.projetoagendacompromisso;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BdAgenda extends SQLiteOpenHelper{

    public BdAgenda(Context context){
        super(context,"BdAgenda.bd" , null, 1);}


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS Agenda (" +
                "_id integer primary key autoincrement not null, " +
                "descricao VARCHAR(50) NOT NULL," +
                "hora VARCHAR(5) NOT NULL," +
                "data VARCHAR(10) NOT NULL)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
