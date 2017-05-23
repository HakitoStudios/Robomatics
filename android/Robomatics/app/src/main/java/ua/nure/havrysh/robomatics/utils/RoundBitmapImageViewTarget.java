package ua.nure.havrysh.robomatics.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.bumptech.glide.request.target.BitmapImageViewTarget;

public class RoundBitmapImageViewTarget extends BitmapImageViewTarget {

    @NonNull
    private final ImageView imageView;

    @NonNull
    private final Resources resources;

    public RoundBitmapImageViewTarget(@NonNull ImageView imageView, @NonNull Resources resources) {
        super(imageView);
        this.imageView = imageView;
        this.resources = resources;
    }

    @Override
    protected void setResource(@NonNull Bitmap resource) {

        RoundedBitmapDrawable circularBitmapDrawable =
                RoundedBitmapDrawableFactory.create(resources, resource);
        circularBitmapDrawable.setCircular(true);
        imageView.setImageDrawable(circularBitmapDrawable);
    }
}