package fr.eseo.dis.dauvillier.aipconnexiontest.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ProjetsDao {

    @Query("SELECT * FROM projets")
    public List<Projets> findAllProjets();

}
