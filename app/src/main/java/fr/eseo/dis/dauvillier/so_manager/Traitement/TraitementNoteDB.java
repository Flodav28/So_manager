package fr.eseo.dis.dauvillier.so_manager.Traitement;

import android.app.Activity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import fr.eseo.dis.dauvillier.so_manager.data.Eleves;
import fr.eseo.dis.dauvillier.so_manager.data.ElevesDao;
import fr.eseo.dis.dauvillier.so_manager.data.Notation;
import fr.eseo.dis.dauvillier.so_manager.data.NotationDao;
import fr.eseo.dis.dauvillier.so_manager.data.ProjectsDatabase;

public class TraitementNoteDB {
    private List<Eleves> leleves;
    private ElevesDao elevesDao;
    private Notation note;
    private NotationDao noteDao;
    private JSONObject jsonNote;
    private List<Notation> lNotes;

    public TraitementNoteDB(Activity activity, JSONObject jsonNote){

        this.elevesDao= ProjectsDatabase.getDatabase(activity).elevesDao();
        this.noteDao=ProjectsDatabase.getDatabase(activity).notationDao();

        this.leleves =this.elevesDao.getAllEleves();
        this.lNotes=this.noteDao.getAllNotes();
        this.jsonNote=jsonNote;

    }

    public  boolean   existInDB(Eleves eleve){

        boolean isInDB=false;

        for (Eleves eleves :this.leleves){
            if (eleves.getSurname().equals(eleve.getSurname()) && eleves.getForename().equals(eleve.getForename())){

                isInDB=true;
                eleves.setAverageNote(eleve.getAverageNote());
                this.elevesDao.updateEleve(eleves);
            }
        }
        return isInDB;
    }
    public  boolean   existInDB(Notation note){

        boolean isInDB=false;

        for (Notation notation :this.lNotes){

            if (notation.getStudent()==note.getStudent()){
                isInDB=true;
                notation.setNote(note.getNote());
                this.noteDao.updateNote(notation);
            }
        }
        return isInDB;
    }
    public void insertEleve(Eleves eleve){
        this.elevesDao.insertEleve(eleve);

    }
    public void insertNote(Notation notation){
        this.noteDao.insertNote(notation);

    }
    public static JSONObject getJsonObject(String data){
        JSONObject jsonObj=null;
        try {
            jsonObj = new JSONObject(data);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObj;
    }

    public boolean resultOk(){
        boolean bool=false;
        try {
            if( this.jsonNote.get("result").equals("OK")){
                bool=true;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return bool;
    }

    public List<Notation> getlNotes() {
         setlNotes(this.noteDao.getAllNotes());
         return this.noteDao.getAllNotes();
    }

    public void setlNotes(List<Notation> lNotes) {
        this.lNotes = lNotes;
    }

    public void traitement() throws JSONException {

        Eleves eleve = new Eleves(
                (int) this.jsonNote.getInt("userId"),
                (String)this.jsonNote.get("forename"),
                (String)this.jsonNote.get("surname"),
                (Float)Float.parseFloat(this.jsonNote.getString("avgNote")),
                null

        );
        String avgnote=null;
        avgnote=this.jsonNote.getString("mynote");
        Float avg=null;
        if(avgnote.equals("null")){
            avg=-1f;
        }else{
            avg=Float.parseFloat(avgnote);
        }

        Notation note = new Notation(
            getId(),
                (int) this.jsonNote.getInt("userId"),
                avg

        );

        if (!this.existInDB(eleve)) {
            this.insertEleve(eleve);
        }
        if (!this.existInDB(note)) {
            this.insertNote(note);
        }

    }
    public int getId() {
        int id = 0;
        if (!lNotes.isEmpty()) {
            int i = lNotes.size() - 1;
            id = lNotes.get(i).getIdNote() + 1;
        } else {
            id = 1;
        }
        return id;
    }


}
