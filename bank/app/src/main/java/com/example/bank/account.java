package com.example.bank;




import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bank.Helper.DBHelper;

import net.sqlcipher.database.SQLiteDatabase;

import java.util.stream.LongStream;


public class account extends AppCompatActivity {

    Button btnAdd,btnUpdate,btnDelete;
    EditText edtEmail;
    ListView lstEmail;
    String saveEmail="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        SQLiteDatabase.loadLibs(this);

        lstEmail=(ListView)findViewById(R.id.lstEmail);
        lstEmail.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item=(String)lstEmail.getItemAtPosition(position);
                saveEmail=item;
            }

        });
        edtEmail=(EditText)findViewById(R.id.edtEmail);
        btnAdd=(Button)findViewById(R.id.btnAdd);
        btnUpdate=(Button)findViewById(R.id.btnUpdate);
        btnDelete=(Button)findViewById(R.id.btnDelete);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                DBHelper.getInstance(account.this).insertNewEmail(edtEmail.getText().toString());
                reloadEmails();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                DBHelper.getInstance(account.this).updateNewEmail(saveEmail,edtEmail.getText().toString());
                reloadEmails();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                DBHelper.getInstance(account.this).deleteNewEmail(edtEmail.getText().toString());
                reloadEmails();
            }
        });
        reloadEmails();
    }
    private void reloadEmails(){
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,android.R.id.text1,DBHelper.getInstance(this).getAllEmail());
        lstEmail.setAdapter(adapter);
    }
}