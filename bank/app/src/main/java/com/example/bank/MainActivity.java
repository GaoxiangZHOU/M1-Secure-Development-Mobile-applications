package com.example.bank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bank.Helper.DBHelper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_log;
    EditText editTextName,editTextPwd;
    InputStream inputStream;
    HttpURLConnection connection;
    BufferedReader bufferedReader;
    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextName=(EditText)this.findViewById(R.id.email);
        editTextPwd=(EditText)this.findViewById(R.id.pwd);
        initUI();

    }
    private void initUI(){
        findViewById(R.id.btn_log).setOnClickListener(this);

    }
    public void onClick(View view){
        Login();
    }


    private void Login(){

        data =getDataFromServer();
        //Log.i("MainActivity",""+data);
        String name=editTextName.getText().toString();
        String pwd=editTextPwd.getText().toString();
        if (name.equals("admin") && pwd.equals("123")){

            Toast.makeText(this, "welcome "+data, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setClass(getApplicationContext(),account.class);
            this.startActivity(intent);
        }
        else{
            Toast.makeText(this, "failed to login", Toast.LENGTH_SHORT).show();
        }
    }
    private String getDataFromServer(){

        try{
            URL url = new URL("http://60102f166c21e10017050128.mockapi.io/labbbank/config/1");

            connection = (HttpURLConnection) url.openConnection();


            inputStream = connection.getInputStream();

            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder=new StringBuilder();
            String line = bufferedReader.readLine();


            return line;

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (bufferedReader!=null)bufferedReader.close();
                if (inputStream!=null)inputStream.close();
                if (connection!=null)connection.disconnect();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return "{\"id\":\"1\",\"name\":\"Georgiana\",\"lastname\":\"Maggio\"}";
    }
}