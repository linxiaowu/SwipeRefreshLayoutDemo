package linxiaowu.com.swiperefreshlayoutdemo.net.image;


import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import linxiaowu.com.swiperefreshlayoutdemo.BaseApplication;

/**
 * 默认图片加载器，一般情况下，直接使用
 * @author 
 */
public class DefaultImageLoader extends  ImageLoader{
	
	private static DefaultImageLoader mInstance;
	public static final int MAX_SIZE = 10 * 1024 * 1024;;
	private static final String LOCK = "lock";
	
	private  DefaultImageLoader(RequestQueue requestQueue, ImageCache cache) {
		super(requestQueue, cache);
	}
	
	public static DefaultImageLoader getInstance(){
		if(mInstance==null){
			synchronized (LOCK) {
				if(mInstance==null){
					mInstance = new DefaultImageLoader(Volley.newRequestQueue(BaseApplication.getInstance()), new BitmapCache(MAX_SIZE));
				}
			}
		}
		return mInstance;
	}
	
	
}
