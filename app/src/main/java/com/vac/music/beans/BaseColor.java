package com.vac.music.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by vac on 16/8/8.
 * 颜色模型类
 */
public class BaseColor implements Parcelable{
    private int alpha;
    private int red;
    private int green;
    private int blue;
    public BaseColor(int alpha_,int red_,int green_,int blue_){
        this.alpha = alpha_;
        this.red = red_;
        this.green = green_;
        this.blue = blue_;
    }

    public int getAlpha() {
        return alpha;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(alpha);
        parcel.writeInt(red);
        parcel.writeInt(green);
        parcel.writeInt(blue);
    }
    public static final Parcelable.Creator<BaseColor> CREATOR = new Parcelable.Creator<BaseColor>(){
        @Override
        public BaseColor createFromParcel(Parcel parcel) {
            return new BaseColor(parcel);
        }

        @Override
        public BaseColor[] newArray(int i) {
            return new BaseColor[i];
        }
    };

    public BaseColor(Parcel parcel){
        alpha = parcel.readInt();
        red = parcel.readInt();
        green = parcel.readInt();
        blue = parcel.readInt();
    }
}
