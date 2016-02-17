package uk.co.bbc.listviewasyncimageloading;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView listView = (RecyclerView) findViewById(R.id.list_view);
        EpisodeListViewImpl myEpisodeListView = new EpisodeListViewImpl(this, listView);
        EpisodePresenter episodePresenter = new EpisodePresenter(new EpisodeProvider(), new EpisodeImageService(), new ImageCache());
        episodePresenter.onViewCreated(myEpisodeListView);
    }

}
