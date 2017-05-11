package com.sdk.dyq.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.sdk.dyq.multitypemodel.R;
import com.sdk.dyq.sample.bilibili.BilibiliActivity;
import com.sdk.dyq.sample.communicate_with_binder.SimpleActivity;
import com.sdk.dyq.sample.dyq_test.RecyclerGridActivity;
import com.sdk.dyq.sample.dyq_test.RecyclerViewActivity;
import com.sdk.dyq.sample.multi_select.MultiSelectActivity;
import com.sdk.dyq.sample.normal.NormalActivity;
import com.sdk.dyq.sample.one2many.OneDataToManyActivity;
import com.sdk.dyq.sample.payload.TestPayloadActivity;
import com.sdk.dyq.sample.weibo.WeiboActivity;


/**
 * @author drakeet
 */
public class MenuBaseActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent();
        switch (item.getItemId()) {
            case R.id.RecyclerGridActivity:
                intent.setClass(this, RecyclerGridActivity.class);
                break;
            case R.id.RecyclerViewTestActivity:
                intent.setClass(this, RecyclerViewActivity.class);
                break;
            case R.id.NormalActivity:
                intent.setClass(this, NormalActivity.class);
                break;
            case R.id.MultiSelectActivity:
                intent.setClass(this, MultiSelectActivity.class);
                break;
            case R.id.communicate_with_binder:
                intent.setClass(this, SimpleActivity.class);
                break;
            case R.id.BilibiliActivity:
                intent.setClass(this, BilibiliActivity.class);
                break;
            case R.id.WeiboActivity:
                intent.setClass(this, WeiboActivity.class);
                break;
            case R.id.OneDataToManyActivity:
                intent.setClass(this, OneDataToManyActivity.class);
                break;
            case R.id.TestPayloadActivity:
                intent.setClass(this, TestPayloadActivity.class);
                break;
            default:
                return false;
        }
        startActivity(intent);
        this.finish();
        return true;
    }
}
