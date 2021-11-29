package com.example.phonebook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder>{
    Context mContext;
    List<Contact> contactList;

    public ContactAdapter(Context mContext,List<Contact> contactList){
        this.mContext=mContext;
        this.contactList=contactList;
    }


    @NonNull
    @Override
    public ContactAdapter.ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.items,parent,false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.ContactViewHolder holder, int position) {
        Contact contact = contactList.get(position);
        holder.name_contact.setText(contact.getName());
        holder.phone_contact.setText(contact.getPhone());

        if(contact.getPhoto() != null){
            holder.img_contact.setImageBitmap(contact.getPhoto());
        }else{
            holder.img_contact.setImageResource(R.mipmap.ic_launcher_round);
        }

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder{
        TextView name_contact,phone_contact;
        ImageView img_contact;

        public ContactViewHolder(@Nullable View itemView){
            super(itemView);
            name_contact = itemView.findViewById(R.id.txt_name);
            phone_contact = itemView.findViewById(R.id.txt_phone);
            img_contact = itemView.findViewById((R.id.img_photo));

        }
    }
}
