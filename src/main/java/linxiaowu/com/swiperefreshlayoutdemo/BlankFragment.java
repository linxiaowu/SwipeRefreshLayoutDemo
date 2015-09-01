package linxiaowu.com.swiperefreshlayoutdemo;


import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import linxiaowu.com.swiperefreshlayoutdemo.dataGetter.DataGetter;
import linxiaowu.com.swiperefreshlayoutdemo.model.ClinicType;
import linxiaowu.com.swiperefreshlayoutdemo.model.HealthListBean;
import linxiaowu.com.swiperefreshlayoutdemo.model.NetBaseBean;


public class BlankFragment extends BaseListFragment {
    private List<HealthListBean.HealthBean> mlist;
    private RecyclerAdapter recyclerAdapter;

    public static BlankFragment newInstance() {
        Bundle args = new Bundle();
        BlankFragment fragment = new BlankFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setRefresh(true);
                getData(1);
            }
        }, 365);
        getmRecyclerView().addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getActivity(),"Click "+position,Toast.LENGTH_SHORT).show();
                recyclerAdapter.notifyItemRemoved(position);
            }
        }));
    }

    @Override
    public void refresh() {
        setRefresh(true);
//        Toast.makeText(getActivity(), "刷新", Toast.LENGTH_SHORT).show();
        getData(2);

    }

    private void getData(final int index) {
        Map<String, String> map = setMap(1);
        DataGetter.getInstance(getActivity()).healthListAction(NetworkAPIs.GET_HEALTH_URL, map, ClinicType.class, new Response.Listener<ClinicType>() {
            @Override
            public void onResponse(ClinicType response) {
                NetBaseBean.AppStatus as = response.getAppStatus();
                if (as != null) {
                    if (index == 1) {
                        mlist = response.getAppHealthList();
                        recyclerAdapter = new RecyclerAdapter(mlist);
                        setAdapter(recyclerAdapter);
                    } else {
                        mlist.addAll(response.getAppHealthList());
                        recyclerAdapter.notifyDataSetChanged();
                    }
                    setRefresh(false);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                getSwipeRefreshLayout().setRefreshing(false);
                setRefresh(false);
                Toast.makeText(getActivity(), "网络错误", Toast.
                        LENGTH_SHORT).show();
            }
        });
    }


    //组装参数
    private Map<String, String> setMap(final int index) {
        Map<String, String> map = new HashMap<>();
        map.put("enid", "0");
        map.put("page", String.valueOf(index));
        return map;
    }
}
