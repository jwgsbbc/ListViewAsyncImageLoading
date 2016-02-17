package uk.co.bbc.listviewasyncimageloading;

import android.graphics.Bitmap;

final class EpisodePresenter implements Presenter<EpisodeListView> {

    private final ImageCache mImageCache;
    private final ImageService mImageService;
    private final String[] mIds;

    private EpisodeListView mView;

    public EpisodePresenter(String[] ids, ImageCache imageCache, ImageService imageService) {
        mImageCache = imageCache;
        mImageService = imageService;
        mIds = ids;
    }

    @Override
    public void onViewCreated(EpisodeListView view) {
        mView = view;
        view.setEpisodeViewModelImageProvider(new MyEpisodeViewModelProvider());
        view.setEpisodeCount(mIds.length);
    }

    @Override
    public void onViewDestroyed() {
        mView = null;
    }

    private Bitmap requestImage(final int position) {
        String id = mIds[position];
        Bitmap cached = mImageCache.getImage(id);
        if(cached==null) {
            mImageService.requestImage(id, new MyImageReceiver(position));
        }
        return cached;
    }

    private class MyEpisodeViewModelProvider implements EpisodeListView.EpisodeViewModelProvider {
        @Override
        public EpisodeViewModel getEpisodeViewModel(int position) {
            String title = mIds[position];
            Bitmap image = requestImage(position);
            return new EpisodeViewModel(title, image);
        }
    }

    private class MyImageReceiver implements ImageReceiver {
        private final int mPosition;

        public MyImageReceiver(int position) {
            mPosition = position;
        }

        @Override
        public void onImageReceived(final Bitmap image) {
            mImageCache.putImage(mIds[mPosition], image);
            if(mView!=null) {
                mView.notifyItemChanged(mPosition);
            }
        }
    }
}
