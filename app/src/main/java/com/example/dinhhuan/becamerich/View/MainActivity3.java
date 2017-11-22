package com.example.dinhhuan.becamerich.View;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.dinhhuan.becamerich.Controller.Contact;
import com.example.dinhhuan.becamerich.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Dinh_Huan on 11/19/2017.
 */

public class MainActivity3 extends Activity {
    TextView txtDate,txtTime;
    EditText editCv,editNd;
    Button btnDate,btnTime,btnAdd;
    //Khai báo Datasource lưu trữ danh sách công việc
    ArrayList<Contact>arrJob=new ArrayList<Contact>();
    //Khai báo ArrayAdapter cho ListView
    ArrayAdapter<Contact> adapter=null;
    ListView lvCv;
    Calendar cal;
    Date date;
    Date hour;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFormWidgets();
        getDefaultInfor();
        addEventFormWidgets();
        setContentView(R.layout.outline);
    }

    /**
     * hàm dùng để load các control theo Id
     */
    public void getFormWidgets()
    {
        txtDate=(TextView) findViewById(R.id.txtdate);
        txtTime=(TextView) findViewById(R.id.txttime);
        editCv=(EditText) findViewById(R.id.editcongviec);
        editNd=(EditText) findViewById(R.id.editnoidung);
        btnDate=(Button) findViewById(R.id.btndate);
        btnTime=(Button) findViewById(R.id.btntime);
        btnAdd=(Button) findViewById(R.id.btncongviec);
        lvCv=(ListView) findViewById(R.id.lvcongviec);
        //Gán DataSource vào ArrayAdapter
        adapter=new ArrayAdapter<Contact>
                (this,
                        android.R.layout.simple_list_item_1,
                        arrJob);
        //gán Adapter vào ListView
        lvCv.setAdapter(adapter);
    }
    /**
     * Hàm lấy các thông số mặc định khi lần đầu tiền chạy ứng dụng
     */
    public void getDefaultInfor()
    {
        //lấy ngày hiện tại của hệ thống
        cal=Calendar.getInstance();
        SimpleDateFormat dft=null;
        //Định dạng ngày / tháng /năm
        dft=new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String strDate=dft.format(cal.getTime());
        //hiển thị lên giao diện
        txtDate.setText(strDate);
        //Định dạng giờ phút am/pm
        dft=new SimpleDateFormat("hh:mm a",Locale.getDefault());
        String strTime=dft.format(cal.getTime());
        //đưa lên giao diện
        txtTime.setText(strTime);
        //lấy giờ theo 24 để lập trình theo Tag
        dft=new SimpleDateFormat("HH:mm",Locale.getDefault());
        txtTime.setTag(dft.format(cal.getTime()));

        editCv.requestFocus();
        //gán cal.getTime() cho ngày hoàn thành và giờ hoàn thành
        date=cal.getTime();
        hour=cal.getTime();
    }
    /**
     * Hàm gán các sự kiện cho các control
     */
    public void addEventFormWidgets()
    {
        btnDate.setOnClickListener(new MyButtonEvent());
        btnTime.setOnClickListener(new MyButtonEvent());
        btnAdd.setOnClickListener(new MyButtonEvent());
        lvCv.setOnItemClickListener(new MyListViewEvent());
        lvCv.setOnItemLongClickListener(new MyListViewEvent());
    }
    /**
     * Class sự kiện của các Button
     * @author drthanh
     *
     */
    private class MyButtonEvent implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
            switch(v.getId())
            {
                case R.id.btndate:
                    showDatePickerDialog();
                    break;
                case R.id.btntime:
                    showTimePickerDialog();
                    break;
                case R.id.btncongviec:
                    processAddJob();
                    break;
            }
        }

    }
    /**
     * Class sự kiện của ListView
     * @author drthanh
     *
     */
    private class MyListViewEvent implements
            AdapterView.OnItemClickListener,
            AdapterView.OnItemLongClickListener
    {

        @Override
        public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
            //Xóa vị trí thứ arg2
            arrJob.remove(arg2);
            adapter.notifyDataSetChanged();
            return false;
        }

        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                long arg3) {
            //Hiển thị nội dung công việc tại vị trí thứ arg2
            Toast.makeText(MainActivity3.this,
                    arrJob.get(arg2).getnote(),
                    Toast.LENGTH_LONG).show();
        }

    }
    /**
     * Hàm hiển thị DatePicker dialog
     */
    public void showDatePickerDialog()
    {
        DatePickerDialog.OnDateSetListener callback=new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear,
                                  int dayOfMonth) {
                //Mỗi lần thay đổi ngày tháng năm thì cập nhật lại TextView Date
                txtDate.setText(
                        (dayOfMonth) +"/"+(monthOfYear+1)+"/"+year);
                //Lưu vết lại biến ngày hoàn thành
                cal.set(year, monthOfYear, dayOfMonth);
                date=cal.getTime();
            }
        };
        //các lệnh dưới này xử lý ngày giờ trong DatePickerDialog
        //sẽ giống với trên TextView khi mở nó lên
        String s=txtDate.getText()+"";
        String strArrtmp[]=s.split("/");
        int ngay=Integer.parseInt(strArrtmp[0]);
        int thang=Integer.parseInt(strArrtmp[1])-1;
        int nam=Integer.parseInt(strArrtmp[2]);
        DatePickerDialog pic=new DatePickerDialog(
                MainActivity3.this,
                callback, nam, thang, ngay);
        pic.setTitle("Chọn ngày");
        pic.show();
    }
    /**
     * Hàm hiển thị TimePickerDialog
     */
    public void showTimePickerDialog()
    {
        TimePickerDialog.OnTimeSetListener callback=new TimePickerDialog.OnTimeSetListener() {
            public void onTimeSet(TimePicker view,
                                  int hourOfDay, int minute) {
                //Xử lý lưu giờ và AM,PM
                String s=hourOfDay +":"+minute;
                int hourTam=hourOfDay;
                if(hourTam>12)
                    hourTam=hourTam-12;
                txtTime.setText
                        (hourTam +":"+minute +(hourOfDay>12?" PM":" AM"));
                //lưu giờ thực vào tag
                txtTime.setTag(s);
                //lưu vết lại giờ vào hourFinish
                cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
                cal.set(Calendar.MINUTE, minute);
                hour=cal.getTime();
            }
        };
        //các lệnh dưới này xử lý ngày giờ trong TimePickerDialog
        //sẽ giống với trên TextView khi mở nó lên
        String s=txtTime.getTag()+"";
        String strArr[]=s.split(":");
        int gio=Integer.parseInt(strArr[0]);
        int phut=Integer.parseInt(strArr[1]);
        TimePickerDialog time=new TimePickerDialog(
                MainActivity3.this,
                callback, gio, phut, true);
        time.setTitle("Chọn giờ ");
        time.show();
    }
    /**
     * Hàm xử lý đưa công việc vào ListView khi nhấn nút Thêm Công việc
     */
    public void processAddJob()
    {
        String title=editCv.getText()+"";
        String note=editNd.getText()+"";
        Contact ct=new Contact(title, note, date, hour);
        arrJob.add(ct);
        adapter.notifyDataSetChanged();
        //sau khi cập nhật thì reset dữ liệu và cho focus tới editCV
        editCv.setText("");
        editNd.setText("");
        editCv.requestFocus();
    }

}

