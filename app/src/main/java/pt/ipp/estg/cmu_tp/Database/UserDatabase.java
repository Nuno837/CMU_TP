package pt.ipp.estg.cmu_tp.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import pt.ipp.estg.cmu_tp.Models.User;


@Database(entities = {User.class}, version = 1, exportSchema = false)

public abstract class UserDatabase extends RoomDatabase {

    public abstract UserDao getUserDao();

}
