package com.test.mindvalley.adapter;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.test.mindvalley.ImageLoading.ImageLoader;
import com.test.mindvalley.R;
import com.test.mindvalley.di.component.MainActivityComponent;
import com.test.mindvalley.di.module.MainActivityContextModule;
import com.test.mindvalley.di.qualifier.ActivityContext;
import com.test.mindvalley.di.qualifier.ApplicationContext;
import com.test.mindvalley.pojo.Example;
import com.test.mindvalley.pojo.ProfileImage;
import com.test.mindvalley.pojo.User;
import com.test.mindvalley.ui.MainActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<Example> data;
    private RecyclerViewAdapter.ClickListener clickListener;
    ImageLoader imageLoader;

    Context context;
    @Inject
    public RecyclerViewAdapter(ClickListener clickListener) {
        this.clickListener = clickListener;
        data = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         context = parent.getContext();
         imageLoader=new ImageLoader(context);
       return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_list_row, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        User user=data.get(position).user;
        ProfileImage profileImage=user.profileImage;
        holder.txtName.setText(user.name);
        holder.txtUsername.setText(user.username);
        holder.txtLikes.setText("Likes : "+data.get(position).likes.toString());

        imageLoader.DisplayImage(profileImage.large,R.drawable.place_stub_error,holder.imageProfile);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtName;
        private TextView txtUsername;
        private TextView txtLikes;
        private ImageView imageProfile;
        private CardView cardView;

        ViewHolder(View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.textView_name);
            txtUsername = itemView.findViewById(R.id.textView_UserName);
            txtLikes = itemView.findViewById(R.id.textView_Likes);
            imageProfile = itemView.findViewById(R.id.imageView_Profile);
            cardView = itemView.findViewById(R.id.cardView);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,"Clicked",Toast.LENGTH_LONG).show();
                 //   clickListener.launchIntent(data.get(getAdapterPosition()));
                }
            });



        }
    }

    public interface ClickListener {
        void launchIntent(String filmName);
    }

    public void setData(List<Example> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }
}
