package fr.eseo.dis.dauvillier.aipconnexiontest.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.sql.Date;
import java.util.List;

@Dao
public interface JuryDao {

    @Query("SELECT * FROM jury WHERE id_jury = :idJury")
    Jury getJuryById(int idJury);

    @Query("SELECT * FROM jury")
    List<Jury> getAllJury();

    @Query("SELECT * FROM jury j " +
            "JOIN membre_jury mj ON mj.user = :userId AND mj.jury = j.id_jury")
    List<Jury> getJuryUtilisateur(int userId);

    @Insert
    long insertJury(Jury jury);


    @Update
    void updateJury(Jury jury);

    @Delete
    void deleteJury(Jury jury);
}
