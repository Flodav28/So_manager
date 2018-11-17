package fr.eseo.dis.dauvillier.so_manager.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.Date;

@Entity(tableName = "jury")
@TypeConverters({Converters.class})
public class Jury implements Parcelable {

    public static final Parcelable.Creator<Jury> CREATOR = new Parcelable.Creator<Jury>(){
        public Jury createFromParcel(Parcel source){
            return new Jury(source);
        }

        public Jury[] newArray(int size){
            return new Jury[size];
        }
    };

    @PrimaryKey
    @ColumnInfo(name = "id_jury")
    private int idJury;

    @NonNull
    private Date date;

    public Jury(int idJury, @NonNull Date date) {
        this.idJury = idJury;
        this.date = date;
    }
    @Ignore
    public Jury(Parcel in){
        this.idJury = in.readInt();
    }

    public int getIdJury() {
        return idJury;
    }

    public void setIdJury(int idJury) {
        this.idJury = idJury;
    }

    @NonNull
    public Date getDate() {
        return date;
    }

    public void setDate(@NonNull Date date) {
        this.date = date;
    }

    @Ignore
    public int describeContents(){
        return 0;
    }

    @Ignore
    public void writeToParcel(Parcel dest, int flags){
        dest.writeInt(this.idJury);
    }
}
