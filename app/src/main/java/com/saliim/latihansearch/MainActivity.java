package com.saliim.latihansearch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;

import com.saliim.latihansearch.adapter.CustomAdapter;
import com.saliim.latihansearch.network.ApiService;
import com.saliim.latihansearch.network.ConfigRetrofit;
import com.saliim.latihansearch.pojo.ResponseSearch;
import com.saliim.latihansearch.pojo.ResultsItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.btn_search)
    Button btnSearch;
    @BindView(R.id.list_recycle)
    RecyclerView listRecycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_search)
    public void onViewClicked() {

        getSearch();
    }

    private void getSearch() {
        String serch = etSearch.getText().toString();

        if (serch.isEmpty()) {
            etSearch.setError("isi doeloe");
        } else {
            ApiService apiService = ConfigRetrofit.getInsteance();

            retrofit2.Call<ResponseSearch> call = apiService.searchin(BuildConfig.API_KEY, BuildConfig.LANGUAGE, serch);

            call.enqueue(new Callback<ResponseSearch>() {
                @Override
                public void onResponse(retrofit2.Call<ResponseSearch> call, Response<ResponseSearch> response) {

                    if (response.isSuccessful()) {
                        List<ResultsItem> dataMovies = response.body().getResults();
                        listRecycle.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                        listRecycle.setHasFixedSize(true);
                        CustomAdapter adapter = new CustomAdapter(MainActivity.this, dataMovies);
                        listRecycle.setAdapter(adapter);
                    }

                }

                @Override
                public void onFailure(retrofit2.Call<ResponseSearch> call, Throwable t) {

                }
            });
        }
    }
}
