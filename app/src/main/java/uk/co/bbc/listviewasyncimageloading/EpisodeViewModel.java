package uk.co.bbc.listviewasyncimageloading;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

class EpisodeViewModel {

    @NonNull
    public final String title;

    @Nullable
    public final Bitmap image;

    public EpisodeViewModel(@NonNull String title, @Nullable Bitmap image) {
        this.title = title;
        this.image = image;
    }
}
