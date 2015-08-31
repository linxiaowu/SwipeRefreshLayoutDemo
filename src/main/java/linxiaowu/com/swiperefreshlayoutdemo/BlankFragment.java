package linxiaowu.com.swiperefreshlayoutdemo;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;
import android.os.Handler;

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
    private RecyclerView mRecyclerView;
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
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(layoutManager);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setRefresh(true);
            }
        }, 365);
        getData(1);
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
                        mRecyclerView.setAdapter(recyclerAdapter);
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
