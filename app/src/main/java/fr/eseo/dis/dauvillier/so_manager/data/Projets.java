package fr.eseo.dis.dauvillier.so_manager.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.Serializable;
import java.util.List;

import static java.util.Arrays.asList;

@Entity(tableName = "projets",
        foreignKeys = {
                @ForeignKey(
                        entity = Utilisateur.class,
                        parentColumns = "id_user",
                        childColumns = "supervisor"
                ),
                @ForeignKey(
                        entity = Jury.class,
                        parentColumns = "id_jury",
                        childColumns = "jury"
                )
        })
public class Projets implements Serializable {

    public static final List<String> projets = asList("projectId", "title", "descrip","poster", "supervisor", "confid", "students");

    @PrimaryKey
    @ColumnInfo(name = "id_project")
    private int idProject;

    @NonNull
    private String title;

    @Nullable
    private String descrip;

    @Nullable
    private String confid;

    @Nullable
    private boolean poster;

    @Nullable
    @ColumnInfo(name = "supervisor")
    private Integer supervisor;

    @Nullable
    @ColumnInfo(name = "jury")
    private Integer jury;

    public Projets(@NonNull int idProject, @NonNull String title, @Nullable String descrip,
                   @Nullable String confid, @Nullable boolean poster, @Nullable Integer supervisor,
                   @Nullable Integer jury) {
        this.idProject = idProject;
        this.title = title;
        this.descrip = descrip;
        this.confid = confid;
        this.poster = poster;
        this.supervisor = supervisor;
        this.jury = jury;
    }

    @Ignore
    public Projets(Parcel in){
        this.idProject = in.readInt();
        this.title = in.readString();
        this.descrip = in.readString();
        this.confid = in.readString();
        this.supervisor = in.readInt();
        this.jury = in.readInt();
    }

    public static List<String> getProjets() {
        return projets;
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

    @Nullable
    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(@Nullable String descrip) {
        this.descrip = descrip;
    }

    @Nullable
    public String getConfid() {
        return confid;
    }

    public void setConfid(@Nullable String confid) {
        this.confid = confid;
    }

    @Nullable
    public boolean isPoster() {
        return poster;
    }

    public void setPoster(@Nullable boolean poster) {
        this.poster = poster;
    }

    @Nullable
    public Integer getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(@Nullable Integer supervisor) {
        this.supervisor = supervisor;
    }

    @Nullable
    public Integer getJury() {
        return jury;
    }

    public void setJury(@Nullable Integer jury) {
        this.jury = jury;
    }

    @Ignore
    public int describeContents(){
        return 0;
    }

    @Ignore
    public void writeToParcel(Parcel dest, int flags){
        dest.writeInt(this.idProject);
        dest.writeString(this.title);
        dest.writeString(this.descrip);
        dest.writeString(this.confid);
        /*dest.writeInt(this.supervisor);
        dest.writeInt(this.jury);*/
    }
}
