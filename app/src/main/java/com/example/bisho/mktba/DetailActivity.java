package com.example.bisho.mktba;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {

    TextView name_text;
    TextView auther_text;
    TextView subject_text;
    TextView code_text;
    TextView entering_text;
    TextView copies_text;

    String name = "";
    String auther = "المؤلف : ";
    String subject = "الموضوع : " ;
    String code   = "كود الكتاب : ";
    String entering = "رقم الإيداع : ";
    String copies = "عدد النسخ : ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        name   = intent.getStringExtra("name");
        auther   += intent.getStringExtra("auther");
        subject   += intent.getStringExtra("subject");
        code   += intent.getStringExtra("code");
        entering   += intent.getStringExtra("entering");
        copies   += intent.getStringExtra("copies");

        name_text = (TextView) findViewById(R.id.book_name);
        name_text.setText(name);
        auther_text = (TextView) findViewById(R.id.auther_name);
        auther_text.setText(auther);
        subject_text = (TextView) findViewById(R.id.subject);
        subject_text.setText(subject);
        code_text = (TextView) findViewById(R.id.code);
        code_text.setText(code);
        entering_text = (TextView) findViewById(R.id.entering_code);
        entering_text.setText(entering);
        copies_text = (TextView) findViewById(R.id.copies);
        copies_text.setText(copies);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail,menu);
        MenuItem add = menu.findItem(R.id.action_add);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_add)
        {
        Intent intent = new Intent(this, Request_Activity.class);
        intent.putExtra("name",name);
        intent.putExtra("auther",auther);
        intent.putExtra("subject",subject);
        intent.putExtra("code",code);
        intent.putExtra("entering",entering);
        intent.putExtra("copies",copies);
        Toast.makeText(this,name,Toast.LENGTH_SHORT).show();
        startActivity(intent);
        }
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return true;
    }
}
