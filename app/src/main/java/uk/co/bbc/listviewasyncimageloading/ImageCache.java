package uk.co.bbc.listviewasyncimageloading;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

public final class ImageCache {

    private final LruCache<String, Bitmap> mCache;

    public ImageCache() {
        mCache = new LruCache<>(20);
    }

    public Bitmap getImage(String id) {
        return mCache.get(id);
    }

    public void putImage(String id, Bitmap image) {
        mCache.put(id, image);
    }
}
