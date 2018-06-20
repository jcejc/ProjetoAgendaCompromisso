package android.usuario.projetoagendacompromisso;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;

public class listarCompromissosDoDia extends AppCompatActivity {

    ListView layout_lista;
    Cursor cursor;
    TextView id, descricao, hora, data;
    String codigo;
    AlertDialog alerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_compromissos_do_dia);

        Controller c = new Controller(getBaseContext());
        long d= System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dataCerta = sdf.format(d);
        String date= String.valueOf(dataCerta);
        cursor = c.listarCompromissosdodia(date);
        String[] campos = new String[]{"_id", "descricao", "tipo", "hora", "data"};
        int[] idViews = new int[]{R.id.id,R.id.descricao,R.id.tipo,R.id.hora,R.id.data};

        SimpleCursorAdapter ad = new SimpleCursorAdapter(getBaseContext(),R.layout.layout_lista,cursor,campos,idViews, 0);

        layout_lista = (ListView)findViewById(R.id.listarDoDia);
        layout_lista.setAdapter(ad);

        layout_lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapterView, final View view, int i, long l) {
                cursor.moveToPosition(i);
                codigo = cursor.getString(cursor.getColumnIndexOrThrow("_id"));
                AlertDialog.Builder builder = new AlertDialog.Builder(getBaseContext());
                builder.setMessage("Qual Função Deseja Realizar?");
                builder.setPositiveButton("Alterar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(getBaseContext(),CadActivity.class);
                        intent.putExtra("situacao", "alterar");
                        intent.putExtra("codigo",codigo);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("Excluir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getBaseContext());
                        builder.setMessage("Deseja Excluir o Registro");
                        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Controller c = new Controller(getBaseContext());
                                String result = c.excluir(Integer.parseInt(codigo));
                                Toast.makeText(getBaseContext(), result, Toast.LENGTH_SHORT).show();
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
