package linxiaowu.com.swiperefreshlayoutdemo.dataGetter;

import android.content.Context;

import com.android.volley.Response;

import linxiaowu.com.swiperefreshlayoutdemo.model.NetBaseBean;
import linxiaowu.com.swiperefreshlayoutdemo.net.BaseNetManager;
import linxiaowu.com.swiperefreshlayoutdemo.util.NetworkUtils;

import java.util.Map;


public class NetDataGetter implements IDataGetter {

    private Context ctx;
    private BaseNetManager mBaseNetManager;


    //养生列表
    @Override
    public <T extends NetBaseBean> void healthListAction(
            String url, Map<String, String> map, Class<T> clazz,
            Response.Listener<T> listener,
            Response.ErrorListener errorListener) {
        String updateUserInfoUrl = NetworkUtils.connectParams(url, map);
        mBaseNetManager.getNetMsgBase(updateUserInfoUrl, clazz, listener, errorListener);
    }


    public NetDataGetter(Context cxt) {
        this.ctx = cxt;
        mBaseNetManager = new BaseNetManager(cxt);
    }

}
