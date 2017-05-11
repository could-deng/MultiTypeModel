package com.sdk.dyq.sample.dyq_test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sdk.dyq.multitypemodel.R;
import com.sdk.dyq.sample.MenuBaseActivity;
import com.sdk.dyq.sample.dyq_test.view.DividerGridItemDecoration;
import com.sdk.dyq.sample.dyq_test.view.EmptyRecyclerView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by yuanqiang on 2017/5/10.
 * RecyclerView的实现gridview(瀑布流效果)使用
 */

public class RecyclerGridActivity extends MenuBaseActivity {
    private Button btAdd;
    private EmptyRecyclerView emptyRV;
    private List<String> mDatas;
    private List<Integer> mHeights;//item的随机params
    private HomeAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_gridv);
        initData();
        init();
    }

    protected void initData()
    {
        mDatas = new ArrayList<>();
        for (int i = 'A'; i < 'z'; i++)
        {
            mDatas.add("" + (char) i);
        }
        mHeights = new ArrayList<Integer>();
        for (int i = 0; i < mDatas.size(); i++)
        {
            mHeights.add( (int) (100 + Math.random() * 300));
        }
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.bt_add:
                adapter.addData(1);
                break;
        }
    }
    private void init(){
        btAdd = (Button) findViewById(R.id.bt_add);
        emptyRV = (EmptyRecyclerView) findViewById(R.id.empty_rv);

        adapter = new HomeAdapter();

//        emptyRV.setLayoutManager(new GridLayoutManager(this,4));
        emptyRV.setLayoutManager(new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL));//改为Horizontal则表现为滚动的GridView效果
        emptyRV.setItemAnimator(new DefaultItemAnimator());//动画
        emptyRV.addItemDecoration(new DividerGridItemDecoration(this));
        adapter.setItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(RecyclerGridActivity.this,"click"+position,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(RecyclerGridActivity.this,"longClick"+position,Toast.LENGTH_SHORT).show();
            }
        });
        emptyRV.setAdapter(adapter);
    }






    /**
     * 点击事件
     */
    interface OnItemClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    /**
     * 适配器
     */
    class HomeAdapter extends RecyclerView.Adapter<MyViewHolder>{
        private OnItemClickListener itemClickListener;

        public OnItemClickListener getItemClickListener() {
            return itemClickListener;
        }
        public void setItemClickListener(OnItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder myViewHolder = new MyViewHolder(LayoutInflater.from(RecyclerGridActivity.this).inflate(R.layout.activity_recyclerview_gridv_item,parent,false));
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {
            String data = mDatas.get(position);

            ViewGroup.LayoutParams lp = holder.tv.getLayoutParams();
            lp.height = mHeights.get(position);
            holder.tv.setLayoutParams(lp);

            if(getItemClickListener()!=null){
                holder.tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getItemClickListener().onItemClick(holder.tv,position);
                    }
                });
                holder.tv.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        int pos = holder.getLayoutPosition();//更新后的pos
                        getItemClickListener().onItemLongClick(holder.tv,pos);
                        removeData(pos);
                        return false;
                    }
                });
            }
            holder.tv.setText(data);
        }

        @Override
        public int getItemCount() {
            if(mDatas == null || mDatas.size() == 0) {
                return 0;
            }
            return mDatas.size();
        }
        public void addData(int position)
        {
            mDatas.add(position, "Insert One");
            mHeights.add( (int) (100 + Math.random() * 300));
            notifyItemInserted(position);
        }

        public void removeData(int position)
        {
            mDatas.remove(position);
            notifyItemRemoved(position);
        }

    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_rv_item);
        }

    }
}
