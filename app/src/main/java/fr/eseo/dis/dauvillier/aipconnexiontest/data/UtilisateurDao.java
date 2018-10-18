package fr.eseo.dis.dauvillier.aipconnexiontest.data;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

public interface UtilisateurDao{

        @Query("SELECT * FROM utilisateur")
        List<Utilisateur> getAllUtilisateurs();

        @Query("SELECT * FROM utilisateur WHERE id_user= :id")
        Utilisateur getUtilisateurById(int id);

        @Query("SELECT * FROM utilisateur WHERE forename = :forename AND surname = :surname")
        Utilisateur getUtilisateurParNom(String forename, String surname);

        @Insert
        long insertUtilisateur(Utilisateur utilisateur);

        @Update
        void updateUtilisateur(Utilisateur utilisateur);

        @Delete
        void deleteUtilisateur(Utilisateur user);
}
