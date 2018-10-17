package fr.eseo.dis.dauvillier.aipconnexiontest.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.lang.reflect.Array;
import java.util.List;

import static java.util.Arrays.asList;

@Entity(tableName = "projets")
public class Projets {

    public static final List<String> projets = asList("projectId", "title", "descrip", "poster", "supervisor", "confid", "students");

    @PrimaryKey
    @NonNull
    private int projectId;

    @NonNull
    private String title;

    @NonNull
    private String descrip;

    @NonNull
    private String confid;

    @NonNull
    private boolean poster;

    @NonNull
    private Array supervisor;

    @NonNull
    private String students;

    public Projets(@NonNull int projectId, @NonNull String title, @NonNull String descrip, @NonNull String confid, @NonNull boolean poster, @NonNull String supervisor) {
        this.projectId = projectId;
        this.title = title;
        this.descrip = descrip;
        this.confid = confid;
        this.poster = poster;
        this.supervisor = supervisor;
    }

    @NonNull
    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(@NonNull int projectId) {
        this.projectId = projectId;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    @NonNull
    public String getDescript() {
        return descrip;
    }

    public void setDescript(@NonNull String descrip) {
        this.descrip = descrip;
    }

    @NonNull
    public String getConfid() {
        return confid;
    }

    public void setConfid(@NonNull String confid) {
        this.confid = confid;
    }

    @NonNull
    public String getPoster() {
        return poster;
    }

    public void setPoster(@NonNull String poster) {
        this.poster = poster;
    }

    @NonNull
    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(@NonNull String supervisor) {
        this.supervisor = supervisor;
    }
}
