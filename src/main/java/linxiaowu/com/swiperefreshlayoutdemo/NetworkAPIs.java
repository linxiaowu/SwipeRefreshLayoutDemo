package linxiaowu.com.swiperefreshlayoutdemo;

/**
 * 网络接口API
 */
public class NetworkAPIs {


    //        private static final String BASE_URL = "http://192.168.1.249:10008/API/"; //内网
    //
    private static final String BASE_URL = "http://ibona.xicp.net:10008/API/";  //外网
    // 图片拼接地址
//    public static final String IMAGE_URL = "http://192.168.1.249:10008";

    // 消息头
    public static final String KEY = "1knet-app-key";
    public static final String VALUE = "B493AF91AA95DEF0F12E4BB9C544BA8C";

    // 查询养生列表
    public static final String GET_HEALTH_URL = BASE_URL + "Encyclopaedia/GetKnowledgeListForPage.aspx";

    public static final String IMAGE_URL = "http://ibona.xicp.net:10008";

}
