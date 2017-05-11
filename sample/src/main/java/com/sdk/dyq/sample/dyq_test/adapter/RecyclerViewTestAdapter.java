package com.sdk.dyq.sample.dyq_test.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sdk.dyq.multitypemodel.R;
import com.sdk.dyq.sample.dyq_test.Bean.MessageBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuanqiang on 2017/5/9.
 */

public class RecyclerViewTestAdapter extends RecyclerView.Adapter<RecyclerViewTestAdapter.ViewHolder>{

    private static final int ME = 11;//自己
    private static final int OTHER = 12;//他人

    private List<MessageBean> beanList;

    public RecyclerViewTestAdapter() {
        beanList = null;
    }

    public void setBeanList(List<MessageBean> msgList){
        if((beanList!=null)&& beanList!=msgList){
            beanList.clear();
        }
        beanList = msgList;
        notifyDataSetChanged();
    }
    private List<MessageBean> getBeanList(){
        if(beanList == null) beanList = new ArrayList<>();
        return beanList;
    }
    @Override
    public int getItemViewType(int position) {
        int viewType;
        MessageBean bean = getBeanList().get(position);
        if(bean.getId() == 0){
            viewType = ME;
        }else {
            viewType = OTHER;
        }
        return viewType;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        ViewGroup view;
        switch (viewType){
            case ME:
                view = (ViewGroup) mInflater.inflate(R.layout.listview_message_mine_item,parent,false);
                //不能写成:ViewGroup member = mInflater.inflate(R.layout.listview_message_item,null);
                return new MineViewHolder(view);
            case OTHER:
                view = (ViewGroup) mInflater.inflate(R.layout.listview_message_item,parent,false);
                return new MemberViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MessageBean bean = getBeanList().get(position);
        switch (holder.getItemViewType()){
            case ME:
                ((MineViewHolder)holder).tv_member_name.setText("我");
                ((MineViewHolder)holder).tv_message.setText(bean.getMsgContent());
                break;
            case OTHER:
                ((MemberViewHolder)holder).tv_member_name.setText(bean.getName());
                ((MemberViewHolder)holder).tv_message.setText(bean.getMsgContent());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return beanList.size();
    }


    /**
     * 基类
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class MemberViewHolder extends ViewHolder{
        public ImageView img_member_avatar;
        public TextView tv_member_name;
        public TextView tv_message;
        public TextView tv_message_time;
        public MemberViewHolder(View itemView) {
            super(itemView);
            img_member_avatar = (ImageView) itemView.findViewById(R.id.img_member_avatar);
            tv_member_name = (TextView) itemView.findViewById(R.id.tv_member_name);
            tv_message = (TextView) itemView.findViewById(R.id.tv_message);
            tv_message_time = (TextView) itemView.findViewById(R.id.tv_message_time);
        }
    }
    public class MineViewHolder extends ViewHolder {
        public ImageView img_member_avatar;
        public TextView tv_member_name;
        public TextView tv_message;
        public TextView tv_message_time;

        public MineViewHolder(View itemView) {
            super(itemView);
            img_member_avatar = (ImageView) itemView.findViewById(R.id.img_member_avatar);
            tv_member_name = (TextView) itemView.findViewById(R.id.tv_member_name);
            tv_message = (TextView) itemView.findViewById(R.id.tv_message);
            tv_message_time = (TextView) itemView.findViewById(R.id.tv_message_time);
        }
    }

}
