package com.joyce.library;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    Toolbar toolbar1;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        toolbar1 = (Toolbar) findViewById(R.id.toolbar1);
        textView=(TextView) findViewById(R.id.textView);
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            toolbar1.setTitle(bundle.getString("LibName"));
            textView.setText("詳細資訊:\n\n地址:\n"+bundle.getString("LibAdd")+"\n\n開放時間:\n"+bundle.getString("LibTime")+
                    "\n\n設立目的:\n"+bundle.getString("LibPurpose")+"\n\n服務:\n"+bundle.getString("LibSer"));
            textView.setMovementMethod(new ScrollingMovementMethod());
        }
    }
}
