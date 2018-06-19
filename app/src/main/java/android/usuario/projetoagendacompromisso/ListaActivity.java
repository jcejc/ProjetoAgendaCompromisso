package android.usuario.projetoagendacompromisso;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ListaActivity extends AppCompatActivity {

    ListView layout_lista;
    Cursor cursor;
    TextView id, descricao, hora, data;
    String codigo;
    AlertDialog alerta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        Controller c = new Controller(getBaseContext());
        cursor = c.lista();
        String[] campos = new String[]{"_id", "descricao", "tipo", "hora", "data"};
        int[] idViews = new int[]{R.id.id,R.id.descricao,R.id.tipo,R.id.hora,R.id.data};

        SimpleCursorAdapter ad = new SimpleCursorAdapter(getBaseContext(),R.layout.layout_lista,cursor,campos,idViews, 0);

        layout_lista = (ListView)findViewById(R.id.ListaDados);
        layout_lista.setAdapter(ad);

        layout_lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapterView,final View view, int i, long l) {
                cursor.moveToPosition(i);
                codigo = cursor.getString(cursor.getColumnIndexOrThrow("_id"));
                AlertDialog.Builder builder = new AlertDialog.Builder(ListaActivity.this);
                builder.setMessage("Qual Função Deseja Realizar?");
                builder.setPositiveButton("Alterar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(ListaActivity.this,CadActivity.class);
                        intent.putExtra("situacao", "alterar");
                        intent.putExtra("codigo",codigo);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("Excluir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(ListaActivity.this);
                        builder.setMessage("Deseja Excluir o Registro");
                        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Controller c = new Controller(getBaseContext());
                                String result = c.excluir(Integer.parseInt(codigo));
                                Toast.makeText(ListaActivity.this, result, Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(getIntent());
                            }
                        });
                        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        alerta = builder.create();
                        alerta.show();
                    }
                });
                alerta = builder.create();
                alerta.show();


            }
        });
    }
}
