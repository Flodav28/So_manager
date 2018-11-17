package fr.eseo.dis.dauvillier.so_manager.Traitement;

import android.app.Activity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import fr.eseo.dis.dauvillier.so_manager.data.ProjectsDatabase;
import fr.eseo.dis.dauvillier.so_manager.data.Utilisateur;
import fr.eseo.dis.dauvillier.so_manager.data.UtilisateurDao;

public class TraitementUtilisateurDB {
    private List<Utilisateur> lUtilisateur;
    private UtilisateurDao utilisateurDao;
    private JSONObject jsonUtilisateur;
    private Utilisateur utilisateur;

    public TraitementUtilisateurDB(Activity activity,  JSONObject jsonUtilisateur){
        this.utilisateurDao=ProjectsDatabase.getDatabase(activity).utilisateurDao();
        this.lUtilisateur =this.utilisateurDao.getAllUtilisateurs();
        this.jsonUtilisateur=jsonUtilisateur;
    }

    public boolean existInDB(Utilisateur user){
        boolean isInDB=false;
        for (Utilisateur utilisateur :this.utilisateurDao.getAllUtilisateurs()){
            if (utilisateur.getSurname().equals(user.getSurname()) && utilisateur.getForename().equals(user.getForename())){
                isInDB=true;
                user.setIdUser(utilisateur.getIdUser());
            }
        }
        return isInDB;
    }

    public void insertUtilisateur(Utilisateur utilisateur){
        this.utilisateurDao.insertUtilisateur(utilisateur);

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

    public int getId(){
        int id=0;
        if(!lUtilisateur.isEmpty()) {
            int i=lUtilisateur.size()-1;
            id=lUtilisateur.get(i).getIdUser()+1;
        }else{
            id=1;
        }

        return  id;
    }

    public boolean resultOk(){
        /*boolean bool=false;
        try {
            if( this.jsonUtilisateur.get("result").equals("OK")){
                bool=true;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return bool;*/
        return true;
    }

    public void traitement(String APIname) throws JSONException {
        int id = this.getId();
        String forename=null;
        String surname=null;


            forename=(String)this.jsonUtilisateur.get("forename");
            surname=(String)this.jsonUtilisateur.get("surname");

        utilisateur  = new Utilisateur(
                id,
                forename,
                surname
        );
        if (!this.existInDB(utilisateur)) {
            this.insertUtilisateur(utilisateur);
        }
    }

    public List<Utilisateur> getlUtilisateur() {
        setlUtilisateur();
        return lUtilisateur;
    }

    public void setlUtilisateur() {
        this.lUtilisateur = this.utilisateurDao.getAllUtilisateurs();
    }

    public UtilisateurDao getUtilisateurDao() {
        return utilisateurDao;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateurDao(UtilisateurDao utilisateurDao) {
        this.utilisateurDao = utilisateurDao;
    }

    public JSONObject getJsonUtilisateur() {
        return jsonUtilisateur;
    }

    public void setJsonUtilisateur(JSONObject jsonUtilisateur) {
        this.jsonUtilisateur = jsonUtilisateur;
    }

}
