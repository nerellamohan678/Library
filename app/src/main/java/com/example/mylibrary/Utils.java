package com.example.mylibrary;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;

public class Utils {
    private static final String ALL_BOOKS_KEY = "all_books";
    private static final String ALL_ADD_KEY = "all_add";
    private static final String ALREADY_READ_BOOKS = "already_read_books";
    private static final String WANT_TO_READ_BOOKS = "want_to_read_books";
    private static final String CURRENTLY_READING_BOOKS = "currently_reading_books";
    private static final String FAVORITE_BOOKS = "favorite_books";

    private static Utils instance;
    private SharedPreferences sharedPreferences;
    public static Utils getInstance(Context context) {//a physical manifestation of this class can be called an instance
        if(null != instance)
        {
            return instance;
        }
        else
        {
            instance = new Utils(context);
            return instance;
        }
    }

//    private static ArrayList<Book> allBooks;
//    private static ArrayList<Book> alreadyReadBook;
//    private static ArrayList<Book> wantToRead;
//    private static ArrayList<Book> CurrrentlyReadingBook;
//    private static ArrayList<Book> favoriteBooks;
    private Utils(Context context) {

        sharedPreferences = context.getSharedPreferences("alternate_db",Context.MODE_PRIVATE);
        if(null ==getAllBooks())
        {
            intitData();
        }
        /*
        *below code is to initialize all the classes(like:alreadyReadBooks,CurrentlyReadingBooks,ect) by taking string and changing into arraylist and then initializing with empty arraylist
        */
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson  =new Gson();
        if(null ==getAlreadyReadBook())
        {
            editor.putString(ALREADY_READ_BOOKS,gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if(null ==getCurrrentlyReadingBook())
        {
            editor.putString(CURRENTLY_READING_BOOKS,gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if(null ==getFavoriteBooks())
        {
            editor.putString(FAVORITE_BOOKS,gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if(null==getWantToRead())
        {
            editor.putString(WANT_TO_READ_BOOKS,gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }

    }
    private void intitData() {
        //TODO: add intial data;
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book(1,"The Last Rebellion","James Hunt",300,"https://m.media-amazon.com/images/I/618Q3PXvFhL._SL1500_.jpg",
                "Men to save his family","Charlie Owens spent his life protecting others, but after he left the service, " +
                "he focused on protecting his family. An EMP has wreaked havoc on the country, and the person responsible plans more " +
                "destruction. Charlie vowed never to be pulled back into another person’s conflict. But if " +
                "he wants to keep his family alive, Charlie will have no choice but to fight."));
        books.add(new Book(2,"God of Small Things","Roy Arundhati",356,"https://m.media-amazon.com/images/I/91saO95VziL._SL1500_.jpg",
                "life in kerala"," is a story about two children, Esthappen and Rahel. This was Arundhati Roy's debut novel, " +
                "in which she throws light on certain facets of life in Kerala, highlighting issues of caste system, " +
                "Keralite Syrian Christian lifestyle and communism. Esthappen and Rahel at a very young age come to learn about horrifying truth of life, " +
                "as they are being tortured and blamed for every misfortune. Their less than perfect life gets infected by unexpected events. " +
                "through the novel begins with Esthappen and Rahel, most of its part holds wider stories of the political events shaping the state, " +
                "their parents and relatives. " +
                "The darker undertones in the life of twins get more evident, as secrets, bitterness and lies destroy their world."));
        books.add(new Book(3,"Wings Of Fire An Autobiography","Arun Tiwari and A. P. J Abdul Kalam",180,"https://m.media-amazon.com/images/I/71KKZlVjbwL._SL1500_.jpg",
                "It is an autobiography by visionary","Every common man who by his sheer grit and hard work achieves success should " +
                "share his story with the rest for they may find inspiration and strength to go on, in his story. " +
                "The 'Wings of Fire' is one such autobiography by visionary scientist Dr. APJ Abdul Kalam, " +
                "who from very humble beginnings rose to be the President of India. The book is full of insights, personal moments and life experiences of Dr. Kalam. " +
                "It gives us an understanding on his journey of success. Dr. Kalam by narrating his life journey evokes the reader to identify with one’s inner fire and potential, " +
                "for he was of the firm belief that each one of us was born with the strength and potential to make a tangible change in the world. " +
                "How he inspired himself to achieve dreams and how he went about accomplishing so much is what the book captures nicely."));
        books.add(new Book(4,"The Story of My Experiments With Truth","Mahatma Gandhi",448,"https://m.media-amazon.com/images/I/71r55-KjjpL._SL1400_.jpg",
                "Autobiography of Mahatma Gandhi","Explore the remarkable autobiography of Mahatma Gandhi, \"The Story of My Experiments with Truth." +
                "\" Gandhi's insightful narrative takes readers on a transformative journey through his life, principles, and nonviolent philosophy, " +
                "leaving an indelible mark on the world."));
        books.add(new Book(5,"The Gita","Roopa Pai",296,"https://m.media-amazon.com/images/I/71t3+JjDtkL._SL1500_.jpg",
                "Re-telling a part of Bhagavad Gita","Roopa Pai's spirited, one-of-a-kind retelling of the epic conversation between Pandava prince Arjuna and his mentor and " +
                "friend Krishna busts these and other such myths about the Bhagavad Gita. Lucid, thought-provoking and brimming with fun trivia, " +
                "this book will stay with you long after you have turned the last page."));
        books.add(new Book(6,"Malgudi Days","R. K. Narayan",97,"https://m.media-amazon.com/images/I/61EH8ZgZe9L._SL1400_.jpg",
                "Lives of people who live in fictional town"," Malgudi Days explores the lives of those who reside in the fictional town of Malgudi. Malgudi, " +
                "a made-up small town in southern India, is populated by timeless people who may reside anywhere in the world, " +
                "just like any other small town or village."));
        books.add(new Book(7,"Discovery of India","Jawaharlal Nehru",656,"https://m.media-amazon.com/images/I/61zZzKlLleL._SL1000_.jpg",
                "The book is a broad view of Indian history, culture and philosophy","Jawaharlal Nehru wrote the book \u0091The Discovery of India\u0092, " +
                "during his imprisonment at Ahmednagar fort for participating in the Quit India Movement (1942 \u0096 1946). " +
                "The book was written during Nehru\u0092s four years of confinement to solitude in prison and is his way of paying an " +
                "homage to his beloved country and its rich culture.\n" +
                "\n" +
                "The book started from ancient history, Nehru wrote at length of Vedas, Upanishads and textbooks on ancient time and ends during the British raj. " +
                "The book is a broad view of Indian history, culture and philosophy, the same can also be seen in the television series. " +
                "The book is considered as one of the finest writing om Indian History. " +
                "The television series Bharat Ek Khoj which was released in 1988 was based on this book."));
        books.add(new Book(8,"The Complete Adventures of Feluda Vol. 1","Satyajit Ray",804,"https://m.media-amazon.com/images/I/515eufR2p6L._SL1000_.jpg",
                "Adventures by a boy and his cousin","This omnibus edition features the ever-popular adventures of Satyajit Ray's enduring creation, " +
                "the professional sleuth Pradosh C. Mitter (Feluda). In his escapades, Feluda is accompanied by his cousin Topshe and " +
                "the bumbling crime writer Lalmohan Ganguly (Jatayu). From Jaisalmer to Simla, from the Ellora Caves to Varanasi, " +
                "the trio traverse fascinating locales to unravel one devious crime after another."));
        books.add(new Book(9,"The Blue Umbrella","Ruskin Bond",90,"https://m.media-amazon.com/images/I/7148cFCuKrL._SL1500_.jpg",
                "When she bought blue umbrella","The Umbrella was like a flower, a great blue flower that had sprung up on the dry brown hillside." +
                "In exchange for her lucky leopard's claw pendant, Binya acquires a beautiful blue umbrella that makes her the envy of everyone in her village, " +
                "especially Ram Bharosa, the shop-keeper. Ruskin Bond's short and humorous novella, set in the picturesque hills of Garhwal, " +
                "perfectly captures life in a village, where both heroism and redemption can be found."));
        books.add(new Book(10,"Sea of Poppies","Amitav Ghosh",528,"https://m.media-amazon.com/images/I/910Bu7oGWCL._SL1500_.jpg",
                "Journey across the Indian ocean",
                "A motley array of sailors and stowaways, coolies and convicts is sailing down the Hooghly aboard the Ibis on its way to Mauritius. " +
                        "As they journey across the Indian Ocean old family ties are washed away and they begin to view themselves as jahaj-bhais or " +
                        "ship brothers who will build new lives for themselves in the remote islands where they are being taken. " +
                        "A stunningly vibrant and intensely human work, Sea of Poppies, the first book in the Ibis trilogy confirms " +
                        "Amitav Ghosh's reputation as a master storyteller."));
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
//        String text = gson.toJson(books);//here we have converted a array list into a gson file.
        editor.putString(ALL_BOOKS_KEY,gson.toJson(books));//to convert array list into a string using gson
        editor.commit();//commit writes its data to persistent storage immediately, whereas apply will handle it in the background
    }
    /*
    * here down int his method we are getting the text from shared preferences and changing them to Arraylist using typeToken*/
    public ArrayList<Book> getAllBooks() {
        Gson gson = new Gson();
        Type type  = new TypeToken<ArrayList<Book>>() {}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(ALL_BOOKS_KEY,null),type);
        //if we fail to retrive string from the key(ALL_BOOKS_KEY) then it will return null;
        return books;
    }

    public ArrayList<Book> getAlreadyReadBook() {
        Gson gson = new Gson();
        Type type  = new TypeToken<ArrayList<Book>>() {}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(ALREADY_READ_BOOKS,null),type);
        return books;
    }

    public ArrayList<Book> getCurrrentlyReadingBook() {
        Gson gson = new Gson();
        Type type  = new TypeToken<ArrayList<Book>>() {}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(CURRENTLY_READING_BOOKS,null),type);
        return books;
    }

    public ArrayList<Book> getFavoriteBooks() {
        Gson gson = new Gson();
        Type type  = new TypeToken<ArrayList<Book>>() {}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(FAVORITE_BOOKS,null),type);
        return books;
    }

    public ArrayList<Book> getWantToRead() {
        Gson gson = new Gson();
        Type type  = new TypeToken<ArrayList<Book>>() {}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(WANT_TO_READ_BOOKS,null),type);
        return books;
    }
    public Book getBookById(int id)
    {
        ArrayList<Book> books = getAllBooks();//getting book from shared preferences
        if(null!=books)
        {
            for(Book b: books)
            {
                if(b.getId()==id)
                {
                    return b;
                }
            }
        }

        return null;
    }

    public boolean addToAlreadyRead(Book book)
    {
        ArrayList<Book> books = getAlreadyReadBook();
        if(null!=books)
        {
            if(books.add(book))//it will add book to our arraylist and return true else returns false
            {
                //to add book to sharedpreferences
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(ALREADY_READ_BOOKS);
                editor.putString(ALREADY_READ_BOOKS,gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }
    public boolean addToWantToReadBooks(Book book)
    {
        ArrayList<Book> books = getWantToRead();
        if(null!=books)
        {
            if(books.add(book))//it will add book to our arraylist and return true else returns false
            {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(WANT_TO_READ_BOOKS);
                editor.putString(WANT_TO_READ_BOOKS,gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }
    public boolean addToFavoriteBooks(Book book)
    {
        ArrayList<Book> books = getFavoriteBooks();
        if(null!=books)
        {
            if(books.add(book))//it will add book to our arraylist and return true else returns false
            {
                //to add book to sharedpreferences
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(FAVORITE_BOOKS);
                editor.putString(FAVORITE_BOOKS,gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
//        return favoriteBooks.add(book);
    }
    public boolean addToCurrentlyReadBooks(Book book)
    {
        ArrayList<Book> books = getCurrrentlyReadingBook();
        if(null!=books)
        {
            if(books.add(book))//it will add book to our arraylist and return true else returns false
            {
                //to add book to sharedpreferences
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(CURRENTLY_READING_BOOKS);
                editor.putString(CURRENTLY_READING_BOOKS,gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean removeFromAlreadyRead(Book book)
    {
        ArrayList<Book> books = getAlreadyReadBook();
        if(null!=books)
        {
            for (Book b : books)
            {
                if(b.getId() == book.getId())
                {
                    if(books.remove(b))
                    {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(ALREADY_READ_BOOKS);
                        editor.putString(ALREADY_READ_BOOKS,gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean removeFromWantToRead(Book book)
    {
        ArrayList<Book> books = getWantToRead();
        if(null!=books)
        {
            for (Book b : books)
            {
                if(b.getId() == book.getId())
                {
                    if(books.remove(b))
                    {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(WANT_TO_READ_BOOKS);
                        editor.putString(WANT_TO_READ_BOOKS,gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean removeFromCurrentlyReading(Book book)
    {
        ArrayList<Book> books = getCurrrentlyReadingBook();
        if(null!=books)
        {
            for (Book b : books)
            {
                if(b.getId() == book.getId())
                {
                    if(books.remove(b))
                    {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(CURRENTLY_READING_BOOKS);
                        editor.putString(CURRENTLY_READING_BOOKS,gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean removeFromfavoriteBooks(Book book)
    {
        ArrayList<Book> books = getFavoriteBooks();
        if(null!=books)
        {
            for (Book b : books)
            {
                if(b.getId() == book.getId())
                {
                    if(books.remove(b))
                    {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(FAVORITE_BOOKS);
                        editor.putString(FAVORITE_BOOKS,gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }


}
