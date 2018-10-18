package fr.eseo.dis.dauvillier.aipconnexiontest.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

@Entity(tableName = "jury")
public class Jury {

    @PrimaryKey
    @ColumnInfo(name = "id_jury")
    private int idJury;

    @NonNull
    private Date date;

    public Jury(int idJury, @NonNull Date date) {
        this.idJury = idJury;
        this.date = date;
    }

    public int getIdJury() {
        return idJury;
    }

    public void setIdJury(int idJury) {
        this.idJury = idJury;
    }

    @NonNull
    public Date getDate() {
        return date;
    }

    public void setDate(@NonNull Date date) {
        this.date = date;
    }
}
