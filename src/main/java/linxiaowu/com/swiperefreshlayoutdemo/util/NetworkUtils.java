package linxiaowu.com.swiperefreshlayoutdemo.util;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import linxiaowu.com.swiperefreshlayoutdemo.net.image.DefaultImageLoader;

public class NetworkUtils {


    /**
     * 是否存在网络连接
     */
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager
                    .getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * WIFI是否连接
     */
    public static boolean isWifiConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWiFiNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (mWiFiNetworkInfo != null) {
                return mWiFiNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 手机网络是否连接
     */
    public static boolean isMobileConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mMobileNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (mMobileNetworkInfo != null) {
                return mMobileNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 当前网络类型
     */
    public static int getConnectedType(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager
                    .getActiveNetworkInfo();
            if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {
                return mNetworkInfo.getType();
            }
        }
        return -1;
    }

    /**
     * 检测网络
     */
    public static boolean checkNetwork(Activity activity) {
        if (!NetworkUtils.isNetworkConnected(activity)) {
            dialog(activity);
            return false;
        }
        return true;
    }

    /**
     * 弹出dialog
     */
    protected static void dialog(final Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage("网络连接没有打开！");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        Dialog dialog = builder.create();
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                activity.finish();
            }
        });
        dialog.show();
    }

    /**
     * 在URL后连接参数，主要是GET方式时使用
     */
    public static String connectParams(String baseUrl, Map<String, String> map) {

        if (map == null)
            return baseUrl;

        StringBuilder param = new StringBuilder();
        param.append(baseUrl);
        if (!baseUrl.endsWith("?")) {
            param.append("?");
        }

        int i = 0;
        for (String key : map.keySet()) {
            if (i != 0)
                param.append("&");
            try {
                param.append(key).append("=").append(URLEncoder.encode(map.get(key), "utf-8"));
                i++;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        baseUrl = param.toString();
        Log.i("NetworkUtils", "路径: " + baseUrl);
        return baseUrl;
    }

    public static String imageUrl(String url) {
        String replace = url.replace('\\', '/');
        return replace;
    }

    public static ImageLoader.ImageListener getImageListener(ImageView view) {
        ImageLoader.ImageListener listener = DefaultImageLoader.getImageListener(view,
                android.R.drawable.ic_menu_report_image,
                android.R.drawable.ic_menu_report_image);
        return listener;
    }

}
