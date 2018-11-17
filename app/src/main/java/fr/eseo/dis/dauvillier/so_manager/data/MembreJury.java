package fr.eseo.dis.dauvillier.so_manager.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

@Entity(tableName = "membre_jury",
        primaryKeys = {"user", "jury"},
        foreignKeys = {
                @ForeignKey(
                        entity = Utilisateur.class,
                        parentColumns = "id_user",
                        childColumns = "user"
                ),
                @ForeignKey(
                        entity = Jury.class,
                        parentColumns = "id_jury",
                        childColumns = "jury"
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
