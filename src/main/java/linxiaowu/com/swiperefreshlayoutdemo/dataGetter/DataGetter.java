package linxiaowu.com.swiperefreshlayoutdemo.dataGetter;


import android.content.Context;

import com.android.volley.Response;

import linxiaowu.com.swiperefreshlayoutdemo.model.NetBaseBean;

import java.util.Map;

public class DataGetter implements IDataGetter {

    private static DataGetter mInstance = null;
    private NetDataGetter mNetDataGetter = null;

    public DataGetter() {

    }

    public static DataGetter getInstance(Context cxt) {
        if (mInstance == null) {
            synchronized (DataGetter.class) {
                if (mInstance == null) {
                    mInstance = new DataGetter();
                    mInstance.init(cxt);
                }
            }
        }
        return mInstance;
    }

    private void init(Context cxt) {
        mNetDataGetter = new NetDataGetter(cxt);
    }

    //养生列表
    @Override
    public <T extends NetBaseBean> void healthListAction(
            String url, Map<String, String> map, Class<T> clazz,
            Response.Listener<T> listener, Response.ErrorListener errorListener) {
        mNetDataGetter.healthListAction(url, map, clazz, listener, errorListener);
    }


}
