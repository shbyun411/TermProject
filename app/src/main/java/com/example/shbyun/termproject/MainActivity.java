package com.example.shbyun.termproject;

import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;


import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;


import org.w3c.dom.Text;




public class MainActivity extends AppCompatActivity implements SensorEventListener,OnClickListener{

    private SensorManager mSensorManager;
    private Sensor sensor_Gravity;
    private Sensor sensor_accelerometer;
    private Sensor sensor_linear_acceleration;
    private Sensor sensor_Gyroscope;
    double acceleration;
    int steps;
    int dir_UP;
    int dir_DOWN;
    double gravity;
    int state = 0;
    Document doc = null;
    TextView textview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor_Gravity = mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        sensor_accelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensor_linear_acceleration = mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        sensor_Gyroscope = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        Button start_button = (Button) findViewById(R.id.btn_start);
        Button stop_button = (Button) findViewById(R.id.btn_stop);
        Button record_button = (Button) findViewById(R.id.btn_record);
        Button weather_button = (Button) findViewById(R.id.btn_weather);
        start_button.setOnClickListener(this);
        stop_button.setOnClickListener(this);
        record_button.setOnClickListener(this);
        weather_button.setOnClickListener(this);

        textview = (TextView)findViewById(R.id.tv_weather);



    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_start:
                state = 1;
                break;
            case R.id.btn_stop:
                state = 0;
                break;
            case R.id.btn_record:
                Intent intent1 = new Intent(MainActivity.this, RecordTable.class);
                startActivity(intent1);
                break;
            case  R.id.btn_weather:
                GetXMLTask task = new GetXMLTask();
                task.execute("http://www.kma.go.kr/wid/queryDFS.jsp?gridx=61&gridy=125");
                break;


        }
    }

    protected void onResume(){
        super.onResume();

        mSensorManager.registerListener(this, sensor_Gravity, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, sensor_accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, sensor_linear_acceleration, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, sensor_Gyroscope, SensorManager.SENSOR_DELAY_NORMAL);
    }


    protected void onPause(){
        super.onPause();

        mSensorManager.unregisterListener(this);
    }

    public final void onAccuracyChanged(Sensor sensor, int accuracy){

    }
    public final void onSensorChanged(SensorEvent event){


        TextView stepsview;

        stepsview = (TextView)findViewById(R.id.tv_record);

        if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            acceleration = Math.sqrt(x*x + y*y + z*z);


        }
        if(event.sensor.getType()==Sensor.TYPE_GRAVITY){
            float gx = event.values[0];
            float gy = event.values[1];
            float gz = event.values[2];
            gravity = Math.sqrt(gx*gx+gy*gy+gz*gz);

        }
        if(state ==1) {
            if (acceleration - gravity > 5) {
                dir_UP = 1;
            }
            if (gravity - acceleration > 5 && dir_UP == 1) {
                dir_DOWN = 1;
            }

            if (dir_DOWN == 1) {
                steps++;
                stepsview.setText(steps + " 걸음" + "\n" + String.format("%.1f", steps * 0.076) + " 칼로리소모");
                dir_DOWN = 0;
                dir_UP = 0;

            }
        }
    }



    private class GetXMLTask extends AsyncTask<String, Void, Document>{

        @Override
        protected Document doInBackground(String... urls) {
            URL url;
            try {
                url = new URL(urls[0]);
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder(); //XML문서 빌더 객체를 생성
                doc = db.parse(new InputSource(url.openStream())); //XML문서를 파싱한다.
                doc.getDocumentElement().normalize();

            } catch (Exception e) {
                Toast.makeText(getBaseContext(), "Parsing Error", Toast.LENGTH_SHORT).show();
            }
            return doc;
        }

        @Override
        protected void onPostExecute(Document doc) {
            String weatherString = "";
            String temperature;


            NodeList weatherNodeList = doc.getElementsByTagName("data");
            temperature = weatherNodeList.item(0).getChildNodes().item(3).getTextContent() + "˚";

            Node weatherNode = weatherNodeList.item(0);
            Element weatherElement = (Element) weatherNode;

            NodeList websiteList = weatherElement.getElementsByTagName("wfKor");

            weatherString += websiteList.item(0).getChildNodes().item(0).getNodeValue() + ", " + temperature;
            //weatherString += websiteList.item(0).getChildNodes().item(3).getTextContent() + "˚";

            textview.setText(weatherString);
            System.out.println(weatherString + "날씨정보입니다");
            System.out.println(temperature+"현재온도");
            super.onPostExecute(doc);


        }

        }


    }//end inner class - GetXMLTask



//메인클래스종료






