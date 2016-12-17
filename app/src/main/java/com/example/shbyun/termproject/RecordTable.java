package com.example.shbyun.termproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by shbyun on 2016-12-01.
 */

public class RecordTable extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        TextView t11 = (TextView) findViewById(R.id.a11);
        TextView t21 = (TextView) findViewById(R.id.a21);
        TextView t31 = (TextView) findViewById(R.id.a31);
        TextView t41 = (TextView) findViewById(R.id.a41);
        TextView t51 = (TextView) findViewById(R.id.a51);
        TextView t61 = (TextView) findViewById(R.id.a61);
        TextView t71 = (TextView) findViewById(R.id.a71);
        TextView t81 = (TextView) findViewById(R.id.a81);
        TextView t91 = (TextView) findViewById(R.id.a91);
        TextView t101 = (TextView) findViewById(R.id.a101);
        TextView t111 = (TextView) findViewById(R.id.a111);

        TextView t12 = (TextView) findViewById(R.id.a12);
        TextView t22 = (TextView) findViewById(R.id.a22);
        TextView t32 = (TextView) findViewById(R.id.a32);
        TextView t42 = (TextView) findViewById(R.id.a42);
        TextView t52 = (TextView) findViewById(R.id.a52);
        TextView t62 = (TextView) findViewById(R.id.a62);
        TextView t72 = (TextView) findViewById(R.id.a72);
        TextView t82 = (TextView) findViewById(R.id.a82);
        TextView t92 = (TextView) findViewById(R.id.a92);
        TextView t102 = (TextView) findViewById(R.id.a102);
        TextView t112 = (TextView) findViewById(R.id.a112);


        Intent intent2 = getIntent();
        String name[] = intent2.getStringArrayExtra("steps");




        int arr1[] = MainActivity.getArr_steps();
        double arr2[] = MainActivity.Arr_calorie;


        t11.setText(""+arr1[0]);
        t21.setText(""+arr1[1]);
        t31.setText(""+arr1[2]);
        t41.setText(""+arr1[3]);
        t51.setText(""+arr1[4]);
        t61.setText(""+arr1[5]);
        t71.setText(""+arr1[6]);
        t81.setText(""+arr1[7]);
        t91.setText(""+arr1[8]);
        t101.setText(""+arr1[9]);
        t111.setText(""+MainActivity.getTotal_steps());


        t12.setText(""+arr2[0]);
        t22.setText(""+arr2[1]);
        t32.setText(""+arr2[2]);
        t42.setText(""+arr2[3]);
        t52.setText(""+arr2[4]);
        t62.setText(""+arr2[5]);
        t72.setText(""+arr2[6]);
        t82.setText(""+arr2[7]);
        t92.setText(""+arr2[8]);
        t102.setText(""+arr2[9]);
        t112.setText(""+MainActivity.getTotal_calorie());




    }



}
