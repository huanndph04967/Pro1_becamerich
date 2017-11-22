package com.example.dinhhuan.becamerich.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Dinh_Huan on 11/19/2017.
 */

public class Contact {
    private String title;
    private String note;
    private Date date;
    int money;
    private Date hour;


    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getnote() {
        return note;
    }
    public void setnote(String note) {
        this.note = note;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public Date getHour() {
        return hour;
    }
    public void setHour(Date hour) {
        this.hour = hour;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Contact() {
        super();
    }

    public Contact(String title, String note, Date date, int money, Date hour) {
        this.title = title;
        this.note = note;
        this.date = date;
        this.money = money;
        this.hour = hour;
    }

    /**
     * lấy định dạng ngày
     * @param d
     * @return
     */
    public String getDateFormat(Date d)
    {
        SimpleDateFormat dft=new
                SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return dft.format(d);
    }
    /**
     * lấy định dạng giờ phút
     * @param d
     * @return
     */
    public String getHourFormat(Date d)
    {
        SimpleDateFormat dft=new
                SimpleDateFormat("hh:mm a", Locale.getDefault());
        return dft.format(d);
    }
    @Override
    public String toString() {
        return this.title+"-"+
                getDateFormat(this.date)+"-"+
                getHourFormat(this.hour);
    }


}
