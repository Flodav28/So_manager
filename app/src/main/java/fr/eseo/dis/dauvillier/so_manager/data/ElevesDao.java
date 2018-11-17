package fr.eseo.dis.dauvillier.so_manager.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface ElevesDao {

    @Query("SELECT * FROM eleves WHERE id_student = :idStudent")
    Eleves getEleveById(int idStudent);

    @Query("SELECT * FROM eleves")
    List<Eleves> getAllEleves();

    @Query("SELECT * FROM eleves WHERE project = :idProject")
    List<Eleves> getElevesDuProjet(int idProject);

    @Insert
    long insertEleve(Eleves eleve);

    @Update
    void updateEleve(Eleves eleve);

    @Delete
    void deleteEleve(Eleves eleve);
}
