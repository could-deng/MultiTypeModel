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

package com.sdk.dyq.sample.one2many;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.v7.widget.RecyclerView;

import com.sdk.dyq.library.ClassLinker;
import com.sdk.dyq.library.ItemViewBinder;
import com.sdk.dyq.library.MultiTypeAdapter;
import com.sdk.dyq.multitypemodel.R;
import com.sdk.dyq.sample.MenuBaseActivity;

import java.util.ArrayList;
import java.util.List;

import static com.sdk.dyq.library.MultiTypeAsserts.assertAllRegistered;
import static com.sdk.dyq.library.MultiTypeAsserts.assertHasTheSameAdapter;


public class OneDataToManyActivity extends MenuBaseActivity {

    @VisibleForTesting RecyclerView recyclerView;
    @VisibleForTesting
    MultiTypeAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        recyclerView = (RecyclerView) findViewById(R.id.list);
        adapter = new MultiTypeAdapter();

        /*
        adapter.register(Data.class).to(
            new DataType1ViewBinder(),
            new DataType2ViewBinder()
        ).withLinker(new Linker<Data>() {
            @Override
            public int index(@NonNull Data data) {
                if (data.type == Data.TYPE_2) { return 1; } else return 0;
            }
        });
        */

        adapter.register(Data.class).to(
            new DataType1ViewBinder(),
            new DataType2ViewBinder()
        ).withClassLinker(new ClassLinker<Data>() {
            @NonNull @Override
            public Class<? extends ItemViewBinder<Data, ?>> index(@NonNull Data data) {
                if (data.type == Data.TYPE_2) {
                    return DataType2ViewBinder.class;
                } else {
                    return DataType1ViewBinder.class;
                }
            }
        });

        List<Data> dataList = getDataFromService();
        adapter.setItems(dataList);
        adapter.notifyDataSetChanged();
        assertAllRegistered(adapter, dataList);
        recyclerView.setAdapter(adapter);
        assertHasTheSameAdapter(recyclerView, adapter);
    }


    @VisibleForTesting
    List<Data> getDataFromService() {
        List<Data> list = new ArrayList<>();
        for (int i = 0; i < 30; i = i + 2) {
            list.add(new Data("title: " + i, Data.TYPE_1));
            list.add(new Data("title: " + i + 1, Data.TYPE_2));
        }
        return list;
    }
}
