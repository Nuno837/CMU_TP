package pt.ipp.estg.cmu_tp.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import pt.ipp.estg.cmu_tp.Models.Carregamento;


@Dao
public interface CarregamentoDao {

    @Query("SELECT * FROM Carregamento where nomePosto= :nomePosto and morada= :morada")
    Carregamento getCarregamento(String nomePosto, String morada);

    @Insert
    void insert(Carregamento carregamento);

    @Update
    void update(Carregamento carregamento);

    @Delete
    void delete(Carregamento carregamento);
}
