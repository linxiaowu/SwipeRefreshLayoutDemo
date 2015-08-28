package linxiaowu.com.swiperefreshlayoutdemo;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import linxiaowu.com.swiperefreshlayoutdemo.dataGetter.DataGetter;
import linxiaowu.com.swiperefreshlayoutdemo.model.ClinicType;
import linxiaowu.com.swiperefreshlayoutdemo.model.NetBaseBean;


public class BlankFragment extends BaseListFragment {
    private RecyclerView mRecyclerView;

    public static BlankFragment newInstance() {
        Bundle args = new Bundle();
        BlankFragment fragment = new BlankFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static final String[] TITLES =
            {
                    "Henry IV (1)",
                    "Henry V",
                    "Henry VIII",
                    "Richard II",
                    "Richard III",
                    "Merchant of Venice",
                    "Othello",
                    "King Lear",
                    "Henry IV (1)",
                    "Henry V",
                    "Henry VIII",
                    "Richard II",
                    "Richard III",
                    "Merchant of Venice",
                    "Othello",
                    "King Lear",
                    "Henry IV (1)",
                    "Henry V",
                    "Henry VIII",
                    "Richard II",
                    "Richard III",
                    "Merchant of Venice",
                    "Othello",
                    "King Lear",
                    "Henry IV (1)",
                    "Henry V",
                    "Henry VIII",
                    "Richard II",
                    "Richard III",
                    "Merchant of Venice",
                    "Othello",
                    "King Lear"
            };


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(layoutManager);

//        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(TITLES);
//        mRecyclerView.setAdapter(recyclerAdapter);
//        getSwipeRefreshLayout().setRefreshing(true);
//        getSwipeRefreshLayout().setProgressViewEndTarget(false, 200);
//        startTimeOut();

    }

    @Override
    public void refresh() {
        Toast.makeText(getActivity(), "刷新", Toast.LENGTH_SHORT).show();

        Map<String, String> map = setMap(1);
        DataGetter.getInstance(getActivity()).healthListAction(NetworkAPIs.GET_HEALTH_URL, map, ClinicType.class, new Response.Listener<ClinicType>() {
            @Override
            public void onResponse(ClinicType response) {
                NetBaseBean.AppStatus as = response.getAppStatus();
                if (as != null) {
                    RecyclerAdapter recyclerAdapter = new RecyclerAdapter(response.getAppHealthList());
                    mRecyclerView.setAdapter(recyclerAdapter);
                    getSwipeRefreshLayout().setRefreshing(false);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                getSwipeRefreshLayout().setRefreshing(false);
                Toast.makeText(getActivity(), "网络错误", Toast.LENGTH_SHORT).show();
            }
        });

    }


    //组装参数
    private Map<String, String> setMap(final int index) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("enid", "0");
        map.put("page", String.valueOf(index));
        return map;
    }
}
