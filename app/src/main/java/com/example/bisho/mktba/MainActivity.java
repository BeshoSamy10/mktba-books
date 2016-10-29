package com.example.bisho.mktba;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener, View.OnClickListener {

    ReadExcelFile readExcelFile;
    AssetManager assetManager;
    ArrayList<String> mySubjects;
    ArrayList<Book> myBooks;
    Spinner spinner;
    EditText searchText;
    Button myButton;
    GridView gridView;
    boolean subjectsBool = true;
    int sheetNo;
    String[] arr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assetManager = getAssets();
        mySubjects   = new ArrayList<>();
        myBooks      = new ArrayList<>();
        searchText   = (EditText) findViewById(R.id.editText);
        myButton     = (Button) findViewById(R.id.button);
        gridView     = (GridView) findViewById(R.id.gridView);
        arr          = getResources().getStringArray(R.array.spinner_values);
        gridView.setOnItemClickListener(this);
        readExcelFile= new ReadExcelFile();
        readExcelFile.setAssetManager(assetManager);

        myButton.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_spinner,menu);
        MenuItem item = menu.findItem(R.id.spinner);

        spinner = (Spinner) MenuItemCompat.getActionView(item);
        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(this,R.array.spinner_values,R.layout.simple_spinner_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(this);

        return true;
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        TextView myText = (TextView) view;
        subjectsBool = true;
        sheetNo = i;
        mySubjects.clear();
        mySubjects = readExcelFile.getSubjects(i);
        BookAdapter subjectAdapter = new BookAdapter(this);
        gridView.setAdapter(subjectAdapter);
        searchText.setHint("بحث في قسم " + arr[i]);
        Toast.makeText(this,myText.getText(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (!subjectsBool){
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("name",myBooks.get(i).getName());
            intent.putExtra("auther",myBooks.get(i).getAutherName());
            intent.putExtra("subject",myBooks.get(i).getBookSubject());
            intent.putExtra("code",myBooks.get(i).getBooksCode());
            intent.putExtra("entering",myBooks.get(i).getEnteringCode());
            intent.putExtra("copies",myBooks.get(i).getCopiesNumber());
            Toast.makeText(this,myBooks.get(i).getName(),Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }else {
            Intent intent = new Intent(this, Books_Activity.class);
            intent.putExtra("sheetNo",sheetNo);
            intent.putExtra("subject",mySubjects.get(i));
            Toast.makeText(this,mySubjects.get(i),Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }

    }

    @Override
    public void onClick(View view) {
        String searchQuery = searchText.getText().toString();
        if (searchQuery.isEmpty()) Toast.makeText(this, "ادخل كلمة البحث", Toast.LENGTH_SHORT).show();
        else {
            subjectsBool = false;
            myBooks.clear();
            myBooks = readExcelFile.searchAllBooks(searchQuery, sheetNo);
            if(myBooks.isEmpty())Toast.makeText(this,"لا يوجد نتائج",Toast.LENGTH_SHORT).show();
            else {
                BookAdapter subjectAdapter = new BookAdapter(this);
                gridView.setAdapter(subjectAdapter);
            }
        }
    }
    class BookAdapter extends BaseAdapter
    {
        ArrayList<String> subjects;
        ArrayList<Book> books;
        Context context;

        BookAdapter (Context c)
        {

            this.context=c;
            this.subjects = mySubjects;
            this.books = myBooks;

        }

        @Override
        public int getCount() {

            if (subjectsBool) {
                 return subjects.size();
            }
            else return books.size();
        }

        @Override
        public Object getItem(int position) {
            if (subjectsBool)
            {
                 return subjects.get(position);
            }
            else return books.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        class TextViewHolder
        {
            TextView myText;
            TextViewHolder(View v)
            {
                myText = (TextView) v.findViewById(R.id.textView);
            }
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View item=convertView;
            TextViewHolder myHolder;
            if (item == null)
            {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                item = inflater.inflate(R.layout.item_layout,parent,false);
                myHolder = new TextViewHolder(item);
                item.setTag(myHolder);
            }

            else
            {
                myHolder = (TextViewHolder) item.getTag();
            }

            if (subjectsBool) {
                String temp = subjects.get(position);
                myHolder.myText.setText((position+1)+"."+temp);
            }
            else {
                Book temp = this.books.get(position);
                myHolder.myText.setText((position+1)+"."+temp.getName());
            }

            return item;
        }
    }


}

