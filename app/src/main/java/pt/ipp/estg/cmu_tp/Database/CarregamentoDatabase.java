package pt.ipp.estg.cmu_tp.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import pt.ipp.estg.cmu_tp.Models.Carregamento;

@Database(entities = {Carregamento.class}, version = 1, exportSchema = false)

public abstract class CarregamentoDatabase extends RoomDatabase {

    public abstract CarregamentoDao getCarregamentoDao();

}
