package com.mars.robotproject.util;

import java.util.ArrayList;
import java.util.List;

public class CihazKodlar {
    int sira;
    String size;
    String imei;
    String tarih;
    String saat;
    String index;
    String EnlemBoylam;
    String enlem;
    String boylam;
    String status;
    String hiz;
    String mesafe;
    String yon;
    String alarm;
    String uyduSayisi;
    String pilYuzdesi;
    String address;
    String LMessage;


    public int getSira() {
        return sira;
    }

    public void setSira(int sira) {
        this.sira = sira;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public String getSaat() {
        return saat;
    }

    public void setSaat(String saat) {
        this.saat = saat;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getEnlemBoylam() {
        return EnlemBoylam;
    }

    public void setEnlemBoylam(String enlemBoylam) {
        EnlemBoylam = enlemBoylam;
    }

    public String getEnlem() {
        return enlem;
    }

    public void setEnlem(String enlem) {
        this.enlem = enlem;
    }

    public String getBoylam() {
        return boylam;
    }

    public void setBoylam(String boylam) {
        this.boylam = boylam;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHiz() {
        return hiz;
    }

    public void setHiz(String hiz) {
        this.hiz = hiz;
    }

    public String getMesafe() {
        return mesafe;
    }

    public void setMesafe(String mesafe) {
        this.mesafe = mesafe;
    }

    public String getYon() {
        return yon;
    }

    public void setYon(String yon) {
        this.yon = yon;
    }

    public String getAlarm() {
        return alarm;
    }

    public void setAlarm(String alarm) {
        this.alarm = alarm;
    }

    public String getUyduSayisi() {
        return uyduSayisi;
    }

    public void setUyduSayisi(String uyduSayisi) {
        this.uyduSayisi = uyduSayisi;
    }

    public String getPilYuzdesi() {
        return pilYuzdesi;
    }

    public void setPilYuzdesi(String pilYuzdesi) {
        this.pilYuzdesi = pilYuzdesi;
    }

    public String getLMessage() {
        return LMessage;
    }

    public void setLMessage(String LMessage) {
        this.LMessage = LMessage;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return
                "" + sira +
                ";" + size  +
                ";" + imei +
                ";" + tarih +
                ";" + saat +
                ";" + index +
                ";[" + EnlemBoylam +"]"+
                ";" + enlem +
                ";" + boylam +
                ";" + status +
                ";" + hiz +
                ";" + mesafe +
                ";" + yon +
                ";" + alarm +
                ";" + uyduSayisi +
                ";" + pilYuzdesi +
                ";[" + address +"]"+
                ";" + LMessage ;
    }
}
/*
*
CREATE TABLE TESTTABLE1
(
V_SIRA NUMBER(2),
V_SIZE NUMBER(2),
V_IMEI VARCHAR2(255),
V_TARIH VARCHAR2(255),
V_SAAT VARCHAR2(255),
V_INDEX NUMBER(2),
V_ENLEMBOYLAM VARCHAR2(255),
V_ENLEM  VARCHAR2(255),
V_BOYLAM VARCHAR2(255),

V_STATUS VARCHAR2(255),
V_HIZ VARCHAR2(255),
V_MESAFE VARCHAR2(255),
V_YON VARCHAR2(255),
V_ALARM VARCHAR2(255),
V_PIL VARCHAR2(255),
V_UYDU VARCHAR2(255),
V_ADDRESS VARCHAR2(255),
V_MESSAGE VARCHAR2(255)
 );
*/
