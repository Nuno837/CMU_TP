package pt.ipp.estg.cmu_tp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class POI {
    @SerializedName("ID")
    @Expose
    public int ID;

    @SerializedName("AddressInfo")
    @Expose
    public AdressInfo adressInfo;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public AdressInfo getAdressInfo() {
        return adressInfo;
    }

    public void setAdressInfo(AdressInfo adressInfo) {
        this.adressInfo = adressInfo;
    }
}