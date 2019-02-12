package pt.ipp.estg.cmu_tp;

import android.app.ProgressDialog;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import pt.ipp.estg.cmu_tp.Database.CarregamentoDao;
import pt.ipp.estg.cmu_tp.Database.CarregamentoDatabase;
import pt.ipp.estg.cmu_tp.Models.Carregamento;
import pt.ipp.estg.cmu_tp.Models.User;

public class HistoricoActivity extends AppCompatActivity {

    private Button btAddCarregamento;
    private CarregamentoDatabase carregamentoDatabase;
    private Carregamento carregamento;
    private CarregamentoDao carregamentoDao;
    private ProgressDialog progressDialog;

    private TextView tvNomePosto;
    private TextView tvMorada;
    private TextView tvCusto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_carregamentos);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Check User...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        carregamentoDatabase = Room.databaseBuilder(this, CarregamentoDatabase.class, "historico-database.db")
                .allowMainThreadQueries()
                .build();

        carregamentoDao = carregamentoDatabase.getCarregamentoDao();


        carregamento = (Carregamento) getIntent().getSerializableExtra("Carregamento");

        tvNomePosto = findViewById(R.id.tvNomePosto);
        tvMorada = findViewById(R.id.tvMorada);
        tvCusto = findViewById(R.id.tvCusto);

        if (carregamento != null) {
            tvNomePosto.setText("Custo "+carregamento.getNomePosto());
            tvMorada.setText("Custo "+carregamento.getMorada());
            tvCusto.setText(" Custo "+carregamento.getCusto());

        }


        btAddCarregamento = findViewById(R.id.btAddCarregamento);

        btAddCarregamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HistoricoActivity.this, AddCarregamentoActivity.class));
            }
        });

    }

}
