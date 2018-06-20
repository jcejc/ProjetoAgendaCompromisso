package android.usuario.projetoagendacompromisso;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class listarCompromissosDoDia extends AppCompatActivity {

    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_compromissos_do_dia);

        Controller c = new Controller(getBaseContext());

    }
}
