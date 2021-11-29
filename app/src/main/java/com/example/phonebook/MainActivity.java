package com.example.phonebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.ContentUris;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_READ_CONTACTS=1;

    RecyclerView recyclerView;
    List<Contact> contactList;
    ContactAdapter contactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_contacts);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.READ_CONTACTS)== PackageManager.PERMISSION_GRANTED)
        {
            contactList = new ArrayList<Contact>();

            Cursor phonebook = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
            while (phonebook.moveToNext()) {
                String name = phonebook.getString(phonebook.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

                String number = phonebook.getString(phonebook.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));

                String contactID = phonebook.getString(phonebook.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.CONTACT_ID));

                Contact items = new Contact();
                items.setName(name);
                items.setPhone(number);
                items.setPhoto(ContactPhoto(contactID));
                contactList.add(items);

            } phonebook.close();

            contactAdapter = new ContactAdapter(this,contactList);
            if(recyclerView != null){
                recyclerView.setAdapter(contactAdapter);
            }
        }
        else
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS},REQUEST_READ_CONTACTS);

    }

    public Bitmap ContactPhoto(String contactID) {
        Uri contactUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI,Long.valueOf(contactID));
        Uri photoUri = Uri.withAppendedPath(contactUri,ContactsContract.Contacts.Photo.CONTENT_DIRECTORY);
        Cursor cursor = getContentResolver().query(photoUri,new String[]{ContactsContract.Contacts.Photo.PHOTO},null,null,null);
        if (cursor != null && cursor.getCount()>0){
            cursor.moveToNext();
            byte[] data = cursor.getBlob(0);
            if(data != null)
                return BitmapFactory.decodeStream(new ByteArrayInputStream(data));
            else
                return null;
        }cursor.close();
        return null;
    }
}