package com.example.sonodaumi.sharedpreferencestest;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences("PhoneNumberBook", MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();

        Start(sharedPreferences,editor);
    }

    private void Start(final SharedPreferences sharedPreferences, final SharedPreferences.Editor editor){
        Button btnClear = (Button)findViewById(R.id.clear);
        final Button btnAdd = (Button)findViewById(R.id.add);
        final Button btnDisplay = (Button)findViewById(R.id.display);
        Button btnDelete = (Button)findViewById(R.id.delete);
        final EditText etName = (EditText)findViewById(R.id.name);
        final EditText etPhoneNumber = (EditText)findViewById(R.id.phoneNumber);
        final TextView tvShow = (TextView)findViewById(R.id.showText);

        btnClear.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                etName.setText("");
                etPhoneNumber.setText("");
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(!etName.getText().toString().isEmpty() && !etPhoneNumber.getText().toString().isEmpty()){

                    editor.putString(etName.getText().toString(), etPhoneNumber.getText().toString());

                    editor.apply();

                    etName.setText("");
                    etPhoneNumber.setText("");
                    Toast.makeText(MainActivity.this, "添加成功！", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "姓名或电话号码不能为空！", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDisplay.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                Map<String,String> map = (Map<String, String>)sharedPreferences.getAll();
                if(map.isEmpty()){
                    return;
                }
                tvShow.setText("");
                for (Map.Entry<String,String> entry : map.entrySet()) {
                    tvShow.setText(tvShow.getText() + entry.getKey() + "  " + entry.getValue() + "\n");
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                editor.clear();
                editor.apply();
                tvShow.setText("");
            }
        });
    }
}


