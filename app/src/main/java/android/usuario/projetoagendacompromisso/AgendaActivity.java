package android.usuario.projetoagendacompromisso;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AgendaActivity extends AppCompatActivity {

    Button btCadastro, btLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        btCadastro = (Button) findViewById(R.id.btCadastro);
        btCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(AgendaActivity.this, CadActivity.class);
                i.putExtra("situacao", "inserir");
                startActivity(i);
            }
        });

        btLista = (Button) findViewById(R.id.btLista);
        btLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(AgendaActivity.this, ListaActivity.class);
                startActivity(i);
            }
        });
    }
}
