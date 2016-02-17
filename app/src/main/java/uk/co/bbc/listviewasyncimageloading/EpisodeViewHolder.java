package uk.co.bbc.listviewasyncimageloading;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

class EpisodeViewHolder extends RecyclerView.ViewHolder {
    private ImageView imageView;
    private TextView textView;
    private boolean needsFade;

    public EpisodeViewHolder(View view) {
        super(view);
        imageView = (ImageView) view.findViewById(R.id.image_view);
        textView = (TextView) view.findViewById(R.id.text_view);
    }

    public void setImage(Bitmap image) {
        imageView.setVisibility(View.VISIBLE);
        imageView.setImageBitmap(image);
        if(needsFade) {
            needsFade = false;
            imageView.setAlpha(0.0f);
            imageView.animate().alpha(1.0f).start();
        }
    }

    public void clearImage() {
        imageView.setVisibility(View.INVISIBLE);
        needsFade = true;
    }

    public void setText(String title) {
        textView.setText(title);
    }
}
