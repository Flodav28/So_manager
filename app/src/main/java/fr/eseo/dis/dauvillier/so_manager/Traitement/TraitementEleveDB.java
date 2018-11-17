package fr.eseo.dis.dauvillier.so_manager.Traitement;

import android.app.Activity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import fr.eseo.dis.dauvillier.so_manager.data.Eleves;
import fr.eseo.dis.dauvillier.so_manager.data.ElevesDao;
import fr.eseo.dis.dauvillier.so_manager.data.ProjectsDatabase;

public class TraitementEleveDB {
    private List<Eleves> leleves;
    private ElevesDao elevesDao;
    private JSONObject jsonEleve;





    public TraitementEleveDB(Activity activity, JSONObject jsonEleve){
        this.elevesDao= ProjectsDatabase.getDatabase(activity).elevesDao();
        this.leleves =this.elevesDao.getAllEleves();
        this.jsonEleve=jsonEleve;

    }
    public  boolean   existInDB(Eleves eleve){
        boolean isInDB=false;
        for (Eleves eleves :this.leleves){
            if (eleves.getSurname().equals(eleve.getSurname()) && eleves.getForename().equals(eleve.getForename())){
                isInDB=true;
                eleves=eleve;
                this.elevesDao.updateEleve(eleves);
            }
        }
        return isInDB;
    }
    public void insertEleve(Eleves eleve){
        this.elevesDao.insertEleve(eleve);

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
            if( this.jsonEleve.get("result").equals("OK")){
                bool=true;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return bool;
    }
    public void traitement(int idProjet) throws JSONException {

        Eleves eleve = new Eleves(
                (int) this.jsonEleve.getInt("userId"),
                (String)this.jsonEleve.get("forename"),
                (String)this.jsonEleve.get("surname"),
                null,
                idProjet
        );

        if (!this.existInDB(eleve)) {
            this.insertEleve(eleve);
        }
    }

    public List<Eleves> getLeleves() {
        return leleves;
    }

    public void setLeleves(List<Eleves> leleves) {
        this.leleves = leleves;
    }

    public ElevesDao getElevesDao() {
        return elevesDao;
    }

    public void setElevesDao(ElevesDao elevesDao) {
        this.elevesDao = elevesDao;
    }

    public JSONObject getJsonEleve() {
        return jsonEleve;
    }

    public void setJsonEleve(JSONObject jsonEleve) {
        this.jsonEleve = jsonEleve;
    }
}
