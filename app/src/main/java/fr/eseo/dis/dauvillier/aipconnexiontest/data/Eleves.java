package fr.eseo.dis.dauvillier.aipconnexiontest.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

@Entity(tableName = "eleves",
        foreignKeys = {
        @ForeignKey(
                entity = Projets.class,
                parentColumns = "id_project",
                childColumns = "project"
        )
})
public class Eleves {

    @PrimaryKey
    @ColumnInfo(name = "id_student")
    private int idStudent;

    @NonNull
    private String forename;

    @NonNull
    private String surname;

    @Nullable
    @ColumnInfo(name = "average_note")
    private Float averageNote;

    @Nullable
    @ColumnInfo(name = "project")
    private Integer project;

    public Eleves(int idStudent, @NonNull String forename, @NonNull String surname,
                  @Nullable Float averageNote, @Nullable Integer project) {
        this.idStudent = idStudent;
        this.forename = forename;
        this.surname = surname;
        this.averageNote = averageNote;
        this.project = project;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    @NonNull
    public String getForename() {
        return forename;
    }

    public void setForename(@NonNull String forename) {
        this.forename = forename;
    }

    @NonNull
    public String getSurname() {
        return surname;
    }

    public void setSurname(@NonNull String surname) {
        this.surname = surname;
    }

    @Nullable
    public Float getAverageNote() {
        return averageNote;
    }

    public void setAverageNote(@Nullable Float averageNote) {
        this.averageNote = averageNote;
    }

    @Nullable
    public Integer getProject() {
        return project;
    }

    public void setProject(@Nullable Integer project) {
        this.project = project;
    }
}
