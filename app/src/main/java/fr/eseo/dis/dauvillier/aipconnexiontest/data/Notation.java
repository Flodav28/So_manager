package fr.eseo.dis.dauvillier.aipconnexiontest.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

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
    private int idNote;

    @ColumnInfo(name = "student")
    private int student;

    private float note;

    public Notation(int idNote, int student, float note) {
        this.idNote = idNote;
        this.student = student;
        this.note = note;
    }

    public int getIdNote() {
        return idNote;
    }

    public void setIdNote(int idNote) {
        this.idNote = idNote;
    }

    public int getStudent() {
        return student;
    }

    public void setStudent(int student) {
        this.student = student;
    }

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }
}
