package com.application.week7roomclass2.recyclerview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.application.week7roomclass2.R;
import com.application.week7roomclass2.UpdateActivity;
import com.application.week7roomclass2.data.Person;
import com.application.week7roomclass2.data.PersonViewModel;

public class PersonListAdapter extends ListAdapter<Person, PersonListAdapter.MyViewHolder> {

    Context context;
    PersonViewModel personViewModel;

    public PersonListAdapter(@NonNull DiffUtil.ItemCallback<Person> diffCallback, Context context, PersonViewModel personViewModel) {
        super(diffCallback);
        this.context = context;
        this.personViewModel = personViewModel;
    }


    @NonNull
    @Override
    public PersonListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonListAdapter.MyViewHolder holder, int position) {
        Person current = getItem(position);
        holder.name.setText(current.getName());
        holder.email.setText(current.getEmail());

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("model", current);
                ((Activity) context).startActivityForResult(intent, 2);
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                personViewModel.delete(current);
            }
        });

    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name, email;
        ImageView edit, delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.textView);
            email = itemView.findViewById(R.id.textView2);
            edit = itemView.findViewById(R.id.imageView);
            delete = itemView.findViewById(R.id.imageView2);


        }
    }

    public static class PersonDiff extends  DiffUtil.ItemCallback<Person>{

        @Override
        public boolean areItemsTheSame(@NonNull Person oldItem, @NonNull Person newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Person oldItem, @NonNull Person newItem) {
            return oldItem.getName().equals(newItem.getName());
        }
    }
}
