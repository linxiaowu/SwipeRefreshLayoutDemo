package linxiaowu.com.swiperefreshlayoutdemo;

import android.app.Application;

import linxiaowu.com.swiperefreshlayoutdemo.dataGetter.DataGetter;

public class BaseApplication extends Application {
    private static BaseApplication instance;
    private static DataGetter getter;

    public static BaseApplication getInstance() {
        return instance == null ? null : instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        getter = DataGetter.getInstance(instance);
    }

    public static DataGetter getGetter() {
        return getter;
    }
}
