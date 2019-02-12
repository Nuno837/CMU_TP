package pt.ipp.estg.cmu_tp;

import android.app.ProgressDialog;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import pt.ipp.estg.cmu_tp.Database.CarregamentoDao;
import pt.ipp.estg.cmu_tp.Database.CarregamentoDatabase;
import pt.ipp.estg.cmu_tp.Models.Carregamento;

public class AddCarregamentoActivity extends AppCompatActivity {

    private EditText edtnomePosto;
    private EditText edtmorada;
    private EditText edtcusto;

    private Button btCancelH;
    private Button btRegisterH;

    private CarregamentoDao carregamentoDao;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_carregamento);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Registering...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edtnomePosto = findViewById(R.id.nomepostoinput);
        edtmorada = findViewById(R.id.moradainput);
        edtcusto = findViewById(R.id.custoinput);


        btCancelH = findViewById(R.id.btCancelH);
        btRegisterH = findViewById(R.id.btRegisterH);

        carregamentoDao = Room.databaseBuilder(this, CarregamentoDatabase.class, "historico-database.db")
                .allowMainThreadQueries()
                .build()
                .getCarregamentoDao();

        btCancelH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddCarregamentoActivity.this, UserActivity.class));
                finish();
            }
        });

        btRegisterH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isEmpty()) {

                    progressDialog.show();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Carregamento carregamento = new Carregamento(edtnomePosto.getText().toString(), edtmorada.getText().toString(), edtcusto.getText().toString());
                            carregamentoDao.insert(carregamento);
                            progressDialog.dismiss();
                            startActivity(new Intent(AddCarregamentoActivity.this, HistoricoActivity.class));
                        }
                    }, 1000);

                } else {
                    Toast.makeText(AddCarregamentoActivity.this, "Empty Fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isEmpty() {
        if (TextUtils.isEmpty(edtnomePosto.getText().toString()) ||
                TextUtils.isEmpty(edtmorada.getText().toString()) || TextUtils.isEmpty(edtcusto.getText().toString())
        ) {
            return true;
        } else {
            return false;
        }
    }
}
