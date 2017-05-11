/*
 * Copyright 2016 drakeet. https://github.com/drakeet
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sdk.dyq.sample.payload;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;


import com.sdk.dyq.library.Items;
import com.sdk.dyq.library.MultiTypeAdapter;
import com.sdk.dyq.multitypemodel.R;
import com.sdk.dyq.sample.MenuBaseActivity;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

public class TestPayloadActivity extends MenuBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, VERTICAL));
        MultiTypeAdapter adapter = new MultiTypeAdapter();
        recyclerView.setAdapter(adapter);

        adapter.register(HeavyItem.class, new HeavyItemViewBinder());

        Items items = new Items();
        for (int i = 0; i < 30; i++) {
            items.add(new HeavyItem("1000" + i));
        }
        adapter.setItems(items);
        adapter.notifyDataSetChanged();

        Toast.makeText(this, "Try to click or long click items", Toast.LENGTH_SHORT).show();
    }
}
