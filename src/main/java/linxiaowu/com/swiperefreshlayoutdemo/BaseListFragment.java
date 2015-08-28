package linxiaowu.com.swiperefreshlayoutdemo;


import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public abstract class BaseListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


    private SwipeRefreshLayout swipeRefreshLayout;

    public SwipeRefreshLayout getSwipeRefreshLayout() {
        return swipeRefreshLayout;
    }

    private final Runnable refreshDone = new Runnable() {
        @Override
        public void run() {
            timeOut();
        }
    };

    private void timeOut() {

        swipeRefreshLayout.setRefreshing(false);
        if (isAdded())
            Toast.makeText(getActivity().getApplicationContext(), "超时", Toast.LENGTH_SHORT).show();
    }

    private Handler handler = new Handler();

    public BaseListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_base_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeLayout);
//        swipeRefreshLayout.setColorSchemeColors(
//                R.color.color1);
        swipeRefreshLayout.setSize(SwipeRefreshLayout.DEFAULT);

//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//            }
//        });
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setProgressViewEndTarget(false, 200);
    }


//    protected abstract void refresh();

    @Override
    public void onRefresh() {
        handler.removeCallbacks(refreshDone);
        handler.postDelayed(refreshDone, 15000);
    }
}
