package fr.eseo.dis.dauvillier.aipconnexiontest.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface NotationDao {

    @Query("SELECT * FROM notation WHERE id_note = :idNote")
    Notation getNoteById(int idNote);

    @Query("SELECT * FROM notation")
    List<Notation> getAllNotes();

    @Query("SELECT * FROM notation WHERE student = :idStudent")
    Notation getNoteEleve(int idStudent);

    @Insert
    long insertNote(Notation note);

    @Update
    void updateNote(Notation note);

    @Delete
    void deleteNote(Notation note);

    @Query("DELETE FROM notation WHERE student = :idStudent")
    void deleteNotesEleve(int idStudent);
}
