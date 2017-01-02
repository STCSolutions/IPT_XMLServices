/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Com.Stcs.IPPhone.Objects.Model;

import java.util.ArrayList;

/**
 *
 * @author aatawfik
 */
public class PhoneSeriesModel {
    private String SeriesName;
    private ArrayList<String> phoneModels=new ArrayList<String>();
    private String photoSize;
    private boolean isColorPhone=true;

    /**
     * @return the SeriesName
     */
    public String getSeriesName() {
        return SeriesName;
    }

    /**
     * @param SeriesName the SeriesName to set
     */
    public void setSeriesName(String SeriesName) {
        this.SeriesName = SeriesName;
    }

    /**
     * @return the phoneModels
     */
    public ArrayList<String> getPhoneModels() {
        return phoneModels;
    }

    /**
     * @param phoneModels the phoneModels to set
     */
    public void setPhoneModels(ArrayList<String> phoneModels) {
        this.phoneModels = phoneModels;
    }

    /**
     * @return the photoSize
     */
    public String getPhotoSize() {
        return photoSize;
    }

    /**
     * @param photoSize the photoSize to set
     */
    public void setPhotoSize(String photoSize) {
        this.photoSize = photoSize;
    }

    /**
     * @return the isColorPhone
     */
    public boolean isIsColorPhone() {
        return isColorPhone;
    }

    /**
     * @param isColorPhone the isColorPhone to set
     */
    public void setIsColorPhone(boolean isColorPhone) {
        this.isColorPhone = isColorPhone;
    }
    
    
 
 
}
