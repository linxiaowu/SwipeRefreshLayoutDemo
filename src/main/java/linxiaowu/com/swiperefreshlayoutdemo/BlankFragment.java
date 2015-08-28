package linxiaowu.com.swiperefreshlayoutdemo;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class BlankFragment extends BaseListFragment {


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
//        ListView listView = (ListView) view.findViewById(R.id.recyclerView);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);


//        ArrayAdapter arrayAdapter = new ArrayAdapter<>(getActivity(),
//                android.R.layout.simple_list_item_1, android.R.id.text1, TITLES);
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(TITLES);
        recyclerView.setAdapter(recyclerAdapter);
    }
//
//    @Override
//    protected void refresh() {
//        Toast.makeText(getActivity(), "刷新", Toast.LENGTH_SHORT).show();
//    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        Toast.makeText(getActivity(), "刷新", Toast.LENGTH_SHORT).show();
//        try {
//            Thread.sleep(3000);
//            getSwipeRefreshLayout().setRefreshing(false);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }
}
