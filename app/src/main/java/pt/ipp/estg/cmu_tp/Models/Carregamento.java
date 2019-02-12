package pt.ipp.estg.cmu_tp.Models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity
public class Carregamento implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private String nomePosto;
    private String morada;
    private String custo;


    public Carregamento(String nomePosto, String morada, String custo) {
        this.nomePosto = nomePosto;
        this.morada = morada;
        this.custo = custo;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String getNomePosto() {
        return nomePosto;
    }

    public void setNomePosto(String nomePosto) {
        this.nomePosto = nomePosto;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getCusto() {
        return custo;
    }

    public void setCusto(String custo) {
        this.custo = custo;
    }

    @Override
    public String toString() {
        return "Carregamento{" +
                "id=" + id +
                ", nomePosto='" + nomePosto + '\'' +
                ", morada='" + morada + '\'' +
                ", custo='" + custo + '\'' +
                '}';
    }
}


