package com.saliim.latihansearch.detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.saliim.latihansearch.R;
import com.saliim.latihansearch.pojo.ResultsItem;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA = "OBJECT";

    @BindView(R.id.detail_gambar)
    ImageView detailGambar;
    @BindView(R.id.detail_judul)
    TextView detailJudul;
    @BindView(R.id.detail_tgl)
    TextView detailTgl;
    @BindView(R.id.detail_popular)
    TextView detailPopular;
    @BindView(R.id.detail_overview)
    TextView detailOverview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        ResultsItem ambil = getIntent().getParcelableExtra(EXTRA);

        Glide.with(DetailActivity.this)
                .load(ambil.getPosterPath())
                .into(detailGambar);
        detailJudul.setText(ambil.getTitle());
        detailTgl.setText(ambil.getReleaseDate());
        detailPopular.setText(String.valueOf(ambil.getPopularity()));
        detailOverview.setText(ambil.getOverview());
    }
}
