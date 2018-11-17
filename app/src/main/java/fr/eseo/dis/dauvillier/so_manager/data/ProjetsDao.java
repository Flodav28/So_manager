package fr.eseo.dis.dauvillier.so_manager.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface ProjetsDao {

    @Query("SELECT * FROM projets WHERE id_project = :idProjet")
    Projets getProjetById(int idProjet);

    @Query("SELECT * FROM projets p " +
            " JOIN membre_jury mj ON mj.jury = p.jury" +
            " WHERE mj.user = :idUser")
    List<Projets> getAllJuryUtilisateurDuProjet(int idUser);

    @Query("SELECT * FROM projets WHERE supervisor = :idUser")
    List<Projets> getAllUtilisateursDuProjet(int idUser);

    @Query("SELECT * FROM projets")
    List<Projets> getAllProjets();
    @Query("SELECT * FROM projets Where jury= :idJury")
    List<Projets> getProjetsByIdJury(int idJury);

    @Insert
    long insertProjet(Projets projet);

    @Delete
    void deleteProjet(Projets projet);

    @Update
    void updateProjet(Projets projet);

    @Query("SELECT * FROM projets p " +
            "JOIN membre_jury mj ON mj.jury = p.jury " +
            "WHERE mj.user = :userId AND p.id_project = :projectId ")
    Projets isJuryDuPoster(int userId, int projectId);


}
