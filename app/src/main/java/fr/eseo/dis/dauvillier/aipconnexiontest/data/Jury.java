package fr.eseo.dis.dauvillier.aipconnexiontest.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "jury")
public class Jury {

    @PrimaryKey
    @NonNull
    private int idJury;

    @NonNull
    private String date;

    @NonNull
    private String info;

    public Jury(@NonNull int idJury, @NonNull String date, @NonNull String info) {
        this.idJury = idJury;
        this.date = date;
        this.info = info;
    }

    @NonNull
    public int getIdJury() {
        return idJury;
    }

    public void setIdJury(@NonNull int idJury) {
        this.idJury = idJury;
    }

    @NonNull
    public String getDate() {
        return date;
    }

    public void setDate(@NonNull String date) {
        this.date = date;
    }

    @NonNull
    public String getInfo() {
        return info;
    }

    public void setInfo(@NonNull String info) {
        this.info = info;
    }
}
