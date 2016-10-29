package com.example.bisho.mktba;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Books_Activity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ArrayList<Book> myBooks;
    ReadExcelFile readExcelFile;
    GridView gridView;
    String subject ;
    int sheetNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_);

        readExcelFile = new ReadExcelFile();
        readExcelFile.setAssetManager(getAssets());
        myBooks = new ArrayList<>();
        gridView = (GridView) findViewById(R.id.gridView2);
        gridView.setOnItemClickListener(this);
        sheetNo = getIntent().getIntExtra("sheetNo",0);
        subject = getIntent().getStringExtra("subject");
        myBooks = readExcelFile.getBooks(sheetNo,subject);
        BookAdapter bookAdapter = new BookAdapter(this);
        gridView.setAdapter(bookAdapter);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("name",myBooks.get(i).getName());
        intent.putExtra("auther",myBooks.get(i).getAutherName());
        intent.putExtra("subject",myBooks.get(i).getBookSubject());
        intent.putExtra("code",myBooks.get(i).getBooksCode());
        intent.putExtra("entering",myBooks.get(i).getEnteringCode());
        intent.putExtra("copies",myBooks.get(i).getCopiesNumber());
        Toast.makeText(this,myBooks.get(i).getName(),Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }


    class BookAdapter extends BaseAdapter
    {
        ArrayList<Book> books;
        Context context;

        BookAdapter (Context c)
        {

            this.context=c;
            this.books = myBooks;

        }

        @Override
        public int getCount() {return books.size();}

        @Override
        public Object getItem(int position) {return books.get(position);}

        @Override
        public long getItemId(int position) {return position;}

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


                Book temp = this.books.get(position);
                myHolder.myText.setText((position+1)+"."+temp.getName());

            return item;
        }
    }
}
