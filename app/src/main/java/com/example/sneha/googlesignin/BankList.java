package com.example.sneha.googlesignin;

public class BankList {


    private String name;
    private int mImageResourceId = NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED = -1;


    public BankList(String List, int imageResourceId) {
        mImageResourceId = imageResourceId;
        name = List;
    }

    public String getName() {
        return name;
    }

  /*  public void setName(String name) {
        this.name = name;
    }*/

    public int getmImageResourceId() {
        return mImageResourceId;
    }

    /*public void setmImageResourceId(int mImageResourceId) {
        this.mImageResourceId = mImageResourceId;
    }
*/
    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }
}