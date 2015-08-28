package linxiaowu.com.swiperefreshlayoutdemo.model;


public class NetBaseBean extends BaseBean {

    private AppStatus appStatus;

    public class AppStatus {

        public String errorCode;
        public String success;
        public String msg;

    }
    private static final long serialVersionUID = 1L;

    public AppStatus getAppStatus() {
        return appStatus;
    }

    public void setAppStatus(AppStatus appStatus) {
        this.appStatus = appStatus;
    }

}
