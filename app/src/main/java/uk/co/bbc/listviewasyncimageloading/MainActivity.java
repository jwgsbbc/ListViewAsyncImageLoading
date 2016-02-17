package uk.co.bbc.listviewasyncimageloading;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String[] mIds = {
            "p03j0cbd",
            "p03j0c99",
            "p03hjq53",
            "p03jqy1t",
            "p03j7nq4",
            "p03hbntf",
            "p03j0fdd",
            "p03j3n65",
            "p03hx9jp",
            "p03j0gjt",
            "p03j38jw",
            "p03gtcgf",
            "p03hmc9h",
            "p03hxtgm",
            "p03hfnpc",
            "p03j40mp",
            "p03hbc4t",
            "p03hxfkj",
            "p03gmp16",
            "p03hf81d",
            "p03j3mtf",
            "p03j0ky5",
            "p03gqcvw",
            "p03jbjj6",
            "p03j3nyf",
            "p03hdrkn",
            "p03gmpnp",
            "p03hxq7z",
            "p03hmbv6",
            "p03jc5r0",
            "p03j3m9l",
            "p03j1rlr",
    };

    private ImageCache mImageCache = new ImageCache();
    private ImageService mImageService = new ImageService();
    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView listView = (RecyclerView) findViewById(R.id.list_view);
        LinearLayoutManager layout = new LinearLayoutManager(this);
        listView.setLayoutManager(layout);
        DefaultItemAnimator animator = new DefaultItemAnimator();
        animator.setSupportsChangeAnimations(false);
        listView.setItemAnimator(animator);
        mAdapter = new MyAdapter();
        listView.setAdapter(mAdapter);

    }

    private Bitmap requestImage(final int position) {
        Bitmap cached = getCachedImage(position);
        if(cached==null) {
            mImageService.requestImage(getId(position), new ImageReceiver() {
                @Override
                public void onImageReceived(final Bitmap image) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            cacheImage(image, position);
                            mAdapter.notifyItemChanged(position);
                        }
                    });
                }
            });
        }
        return cached;
    }

    private void cacheImage(Bitmap image, int position) {
        mImageCache.putImage(getId(position), image);
    }

    private String getId(int position) {
        return mIds[position];
    }

    private Bitmap getCachedImage(int position) {
        return mImageCache.getImage(getId(position));
    }

    private static class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;

        public MyViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.image_view);
            textView = (TextView) view.findViewById(R.id.text_view);
        }
    }

    private class MyAdapter extends RecyclerView.Adapter {
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_view, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            MyViewHolder vh = (MyViewHolder) holder;
            Bitmap image = requestImage(position);
            if(image!=null) {
                vh.imageView.setVisibility(View.VISIBLE);
                vh.imageView.setImageBitmap(image);
            }
            else {
                vh.imageView.setVisibility(View.INVISIBLE);
            }
            vh.textView.setText(getId(position));
        }

        @Override
        public int getItemCount() {
            return mIds.length;
        }
    }
}
