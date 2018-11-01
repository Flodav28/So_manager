package fr.eseo.dis.dauvillier.aipconnexiontest.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

@Entity(tableName = "notation",
        foreignKeys = {
        @ForeignKey(
                entity = Eleves.class,
                parentColumns = "id_student",
                childColumns = "student"
        )
})
public class Notation {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_note")
    @NonNull
    private int idNote;


    @ColumnInfo(name = "student")
    @NonNull
    private int student;

    @Nullable
    private float note;

    public Notation(@NonNull int idNote, @NonNull int student, @Nullable  float note) {
        this.idNote = idNote;
        this.student = student;
        this.note = note;
    }

    @NonNull
    public int getIdNote() {
        return idNote;
    }

    public void setIdNote(@NonNull int idNote) {
        this.idNote = idNote;
    }

    @NonNull
    public int getStudent() {
        return student;
    }

    public void setStudent(@NonNull int student) {
        this.student = student;
    }

    @Nullable
    public float getNote() {
        return note;
    }

    public void setNote(@Nullable float note) {
        this.note = note;
    }
}
