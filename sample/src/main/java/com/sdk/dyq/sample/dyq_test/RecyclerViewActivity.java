package com.sdk.dyq.sample.dyq_test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.TextView;

import com.sdk.dyq.multitypemodel.R;
import com.sdk.dyq.sample.MenuBaseActivity;
import com.sdk.dyq.sample.dyq_test.Bean.MessageBean;
import com.sdk.dyq.sample.dyq_test.adapter.RecyclerViewTestAdapter;
import com.sdk.dyq.sample.dyq_test.view.EmptyRecyclerView;
import com.sdk.dyq.sample.dyq_test.view.MsgItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuanqiang on 2017/5/9.
 * RecyclerView的实现listview使用
 */

public class RecyclerViewActivity extends MenuBaseActivity {
    EmptyRecyclerView rvMessageList;
    RecyclerViewTestAdapter rvAdapter;
    List<MessageBean> beanList;
    TextView EmptyView;
    SwipeRefreshLayout refreshLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        init();
    }

    private RecyclerViewTestAdapter getRvAdapter(){
        if(rvAdapter == null){
            rvAdapter = new RecyclerViewTestAdapter();
        }
        return rvAdapter;
    }
    private List<MessageBean> getBeanList(){
        if(beanList == null){
            beanList = new ArrayList<>();
            beanList.add(new MessageBean(0,"dyq","content1",System.currentTimeMillis()+""));
            beanList.add(new MessageBean(1,"1","content2",System.currentTimeMillis()+""));
            beanList.add(new MessageBean(2,"2","content3",System.currentTimeMillis()+""));
            beanList.add(new MessageBean(3,"3","content4",System.currentTimeMillis()+""));
            beanList.add(new MessageBean(0,"dyq","content5",System.currentTimeMillis()+""));
            beanList.add(new MessageBean(4,"4","content6",System.currentTimeMillis()+""));
        }
        return beanList;
    }
    private MessageBean produceNewBean(int i){
        return new MessageBean(((i%5==0)?0:i),((i%5==0)?"dyq":String.valueOf(i)),"content"+i,System.currentTimeMillis()+"");
    }

    private void init(){
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.sw_refresh_layout);
        EmptyView = (TextView) findViewById(R.id.tv_emptyView);

        rvMessageList = (EmptyRecyclerView) findViewById(R.id.rv_messagelist);
        rvMessageList.setEmptyView(EmptyView);

        //线性布局
        LinearLayoutManager manager = new LinearLayoutManager(RecyclerViewActivity.this,LinearLayoutManager.VERTICAL,false);
        rvMessageList.setLayoutManager(manager);
        //分隔线
        MsgItemDecoration decoration = new MsgItemDecoration();
//        rvMessageList.addItemDecoration(decoration);
        DividerItemDecoration dd = new DividerItemDecoration(RecyclerViewActivity.this,DividerItemDecoration.VERTICAL);
        rvMessageList.addItemDecoration(dd);

        getRvAdapter().setBeanList(getBeanList());
        rvMessageList.setAdapter(getRvAdapter());

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                List<MessageBean> beanList = new ArrayList<>();
                for (int i =1;i<=5;i++){
                    beanList.add(produceNewBean(i));
                }
                getBeanList().addAll(beanList);

                //完成后
                getRvAdapter().notifyDataSetChanged();
                refreshLayout.setRefreshing(false);
            }
        });

    }
}
