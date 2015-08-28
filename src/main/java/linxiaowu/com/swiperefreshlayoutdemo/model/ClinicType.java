package linxiaowu.com.swiperefreshlayoutdemo.model;

import java.util.List;

public class ClinicType extends NetBaseBean {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Count count;
    private List<HealthListBean.HealthBean> content;

    public class Count {
        public String count;
    }

    public Count getCount() {
        return count;
    }

    public void setCount(Count count) {
        this.count = count;
    }


    public List<HealthListBean.HealthBean> getAppHealthList() {
        return content;
    }

    public void setAppHealthList(List<HealthListBean.HealthBean> content) {
        this.content = content;
    }

}

