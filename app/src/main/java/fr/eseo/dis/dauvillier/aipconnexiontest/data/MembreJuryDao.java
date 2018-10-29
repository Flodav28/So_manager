package fr.eseo.dis.dauvillier.aipconnexiontest.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface MembreJuryDao {

    @Query("SELECT * FROM membre_jury")
    List<MembreJury> getAllMembresJury();

    @Query("SELECT * FROM membre_jury WHERE user == :idMembreJury")
    MembreJury getMembreJuryById(int idMembreJury);

    @Update
    void updateMembreJury(MembreJury membreJury);

    @Insert

    long insertMembreJury(MembreJury membreJury);

    @Delete
    void deleteMembreJury(MembreJury membreJury);
}
