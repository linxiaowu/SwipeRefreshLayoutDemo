package linxiaowu.com.swiperefreshlayoutdemo;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;


public abstract class BaseListFragment extends Fragment {

    private RecyclerView mRecyclerView;

    public RecyclerView getmRecyclerView() {
        return mRecyclerView;
    }

    private SwipeRefreshLayout swipeRefreshLayout;

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
        swipeRefreshLayout.setSize(SwipeRefreshLayout.DEFAULT);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
        swipeRefreshLayout.setProgressViewEndTarget(false, 200);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView view, View itemView, int position) {
                onListItemClick(view, itemView, position);
            }
        }));
    }

    public void onListItemClick(RecyclerView view, View itemView, int position) {
    }

    protected abstract void refresh();

    public void setRefresh(final boolean refreshing) {
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    swipeRefreshLayout.setRefreshing(refreshing);
                }
            }, 1000);
        }
    }


    public void setAdapter(RecyclerView.Adapter<RecyclerAdapter.ViewHolder> recyclerAdapter) {
        mRecyclerView.setAdapter(recyclerAdapter);
    }

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(RecyclerView view, View itemView, int position);
    }

    public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
        GestureDetector mGestureDetector;

        public RecyclerItemClickListener(OnItemClickListener listener) {
            mListener = listener;
            mGestureDetector = new GestureDetector(getActivity(), new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
            View childView = view.findChildViewUnder(e.getX(), e.getY());
            if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
                mListener.onItemClick(view, childView, view.getChildLayoutPosition(childView));
                return true;
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

}
