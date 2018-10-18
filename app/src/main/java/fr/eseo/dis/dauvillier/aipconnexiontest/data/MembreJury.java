package fr.eseo.dis.dauvillier.aipconnexiontest.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

@Entity(tableName = "membre_jury",
        primaryKeys = {"ref_user", "ref_jury"},
        foreignKeys = {
                @ForeignKey(
                        entity = Utilisateur.class,
                        parentColumns = "id_user",
                        childColumns = "ref_user"
                ),
                @ForeignKey(
                        entity = Jury.class,
                        parentColumns = "id_jury",
                        childColumns = "ref_jury"
                )
})
public class MembreJury {

    @ColumnInfo(name = "user")
    private int user;

    @ColumnInfo(name = "jury")
    private int jury;


    public MembreJury(int user, int jury) {
        this.user = user;
        this.jury = jury;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getJury() {
        return jury;
    }

    public void setJury(int jury) {
        this.jury = jury;
    }
}
