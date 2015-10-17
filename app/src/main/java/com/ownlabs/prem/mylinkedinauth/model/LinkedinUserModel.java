package com.ownlabs.prem.mylinkedinauth.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ShekharKG on 12/10/15.
 */
public class LinkedinUserModel {

    String emailAddress;
    String firstName;
    String lastName;
    String formattedName;
    String id;
    String industry;
    Location location;
    String pictureUrl;
    @SerializedName("pictureUrls")
    PictureUrls pictureUrls;
    @SerializedName("positions")
    Positions positions;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFormattedName() {
        return formattedName;
    }

    public void setFormattedName(String formattedName) {
        this.formattedName = formattedName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public PictureUrls getPictureUrls() {
        return pictureUrls;
    }

    public void setPictureUrls(PictureUrls pictureUrls) {
        this.pictureUrls = pictureUrls;
    }

    public Positions getPositions() {
        return positions;
    }

    public void setPositions(Positions positions) {
        this.positions = positions;
    }

    public class Location{

        String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public class PictureUrls {
        int _total;
        String[] values;

        public int get_total() {
            return _total;
        }

        public void set_total(int _total) {
            this._total = _total;
        }

        public String[] getValues() {
            return values;
        }

        public void setValues(String[] values) {
            this.values = values;
        }
    }


    public class Positions {
        int _total;
        List<Title> values;

        public class Title{
            String title;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public int get_total() {
            return _total;
        }

        public void set_total(int _total) {
            this._total = _total;
        }

        public List<Title> getValues() {
            return values;
        }

        public void setValues(List<Title> values) {
            this.values = values;
        }
    }

}
