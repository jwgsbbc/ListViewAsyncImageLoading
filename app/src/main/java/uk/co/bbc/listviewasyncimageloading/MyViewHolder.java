package uk.co.bbc.listviewasyncimageloading;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

class MyViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;
    public TextView textView;

    public MyViewHolder(View view) {
        super(view);
        imageView = (ImageView) view.findViewById(R.id.image_view);
        textView = (TextView) view.findViewById(R.id.text_view);
    }
}
