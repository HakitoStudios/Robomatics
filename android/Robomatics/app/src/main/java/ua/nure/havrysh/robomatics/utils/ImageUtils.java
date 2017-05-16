package ua.nure.havrysh.robomatics.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImageUtils {
    public static void load(Context context, String uri, ImageView imageView) {
        Glide.with(context)
                .load(uri)
                .asBitmap()
                .into(new RoundBitmapImageViewTarget(imageView, context.getResources()));
    }
}
