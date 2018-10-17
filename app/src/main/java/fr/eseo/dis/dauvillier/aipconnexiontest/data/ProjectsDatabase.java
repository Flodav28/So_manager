package fr.eseo.dis.dauvillier.aipconnexiontest.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities= {Commentaires.class, Confidenialite.class, Date.class, Eleves.class,
        Informations.class, Jury.class, Notation.class, Poster.class, Projets.class}, version=1)

public abstract class ProjectsDatabase extends RoomDatabase {

    private static ProjectsDatabase INSTANCE;

    public abstract CommentairesDao commentairesDao();

    public abstract ConfidentialiteDao confidentialiteDao();

    public abstract DateDao dateDao();

    public abstract ElevesDao elevesDao();

    public abstract InformationsDao informationsDao();

    public abstract JuryDao juryDao();

    public abstract NotationDao notationDao();

    public abstract PosterDao posterDao();

    public abstract ProjetsDao projetsDao();

    public static ProjectsDatabase getDatabase(Context context){
        /*if(INSTANCE == null) {
            //Database needs to be 'bound' to a context, identified by a sub class of RoomDatabase
            // and have a filename where the database will be stored physically on the device
            INSTANCE = Room.databaseBuilder(context, FilmographyDatabase.class, "filmography.db")
                    .addCallback(new FilmographyDatabaseCallback())
                    // For ease of use only => Need to delete this for production code
                    .allowMainThreadQueries()
                    //When migrating delete the database and recreate it
                    .fallbackToDestructiveMigration()
                    //Create the database
                    .build();
        }*/
        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE = null;
    }

}
