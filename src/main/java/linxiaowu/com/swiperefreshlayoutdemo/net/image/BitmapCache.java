package linxiaowu.com.swiperefreshlayoutdemo.net.image;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader.ImageCache;

public class BitmapCache implements ImageCache {
    
	/**
	 * 图片缓存机制
	 */
    private LruCache<String, Bitmap> mCache;
    
    public BitmapCache(int maxSize) {
        mCache = new LruCache<String, Bitmap>(maxSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight();
            }
        };
    }

    @Override
    public Bitmap getBitmap(String url) {
        return mCache.get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
    	//#W0#H0http://192.168.1.247:8999/upload/GoodsImage/201500010017045969927.jpg
        mCache.put(url, bitmap);
    }

}
