package com.example.bisho.mktba;

import android.content.res.AssetManager;
import android.util.Log;

import java.io.InputStream;
import java.util.ArrayList;

import jxl.Sheet;
import jxl.Workbook;

/**
 * Created by bisho on 29-Sep-16.
 */
public class ReadExcelFile {

    private ArrayList<Book> myBooks ;
    private ArrayList<Book> sheetBooks ;
    private ArrayList<Book> searchResult;
    private ArrayList<String> searchSubjects;
    private ArrayList<String> mySubjects;
    private AssetManager assetManager;

    public void setAssetManager(AssetManager assetManager) {
        this.assetManager = assetManager;
        myBooks = new ArrayList<>();
        sheetBooks = new ArrayList<>();
        searchResult = new ArrayList<>();
        searchSubjects = new ArrayList<>();
        mySubjects = new ArrayList<>();
    }

    public ArrayList<String> getSubjects (int sheetNo){
        mySubjects.clear();
        String subject="";

        try {
            InputStream inputStream = assetManager.open("mktba.xls");
            Workbook workbook = Workbook.getWorkbook(inputStream);
            Sheet excelSheet = workbook.getSheet(sheetNo);
            int rows = excelSheet.getRows();
            Log.d("rows",""+rows);
            for (int r = 3; r < rows; r++) {
                if (excelSheet.getCell(1, r).getContents().isEmpty()
                        && excelSheet.getCell(2, r).getContents().isEmpty()
                        && excelSheet.getCell(3, r).getContents().isEmpty() && excelSheet.getCell(4, r).getContents().isEmpty()
                        && excelSheet.getCell(5, r).getContents().isEmpty()
                        && excelSheet.getCell(4, r).getContents().isEmpty())break;

                if(!excelSheet.getCell(0, r).getContents().isEmpty() && !excelSheet.getCell(0,r).getContents().equals("اسم الموضوع"))
                {
                    subject = excelSheet.getCell(0, r).getContents();
                    mySubjects.add(subject);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mySubjects;
    }

    public ArrayList<Book> getBooks (int sheetNo , String subjectName) {
        myBooks.clear();
        String name;
        String bookSubject="";
        String copiesNumber;
        String booksCode;
        String autherName;
        String enteringCode;

        try {
            InputStream inputStream = assetManager.open("mktba.xls");
            Workbook workbook = Workbook.getWorkbook(inputStream);
            Sheet excelSheet = workbook.getSheet(sheetNo);
            int rows = excelSheet.getRows();
                for (int r = 3; r < rows; r++) {

                    if (excelSheet.getCell(1, r).getContents().isEmpty()
                            && excelSheet.getCell(2, r).getContents().isEmpty()
                            && excelSheet.getCell(3, r).getContents().isEmpty() && excelSheet.getCell(4, r).getContents().isEmpty()
                            && excelSheet.getCell(5, r).getContents().isEmpty()
                            && excelSheet.getCell(4, r).getContents().isEmpty())break;

                    if (excelSheet.getCell(0, r).getContents().equals(subjectName))
                    {
                        bookSubject = excelSheet.getCell(0, r).getContents();
                        name = excelSheet.getCell(1, r).getContents();
                        copiesNumber = excelSheet.getCell(2, r).getContents();
                        booksCode = excelSheet.getCell(3, r).getContents();
                        autherName = excelSheet.getCell(4, r).getContents();
                        enteringCode = excelSheet.getCell(5, r).getContents();
                        Book book = new Book(name, bookSubject, copiesNumber, booksCode, autherName, enteringCode);
                        myBooks.add(book);
                        for (int i=r+1; i<rows ; i++){
                            if (!excelSheet.getCell(0, i).getContents().isEmpty())break;
                            name = excelSheet.getCell(1, i).getContents();
                            copiesNumber = excelSheet.getCell(2, i).getContents();
                            booksCode = excelSheet.getCell(3, i).getContents();
                            autherName = excelSheet.getCell(4, i).getContents();
                            enteringCode = excelSheet.getCell(5, i).getContents();
                            Book abook = new Book(name, bookSubject, copiesNumber, booksCode, autherName, enteringCode);
                            myBooks.add(abook);
                        }
                    }



                }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return myBooks;
    }
    
    public ArrayList<Book> getAllBooks(int sheetNo)
    {
        sheetBooks.clear();

        String name;
        String bookSubject="";
        String copiesNumber;
        String booksCode;
        String autherName;
        String enteringCode;

        try {
            InputStream inputStream = assetManager.open("mktba.xls");
            Workbook workbook = Workbook.getWorkbook(inputStream);
            Sheet excelSheet = workbook.getSheet(sheetNo);
            int rows = excelSheet.getRows();
            for (int r = 3; r < rows; r++) {

                if (excelSheet.getCell(1, r).getContents().isEmpty()
                        && excelSheet.getCell(2, r).getContents().isEmpty()
                        && excelSheet.getCell(3, r).getContents().isEmpty() && excelSheet.getCell(4, r).getContents().isEmpty()
                        && excelSheet.getCell(5, r).getContents().isEmpty()
                        && excelSheet.getCell(4, r).getContents().isEmpty())break;

                if (!excelSheet.getCell(0, r).getContents().isEmpty())bookSubject = excelSheet.getCell(0, r).getContents();
                    name = excelSheet.getCell(1, r).getContents();
                    copiesNumber = excelSheet.getCell(2, r).getContents();
                    booksCode = excelSheet.getCell(3, r).getContents();
                    autherName = excelSheet.getCell(4, r).getContents();
                    enteringCode = excelSheet.getCell(5, r).getContents();
                    Book book = new Book(name, bookSubject, copiesNumber, booksCode, autherName, enteringCode);
                    sheetBooks.add(book);
                    
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return sheetBooks;
    }

    public ArrayList<Book> getABook (String bookName , ArrayList<Book> booksToSearch){
        searchResult.clear();

        for (Book book : booksToSearch){

            if (book.getName().contains(bookName))searchResult.add(book);

        }

        return searchResult;
    }
    
    public ArrayList<Book> searchAllBooks (String bookName,int sheetNo){
        
        return getABook(bookName,getAllBooks(sheetNo));
    }
    


}
