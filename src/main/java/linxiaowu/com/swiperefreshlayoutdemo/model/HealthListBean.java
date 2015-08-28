package linxiaowu.com.swiperefreshlayoutdemo.model;


import java.util.ArrayList;

/**
 * 养生列表Bean
 */
public class HealthListBean extends NetBaseBean {
    private static final long serialVersionUID = -5636065271940348963L;

    private ArrayList<HealthBean> content;
    private Count count;

    public class HealthBean extends BaseBean {
        private static final long serialVersionUID = 1L;
        public int ROWNUM;
        public int AID;
        public String TITLE;
        public String ADDTIME;
        public String PHOTO;
        public String ISTOP;
        public Count count;
    }

    public class Count {
        public String count;
    }

    public ArrayList<HealthBean> getContent() {
        return content;
    }

    public void setContent(ArrayList<HealthBean> content) {
        this.content = content;
    }

    public String getCount() {
        return this.count.count;
    }

    public void setCount(String count) {
        this.count.count = count;
    }

}
