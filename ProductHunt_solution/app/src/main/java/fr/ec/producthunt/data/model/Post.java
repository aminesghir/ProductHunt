package fr.ec.producthunt.data.model;

import android.content.ContentValues;
import android.graphics.Bitmap;

import java.util.Date;

import fr.ec.producthunt.data.database.DataBaseContract;

/**
 * @author Mohammed Boukadir  @:mohammed.boukadir@gmail.com
 */
public class Post {

    int id;
    String title;
    String subTitle;
    private String urlImage;
    private Bitmap image;
    private String postUrl;
    //--------------------------------
    private String date;
    int nbComments;
    public void setNbComments(int n){ nbComments = n; }

    public int getNbComments(){ return nbComments; }

    public String getDate(){ return this.date; }

    public void setDate(String d){ this.date = d; }
    //--------------------------------
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setPostUrl(String postUrl) {
        this.postUrl = postUrl;
    }

    public String getPostUrl() {
        return postUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ContentValues toContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseContract.PostTable.ID_COLUMN,id);
        contentValues.put(DataBaseContract.PostTable.TITLE_COLUMN,title);
        contentValues.put(DataBaseContract.PostTable.SUBTITLE_COLUMN,subTitle);
        contentValues.put(DataBaseContract.PostTable.IMAGE_URL_COLUMN,urlImage);
        contentValues.put(DataBaseContract.PostTable.POST_URL_COLUMN,postUrl);

        //-----------------------------------------------


        contentValues.put(DataBaseContract.PostTable.CREATION_DATE,date);
        contentValues.put(DataBaseContract.PostTable.NB_COMMENTS,nbComments);


        //---------------------------------------------------
        return contentValues;
    }

}
