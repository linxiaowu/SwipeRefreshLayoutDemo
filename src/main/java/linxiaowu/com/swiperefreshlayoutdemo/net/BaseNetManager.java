package linxiaowu.com.swiperefreshlayoutdemo.net;


import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import linxiaowu.com.swiperefreshlayoutdemo.model.BaseBean;
import linxiaowu.com.swiperefreshlayoutdemo.net.request.GsonPostRequest;
import linxiaowu.com.swiperefreshlayoutdemo.net.request.GsonRequest;

import java.util.Map;

public class BaseNetManager {


    public RequestQueue mRequestQueue;

    public BaseNetManager(Context context) {
        init(context);
    }

    private void init(Context context) {
        if (mRequestQueue == null)
            mRequestQueue = Volley.newRequestQueue(context);
    }
    /**
     * 通过GET方式Gson解析，访问网络的唯一方法，其他方法经过组合url后，调用该方法
     * @param <T>  gson解析后的对应Bean类
     * @param url  完整的get方式url地址
     * @param cls  T对应的class
     * @param listener 成功监听器
     * @param errorListener  失败监听器
     */
    public <T extends BaseBean> void getNetMsgBase(String url, Class<T> cls,
                                                   Response.Listener<T> listener, Response.ErrorListener errorListener) {

        GsonRequest<T> request = new GsonRequest<T>(url, cls, null, listener,
                errorListener);
        mRequestQueue.add(request);
//        Log.e("tag", url + "------>url");
    }
    //... 可以添加其他方式的获取，如Post方式，或者使用别的解析方式
    //网络数据获取不要在这个类写，统一在dataget包中的NetDattaGetter中写。
    //这个类是所有网络获取的执行类。
    public <T extends BaseBean> void getNetMsgBaseOfPost(String url,
                                                         String body, Class<T> cls, Map<String, String> params,
                                                         Response.Listener<T> listener, Response.ErrorListener errorListener) {

        GsonPostRequest<T> postrequest = new GsonPostRequest<T>(url, body, cls,
                null, params, listener, errorListener);
        mRequestQueue.add(postrequest);
        //	LogX.i(this, url + "------>url");
    }
}
