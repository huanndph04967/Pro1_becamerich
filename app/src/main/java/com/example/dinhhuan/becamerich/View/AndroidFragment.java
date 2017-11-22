package com.example.dinhhuan.becamerich.View;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dinhhuan.becamerich.R;

public class AndroidFragment  extends Fragment  {


    public AndroidFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_android, container, false);
    }


}
