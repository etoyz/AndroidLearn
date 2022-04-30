package edu.ytu.yangzhao;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewActivity extends AppCompatActivity {
    List<Video> videoList = new ArrayList<>();
    private static final int COUNT = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RecyclerView recyclerView = (RecyclerView) getLayoutInflater().inflate(R.layout.video_item_list, new FrameLayout(getApplicationContext()), false);

//        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        init();

        recyclerView.setAdapter(new VideoRecyclerViewAdapter(videoList));

        setContentView(recyclerView);
    }

    private void init() {
        for (int i = 0; i < COUNT; i++) {
            videoList.add(new Video("id:" + (i + 1), "\t\t\tcontent", "0"));
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}