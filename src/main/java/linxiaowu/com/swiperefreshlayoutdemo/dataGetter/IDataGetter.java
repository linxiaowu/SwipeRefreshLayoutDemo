package linxiaowu.com.swiperefreshlayoutdemo.dataGetter;


import com.android.volley.Response;
import linxiaowu.com.swiperefreshlayoutdemo.model.NetBaseBean;

import java.util.Map;

public interface IDataGetter {

    //养生列表
    <T extends NetBaseBean> void healthListAction(String url, Map<String, String> map, Class<T> clazz, Response.Listener<T> listener, Response.ErrorListener errorListener);

}
