package pt.ipp.estg.cmu_tp;


import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import pt.ipp.estg.cmu_tp.Database.CarregamentoDao;
import pt.ipp.estg.cmu_tp.Database.CarregamentoDao_Impl;
import pt.ipp.estg.cmu_tp.Database.CarregamentoDatabase;
import pt.ipp.estg.cmu_tp.Database.UserDao;
import pt.ipp.estg.cmu_tp.Database.UserDatabase;
import pt.ipp.estg.cmu_tp.Models.Carregamento;
import pt.ipp.estg.cmu_tp.Models.User;


public class ListaCarregamentosFragment extends AppCompatActivity {

    private TextView TxtInfo;
    private CarregamentoDatabase database;

    private CarregamentoDao cagDao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_lista_carregamentos);

        database = Room.databaseBuilder(this, CarregamentoDatabase.class, "historico-database.db")
                .allowMainThreadQueries()
                .build();

        cagDao = database.getCarregamentoDao();

        List<Carregamento> carregamentos = cagDao.getCarregamentos();

        String info = "";

        for (Carregamento cag : carregamentos){

            String nomePosto = cag.getNomePosto();
            String morada = cag.getMorada();
            String custo = cag.getCusto();

            info = info+"\n\n"+"Nome Posto: "+nomePosto+"\nMorada: "+morada+"\nCusto: "+custo;
        }

        TxtInfo = findViewById(R.id.txtListaCarregamentos);

        TxtInfo.setText(info);
    }

}
