package android.usuario.projetoagendacompromisso;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Controller {
    SQLiteDatabase db;
    BdAgenda banco;

    public Controller(Context context) {
        banco = new BdAgenda(context);
    }

    public String inserir(String descricao, String tipo, String hora, String data) {
        ContentValues v;
        long valores;

        db = banco.getWritableDatabase();

        v = new ContentValues();
        v.put("descricao", descricao);
        v.put("tipo", tipo);
        v.put("hora", hora);
        v.put("data", data);

        valores = db.insert("Agenda", null, v);
        db.close();

        if (valores == -1) {
            return "Erro ao inserir o registro";
        } else {
            return "Registro gravado com sucesso";
        }
    }

    public Cursor lista() {
        Cursor cursor;
        String[] campos = {"_id", "descricao", "tipo", "hora", "data"};
        db = banco.getReadableDatabase();
        cursor = db.query("Agenda", campos, null, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor buscaID(int i) {
        Cursor cursor;
        String[] campos = {"_id", "descricao", "tipo", "hora", "data"};
        String where = "_id=" + i;
        db = banco.getReadableDatabase();
        cursor = db.query("Agenda", campos, where, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public String alterar(int id, String descricao, String tipo, String hora, String data) {
        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();

        //_id =3
        where = "_id=" + id;

        //A chave se refere a coluna do db, que é o primeiro parametro
        valores  = new ContentValues();
        valores.put("_id",id);
        valores.put("descricao", descricao);
        valores.put("tipo", tipo);
        valores.put("hora", hora);
        valores.put("data", data);
        //UPDATE agenda set descricao = "descricao", tipo = "tipo"
        int result = db.update("Agenda", valores, where, null);
        db.close();

        if(result == -1){
            return "Erro ao alterar o registro";
        }else{
            return  "Registro alterado com sucesso!!!";
        }
    }

    public String excluir(int id) {
        String where;
        db = banco.getWritableDatabase();
        where = "_id=" + id;
        int result = db.delete("Agenda", where, null);
        db.close();

        if (result == -1) {
            return "Erro ao excluir o registro";
        } else {
            return "Registro excluido com sucesso";
        }
    }

    public Cursor listaDoDia(String data) {
        Cursor cursor;
        String[] campos = {"_id", "descricao", "tipo", "hora", "data"};
        String where = "data"+ data;
        db = banco.getReadableDatabase();
        cursor = db.query("Agenda", campos, null, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }
}
