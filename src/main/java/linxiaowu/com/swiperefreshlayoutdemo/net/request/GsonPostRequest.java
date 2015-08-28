package linxiaowu.com.swiperefreshlayoutdemo.net.request;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;

import linxiaowu.com.swiperefreshlayoutdemo.NetworkAPIs;
import linxiaowu.com.swiperefreshlayoutdemo.util.LogX;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class GsonPostRequest<T> extends GsonRequest<T> {

    String mBody;
    Map<String, String> params;
    private final Gson gson = new Gson();
    private final Class<T> clazz;
    private final Map<String, String> headers;

    public GsonPostRequest(String url, String body, Class<T> clazz,
                           Map<String, String> headers, Map<String, String> params,
                           Listener<T> listener, ErrorListener errorListener) {
        super(Method.POST, url, clazz, headers, listener, errorListener);
        mBody = body;
        this.params = params;
        this.clazz = clazz;
        this.headers = headers;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Charset", "UTF-8");
        headers.put(NetworkAPIs.KEY, NetworkAPIs.VALUE);
        return headers;
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        LogX.i(this, "post arg : [" + mBody + "]");
        return mBody == null ? super.getBody() : mBody.getBytes();
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        // TODO Auto-generated method stub
        if (params == null)
            return null;
        return params;
    }

    // Json数据 解析
    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(response.data, "utf-8");
            LogX.i(this, json);
            return Response.success(gson.fromJson(json, clazz),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return Response.error(new ParseError(e));
        }
    }

}
