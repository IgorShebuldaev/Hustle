package org.hustle.ui.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import org.hustle.QueryTask;
import org.hustle.R;
import org.hustle.RecyclerViewAdapter;

import java.net.MalformedURLException;

import static org.hustle.utils.Network.getURL;

public class NewsFragment extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_news, container, false);
        final SwipeRefreshLayout refreshLayout = view.findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
               updateData();
               refreshLayout.setRefreshing(false);
            }
        });

        updateData();

        return view;
    }

    private void updateData() {
        try {
            RecyclerView recyclerView = view.findViewById(R.id.rcView);
            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
            RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(view.getContext());
            recyclerView.setAdapter(recyclerViewAdapter);
            new QueryTask(recyclerViewAdapter).execute(getURL());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
