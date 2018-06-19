package android.usuario.projetoagendacompromisso;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import javax.naming.Context;

public class BdAgenda extends SQLiteOpenHelper{

    public BdAgenda(Context context){super((android.content.Context) context, "bdagenda", null, 2);}


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATe TABLE Agenda (" +
                "_id integer primary key autoincrement not null, " +
                "descricao text," +
                "hora text," +
                "data text)";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
