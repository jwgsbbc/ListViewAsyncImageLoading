package uk.co.bbc.listviewasyncimageloading;

import android.graphics.Bitmap;

public interface ImageReceiver {
    void onImageReceived(Bitmap image);
}
