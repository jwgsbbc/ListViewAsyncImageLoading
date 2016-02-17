package uk.co.bbc.listviewasyncimageloading;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public final class ImageService {

    private final OkHttpClient client = new OkHttpClient();

    public void requestImage(String id, final ImageReceiver imageReceiver) {

        String url = "http://ichef.bbci.co.uk/images/ic/480x270/"+id+".jpg";

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                byte[] bytes = response.body().bytes();
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                if(bitmap!=null) {
                    imageReceiver.onImageReceived(bitmap);
                }
            }
        });
    }
}
