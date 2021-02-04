package com.phaytran.demoretrofit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.phaytran.demoretrofit.R;
import com.phaytran.demoretrofit.model.Todo;

import java.util.List;

public class ListTodoAdapter extends RecyclerView.Adapter<ListTodoAdapter.ViewHolder> {

    List<Todo> todoList;

    private OnIconClicked listener;

    public ListTodoAdapter(List<Todo> todoList) {
        this.todoList = todoList;
    }

    public void setListener(OnIconClicked onIconClicked){
        this.listener = onIconClicked;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_todo, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tvName.setText(todoList.get(position).getName());
        holder.tvDesc.setText(todoList.get(position).getDescription());
        holder.imvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onDelete(todoList.get(position).getId());
            }
        });
        holder.imvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onEdit(todoList.get(position).getId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvDesc;
        ImageView imvDelete,imvEdit;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.todoName);
            tvDesc = itemView.findViewById(R.id.todoDesc);
            imvDelete = itemView.findViewById(R.id.icDelete);
            imvEdit = itemView.findViewById(R.id.icEdit);

        }
    }

    public interface  OnIconClicked{
        void onDelete(int id);
        void onEdit(int id);
    }


}