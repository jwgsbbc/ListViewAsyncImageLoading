package uk.co.bbc.listviewasyncimageloading;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

class EpisodeListViewImpl implements EpisodeListView {

    private final MyAdapter mAdapter = new MyAdapter();

    private EpisodeViewModelProvider mEpisodeViewModelProvider;

    private int mEpisodeCount = 0;

    public EpisodeListViewImpl(Context context, RecyclerView listView) {
        LinearLayoutManager layout = new LinearLayoutManager(context);
        listView.setLayoutManager(layout);
        DefaultItemAnimator animator = new DefaultItemAnimator();
        animator.setSupportsChangeAnimations(false);
        listView.setItemAnimator(animator);
        listView.setAdapter(mAdapter);
    }

    private class MyAdapter extends RecyclerView.Adapter {
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_view, parent, false);
            return new EpisodeViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            EpisodeViewHolder vh = (EpisodeViewHolder) holder;
            EpisodeViewModel viewModel = mEpisodeViewModelProvider.getEpisodeViewModel(position);
            Bitmap image = viewModel.image;
            if (image == null) {
                vh.clearImage();
            } else {
                vh.setImage(image);
            }
            vh.setText(viewModel.title);
        }

        @Override
        public int getItemCount() {
            return mEpisodeCount;
        }
    }

    @Override
    public void setEpisodeViewModelProvider(EpisodeViewModelProvider episodeImageProvider) {
        mEpisodeViewModelProvider = episodeImageProvider;
    }

    @Override
    public void setEpisodeCount(int episodeCount) {
        mEpisodeCount = episodeCount;
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void notifyItemChanged(int position) {
        mAdapter.notifyItemChanged(position);
    }
}
