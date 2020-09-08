package dominando.android.volley;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by Luciano on 12/11/2015.
 */
public class VolleySingleton {

    private static VolleySingleton mInstance;

    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    private VolleySingleton(Context context){
        mRequestQueue = Volley.newRequestQueue(context);
        mImageLoader = new ImageLoader(mRequestQueue, new BitmapCache(50));
    }

    public static VolleySingleton getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new VolleySingleton(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        return this.mRequestQueue;
    }

    public ImageLoader getImageLoader() {
        return this.mImageLoader;
    }

    class BitmapCache extends LruCache implements ImageLoader.ImageCache {

        /**
         * @param maxSize for caches that do not override {@link #sizeOf}, this is
         *                the maximum number of entries in the cache. For all other caches,
         *                this is the maximum sum of the sizes of the entries in this cache.
         */
        public BitmapCache(int maxSize) {
            super(maxSize);
        }

        @Override
        public Bitmap getBitmap(String s) {
            return (Bitmap)get(s);
        }

        @Override
        public void putBitmap(String s, Bitmap bitmap) {
            put(s, bitmap);
        }
    }
}
