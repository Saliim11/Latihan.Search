package com.saliim.latihansearch.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.saliim.latihansearch.BuildConfig;
import com.saliim.latihansearch.R;
import com.saliim.latihansearch.detail.DetailActivity;
import com.saliim.latihansearch.pojo.ResultsItem;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    Context context;
    List<ResultsItem> data1;

    public CustomAdapter(Context context, List<ResultsItem> data1) {
        this.context = context;
        this.data1 = data1;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {
        final String judul = data1.get(i).getTitle();
        final String tgl = data1.get(i).getReleaseDate();
        final double popular = data1.get(i).getPopularity();
        final String overview = data1.get(i).getOverview();
        final String gambar =  BuildConfig.IMAGES + data1.get(i).getPosterPath();

        myViewHolder.txtJudul.setText(judul);

//        Picasso.get().load(BuildConfig.IMAGES + gambar).into(myViewHolder.imgGambar);
        Glide.with(context)
                .load(gambar)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(myViewHolder.imgGambar);

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResultsItem data = new ResultsItem();
                data.setTitle(judul);
                data.setPosterPath(gambar);
                data.setReleaseDate(tgl);
                data.setPopularity(popular);
                data.setOverview(overview);

                Intent kirim = new Intent(context, DetailActivity.class);
                kirim.putExtra(DetailActivity.EXTRA, data);
                context.startActivity(kirim);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data1.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_gambar)
        ImageView imgGambar;
        @BindView(R.id.txt_judul)
        TextView txtJudul;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
