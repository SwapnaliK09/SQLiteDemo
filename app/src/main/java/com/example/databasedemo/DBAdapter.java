package com.example.databasedemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DBAdapter extends RecyclerView.Adapter<DBAdapter.ViewHolder> {
    ArrayList<UserModel> userModelArrayList = new ArrayList<>();
    private Context context;

    public DBAdapter(ArrayList<UserModel> userModelArrayList, Context context) {
        this.userModelArrayList = userModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.info_item, parent, false);
        return new ViewHolder(view);

}

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserModel userModel = userModelArrayList.get(position);
        holder.txt_api.setText(userModel.getAPI());
        holder.txt_desc.setText(userModel.getDescription());
        holder.txt_auth.setText(userModel.getAuth());

    }

    @Override
    public int getItemCount() {
        return userModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txt_api, txt_desc, txt_auth;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_api = itemView.findViewById(R.id.api_txt);
            txt_desc = itemView.findViewById(R.id.des_txt);
            txt_auth = itemView.findViewById(R.id.auth_txt);
        }

    }
}
