package fr.eseo.dis.dauvillier.so_manager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.dis.dauvillier.so_manager.Traitement.TraitementEleveDB;
import fr.eseo.dis.dauvillier.so_manager.Traitement.TraitementNoteDB;
import fr.eseo.dis.dauvillier.so_manager.Traitement.TraitementProjetDB;
import fr.eseo.dis.dauvillier.so_manager.Traitement.TraitementUtilisateurDB;
import fr.eseo.dis.dauvillier.so_manager.data.Projets;

public class ResultNOTE {
    private static final String  API="NOTE";

    public String data;
    public MasterActivity activity;
    public JSONObject jsonNoteMain;
    public TraitementNoteDB traitementNoteDB;





    public JSONObject getJsonNoteMain() {
        return jsonNoteMain;
    }

    public void setJsonNoteMain(JSONObject jsonNoteMain) {
        this.jsonNoteMain = jsonNoteMain;
    }

    public TraitementNoteDB getTraitementNoteDB() {
        return traitementNoteDB;
    }

    public void setTraitementNoteDB(TraitementNoteDB traitementNoteDB) {
        this.traitementNoteDB = traitementNoteDB;
    }

    public ResultNOTE(String data, MasterActivity activity) throws JSONException {
        this.data=data;
        this.activity=activity;
        this.jsonNoteMain= TraitementProjetDB.getJsonObject(data);
        this.traitementNoteDB=new TraitementNoteDB(activity,this.jsonNoteMain);
        getResultRequest();
    }


    public JSONObject getJson(String data) throws JSONException {

        return  new JSONObject(data);
    }
    public void getResultRequest()   {
        String responseValues=null;
        TraitementUtilisateurDB traitementUtilisateurDB;
        TraitementEleveDB traitementEleveDB;
        List<Projets> lprojet ;
        try {
            if(this.traitementNoteDB.resultOk()){
                responseValues="OK";
                JSONArray listeJsonNote = jsonNoteMain.getJSONArray("notes");
                JSONObject jsonNote=null;

                for(int i = 0; i < listeJsonNote.length()-1; i++) {
                    jsonNote= listeJsonNote.getJSONObject(i);

                    traitementNoteDB = new TraitementNoteDB(activity,jsonNote);
                    traitementNoteDB.traitement();
                    traitementNoteDB.getlNotes();

                }
            }else{
                responseValues="KO";
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        activity.responseNote(responseValues);
    }


}
