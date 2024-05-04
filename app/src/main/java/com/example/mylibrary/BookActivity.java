package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.util.Util;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    public static final String BOOK_ID_KEY = "bookId";
    private TextView txtAddToBookName,txtAuthor,txtPages,txtDescription;
    private TextView txtLink;
    private Button btnAddToWantoRead,btnAddToAlreadyRead,btnAddtoCurrentlyReading,btnAddToFavourite;
    private ImageView bookImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        intiViews();

//        String LongDescription = "Camus uses the Greek legend of Sisyphus, who is condemned by the gods for eternity to repeatedly roll a boulder up a hill only to have it roll down again once he got it to the top" +
//                "\n" + "According to the Greek myth, Sisyphus is condemned to roll a rock up to the top of a mountain, only to have the rock roll back down to the bottom every time he reaches the top" +
//                "\n" + "The Myth of Sisyphus poses a dilemma that goes to the heart of what it means to be alive. While people strive to create good lives for themselves, the inevitability of death renders this effort—according to Camus—ultimately meaningless"+
//                "\n" + "What is the Sisyphus complex? In the well known myth of Greek mythology, the gods inflict a terrible punishment on Sisyphus: he has to push a heavy rock uphill, ";
//
//        //TODO: Get teh data from recycler view in here
//        Book book = new Book(1,"1Q84","haruki Murakami",1350,"https://m.media-amazon.com/images/I/91WMFe33+1L.jpg",
//                "A work of maddening brilliance",LongDescription);
        txtLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookActivity.this, WebsiteActivity.class);
                intent.putExtra("url","https:Google.com/");
                startActivity(intent);
//                txtLink.setPointerIcon("pointer");
            }
        });


        Intent intent = getIntent();
        if(null!=intent)
        {
            int bookId = intent.getIntExtra(BOOK_ID_KEY,-1);
            if(bookId != -1)
            {
                Book incomingBook = Utils.getInstance(this).getBookById(bookId);
                if(null!=incomingBook)
                {
                    setData1(incomingBook);
                    handleAlreadyRead(incomingBook);
                    handleWantToReadBooks(incomingBook);
                    handleCurrentReadingBooks(incomingBook);
                    handleFavoriteBooks(incomingBook);
                }
            }
        }

//        setData1(book);
    }
    private void handleFavoriteBooks(final Book book)
    {
        ArrayList<Book> favoriteBooks = Utils.getInstance(this).getFavoriteBooks();

        boolean existInFavoriteBooks = false;
        for(Book b: favoriteBooks){
            if(b.getId()==book.getId() )
            {
                existInFavoriteBooks = true;
            }
        }

        if(existInFavoriteBooks)
        {
            btnAddToFavourite.setEnabled(false);
        }else
        {
            btnAddToFavourite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Utils.getInstance(BookActivity.this).addToFavoriteBooks(book))
                    {
                        Toast.makeText(BookActivity.this, "Book Addes", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this,FavoriteBookActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(BookActivity.this, "Something wrong happended", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleCurrentReadingBooks(Book book)
    {
        ArrayList<Book> alreadyReadBooks = Utils.getInstance(this).getCurrrentlyReadingBook();

        boolean existInCurrentlyReadBooks = false;
        for(Book b: alreadyReadBooks){
            if(b.getId()==book.getId() )
            {
                existInCurrentlyReadBooks = true;
            }
        }

        if(existInCurrentlyReadBooks)
        {
            btnAddtoCurrentlyReading.setEnabled(false);
        }else
        {
            btnAddtoCurrentlyReading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Utils.getInstance(BookActivity.this).addToCurrentlyReadBooks(book))
                    {
                        Toast.makeText(BookActivity.this, "Book Addes", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this,CurrentReadActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(BookActivity.this, "Something wrong happended", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
    private void handleWantToReadBooks(final Book book)
    {
        ArrayList<Book> WantToReadBooks = Utils.getInstance(this).getWantToRead();

        boolean existInWantToReadBooks = false;
        for(Book b: WantToReadBooks){
            if(b.getId()==book.getId() )
            {
                existInWantToReadBooks = true;
            }
        }

        if(existInWantToReadBooks)
        {
            btnAddToWantoRead.setEnabled(false);
        }else
        {
            btnAddToWantoRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Utils.getInstance(BookActivity.this).addToWantToReadBooks(book))
                    {
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this,WantToReadActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(BookActivity.this, "Something wrong happended", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    /*
    *enable and diable button
    * add book to ALready Read Book Arraylist
    */
    private void handleAlreadyRead(Book book)
    {
        ArrayList<Book> alreadyReadBooks = Utils.getInstance(this).getAlreadyReadBook();

        boolean existAlreadyBooks = false;
        for(Book b: alreadyReadBooks){
            if(b.getId()==book.getId() )
            {
                existAlreadyBooks = true;
            }
        }

        if(existAlreadyBooks)
        {
            btnAddToAlreadyRead.setEnabled(false);
        }else
        {
            btnAddToAlreadyRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Utils.getInstance(BookActivity.this).addToAlreadyRead(book))
                    {
                        Toast.makeText(BookActivity.this, "Book Addes", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this,alreadyReadBookActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(BookActivity.this, "Something wrong happended", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    private void setData1(Book book)
    {
        txtAddToBookName.setText(book.getName());
        txtAuthor.setText(book.getAuthor());
        txtPages.setText(String.valueOf(book.getPages()));
        txtDescription.setText(book.getLongDesc());
        Glide.with(this)
                .asBitmap().load(book.getImageUrl())
                .into(bookImage);

    }

    private void intiViews()
    {
        txtAuthor = findViewById(R.id.txtAddToAuthor);
        txtAddToBookName = findViewById(R.id.txtAddToBookName);
        txtPages = findViewById(R.id.txtPages);
        txtDescription = findViewById(R.id.txtLongDesc);
        txtLink = findViewById(R.id.txtLink);

        btnAddToAlreadyRead = findViewById(R.id.btnAlreadyRead);
        btnAddtoCurrentlyReading = findViewById(R.id.btnAddToCurrentlyReading);
        btnAddToFavourite = findViewById(R.id.btnAddToFav);
        btnAddToWantoRead = findViewById(R.id.btnAddToWantToRead);

        bookImage = findViewById(R.id.bookImg);
    }

}