package com.example.lesson4_recyclerview_27;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {
    public List<Title> list;
    private Context context;
    ItemClickListener listener;


    public MainAdapter(List<Title> list, Context context) {
        this.list = list;
        this.context = context;
    }
     public void setElement(Title title, int position){
        list.set(position,title);
        notifyDataSetChanged();
     }

    public void addApplication(Title title){
        list.add(title);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        holder.onBind(list.get(position));
        holder.txtPopupMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final PopupMenu popupMenu = new PopupMenu(context, holder.txtPopupMenu);
                popupMenu.inflate(R.menu.menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.menu_delete:
                                list.remove(position);
                                notifyDataSetChanged();
                                break;
                            default:
                        }
                        return false;
                    }
                });
                popupMenu.show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;
        TextView txtViewDate;
        Title title;
        TextView txtPopupMenu;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            textView = itemView.findViewById(R.id.txtView);
            txtViewDate = itemView.findViewById(R.id.txtViewDate);
            txtPopupMenu = itemView.findViewById(R.id.txtPopupMenu);
        }

        public void onBind(Title title) {
            this.title = title;
            textView.setText(title.name);
            txtViewDate.setText(title.date);

        }

        @Override
        public void onClick(View v) {
            if (listener != null){
                listener.onItemClick(title, getAdapterPosition());
            }

        }
    }

    public void setOnClick (ItemClickListener mListener){
        this.listener = mListener;
    }

    public interface ItemClickListener {
        void onItemClick(Title title, int pos);
    }
}
