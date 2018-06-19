package android.usuario.projetoagendacompromisso;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BdAgenda extends SQLiteOpenHelper{

    public BdAgenda(Context context){
        super(context,"BdAgenda.bd" , null, 2);}


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Agenda (" +
                "_id integer primary key autoincrement not null, " +
                "descricao text," +
                "tipo text," +
                "hora text," +
                "data text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
