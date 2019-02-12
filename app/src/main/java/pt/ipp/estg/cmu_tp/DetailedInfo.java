package pt.ipp.estg.cmu_tp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class DetailedInfo extends AppCompatActivity {

    private TextView showNome;
    private TextView showMorada;
    private TextView showCidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_info);

        showNome = findViewById(R.id.showNome);
        showMorada = findViewById(R.id.showMorada);
        showCidade = findViewById(R.id.showCidade);
        Intent intent = getIntent();
        String nome = intent.getStringExtra("nome");
        String morada = intent.getStringExtra("morada");
        String cidade = intent.getStringExtra("cidade");

        showNome.setText("Nome: " + nome);
        showMorada.setText("Morada: " + morada);
        showCidade.setText("Cidade: " + cidade);

    }

}
