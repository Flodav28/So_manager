package fr.eseo.dis.dauvillier.aipconnexiontest;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

@Entity(foreignKeys = {
        //@ForeignKey(entity = User.class, parentColumns = "id_user", childColumns = "ref_supervisor", onDelete = SET_NULL),
        //@ForeignKey(entity = Jury.class, parentColumns = "id_jury", childColumns = "ref_jury", onDelete = SET_NULL)
})
public class Project {

    @PrimaryKey
    @ColumnInfo(name = "id_project")
    private int idProject;

    @NonNull
    private String title;

    @Nullable
    private String descrip;

    private boolean poster;

    @Nullable
    @ColumnInfo(name = "ref_supervisor")
    private Integer refSupervisor;

    private int confid;

    @Nullable
    @ColumnInfo(name = "ref_jury")
    private Integer refJury;

    public Project(int idProject, @NonNull String title, @Nullable String descrip, boolean poster, Integer refSupervisor, int confid, Integer refJury) {
        this.idProject = idProject;
        this.title = title;
        this.descrip = descrip;
        this.poster = poster;
        this.refSupervisor = refSupervisor;
        this.confid = confid;
        this.refJury = refJury;
    }

    public int getIdProject() {
        return idProject;
    }

    public void setIdProject(int idProject) {
        this.idProject = idProject;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    @NonNull
    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(@NonNull String descrip) {
        this.descrip = descrip;
    }

    public boolean isPoster() {
        return poster;
    }

    public void setPoster(boolean poster) {
        this.poster = poster;
    }

    @Nullable
    public Integer getRefSupervisor() {
        return refSupervisor;
    }

    public void setRefSupervisor(@Nullable Integer refSupervisor) {
        this.refSupervisor = refSupervisor;
    }

    public int getConfid() {
        return confid;
    }

    public void setConfid(int confid) {
        this.confid = confid;
    }

    @Nullable
    public Integer getRefJury() {
        return refJury;
    }

    public void setRefJury(@Nullable Integer refJury) {
        this.refJury = refJury;
    }

    @Override
    public String toString() {
        return this.getTitle() + " | " + this.getConfid();
    }
}
