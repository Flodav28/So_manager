package fr.eseo.dis.dauvillier.so_manager.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities= {
        Eleves.class,
        Jury.class,
        MembreJury.class,
        Notation.class,
        Projets.class,
        Utilisateur.class
        },
        version=21
)
public abstract class ProjectsDatabase extends RoomDatabase {

    private static ProjectsDatabase INSTANCE;

    public abstract ElevesDao elevesDao();

    public abstract JuryDao juryDao();

    public abstract MembreJuryDao membreJuryDao();

    public abstract NotationDao notationDao();

    public abstract ProjetsDao projetsDao();

    public abstract UtilisateurDao utilisateurDao();

    public static ProjectsDatabase getDatabase(Context context){
        if(INSTANCE == null) {
            //Database needs to be 'bound' to a context, identified by a sub class of RoomDatabase
            // and have a filename where the database will be stored physically on the device
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ProjectsDatabase.class, "appDB")

                    // For ease of use only => Need to delete this for production code
                    .allowMainThreadQueries()
                    //When migrating delete the database and recreate it
                    .fallbackToDestructiveMigration()
                    //Create the database
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE = null;
    }

}
