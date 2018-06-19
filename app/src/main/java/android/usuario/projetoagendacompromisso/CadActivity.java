package android.usuario.projetoagendacompromisso;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadActivity extends AppCompatActivity {

    EditText ctDescricao, ctTipo, ctHora, ctData;
    Button btSalvar;
    Cursor cursor;
    String codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad);

        ctDescricao = (EditText) findViewById(R.id.ctDescricao);
        ctTipo = (EditText) findViewById(R.id.ctTipo);
        ctHora = (EditText) findViewById(R.id.ctHora);
        ctData = (EditText) findViewById(R.id.ctData);
        btSalvar = (Button) findViewById(R.id.btSalvar);

        if (getIntent().getStringExtra("situacao").equals("alterar")) {
            Controller c = new Controller(getBaseContext());
            codigo = getIntent().getStringExtra("codigo");
            cursor = c.buscaID(Integer.parseInt(codigo));
            ctDescricao.setText(cursor.getString(cursor.getColumnIndexOrThrow("descricao")));
            ctTipo.setText(cursor.getString(cursor.getColumnIndexOrThrow("tipo")));
            ctHora.setText(cursor.getString(cursor.getColumnIndexOrThrow("hora")));
            ctData.setText(cursor.getString(cursor.getColumnIndexOrThrow("data")));
        }

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Controller c = new Controller(getBaseContext());
                String descricao = ctDescricao.getText().toString();
                String tipo = ctTipo.getText().toString();
                String hora = ctHora.getText().toString();
                String data = ctData.getText().toString();
                String result;
                if (getIntent().getStringExtra("situacao").equals("inserir")) {
                    result = c.inserir(descricao, tipo, hora, data);
                } else {
                    result = c.alterar(Integer.parseInt(codigo), descricao, tipo, hora, data);
                }
                Toast.makeText(CadActivity.this, result, Toast.LENGTH_LONG).show();

                Intent i = new Intent(CadActivity.this, AgendaActivity.class);
                startActivity(i);
            }
        });

    }
}
