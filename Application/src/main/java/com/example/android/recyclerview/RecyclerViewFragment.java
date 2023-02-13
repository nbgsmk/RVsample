/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.recyclerview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.model.DugmeKlotKlasa;
import com.example.android.recyclerview.databinding.RecyclerViewFragBinding;

/**
 * Demonstrates the use of {@link RecyclerView} with a {@link LinearLayoutManager} and a
 * {@link GridLayoutManager}.
 */
public class RecyclerViewFragment extends Fragment {

	private static final String TAG = "RecyclerViewFragment";
	private static final String KEY_LAYOUT_MANAGER = "layoutManager";
	private static final int SPAN_COUNT = 2;
	private static final int DATASET_COUNT = 60;
	protected LayoutManagerType layoutManagerType;
	protected CustomAdapter customAdapter;
	protected RecyclerView.LayoutManager layoutManager;
	protected String[] dataset;
	RecyclerViewFragBinding binding;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initDataset();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		binding = RecyclerViewFragBinding.inflate(inflater, container, false);
//		View rootView = binding.getRoot();
		binding.getRoot().setTag(TAG);

		// LinearLayoutManager is used here, this will layout the elements in a similar fashion
		// to the way ListView would layout elements. The RecyclerView.LayoutManager defines how
		// elements are laid out.
		layoutManager = new LinearLayoutManager(getActivity());

		layoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;

		if (savedInstanceState != null) {
			// Restore saved layout manager type.
			layoutManagerType = (LayoutManagerType) savedInstanceState.getSerializable(KEY_LAYOUT_MANAGER);
		}
		setRecyclerViewLayoutManager(layoutManagerType);

        AdapterDugme adpDugme = new AdapterDugme(initDatasetDugme());
		binding.recyclerView.setAdapter(adpDugme);
		// END_INCLUDE(initializeRecyclerView)

		binding.linearLayoutRb.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				setRecyclerViewLayoutManager(LayoutManagerType.LINEAR_LAYOUT_MANAGER);
			}
		});

		binding.gridLayoutRb.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				setRecyclerViewLayoutManager(LayoutManagerType.GRID_LAYOUT_MANAGER);
			}
		});

		return binding.getRoot();
	}

	/**
	 * Set RecyclerView's LayoutManager to the one given.
	 *
	 * @param layoutManagerType Type of layout manager to switch to.
	 */
	public void setRecyclerViewLayoutManager(LayoutManagerType layoutManagerType) {
		int scrollPosition = 0;

		// If a layout manager has already been set, get current scroll position.
		if (binding.recyclerView.getLayoutManager() != null) {
			scrollPosition = ((LinearLayoutManager) binding.recyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition();
		}

		switch (layoutManagerType) {
			case GRID_LAYOUT_MANAGER:
				layoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT);
				this.layoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER;
				break;
			case LINEAR_LAYOUT_MANAGER:
				layoutManager = new LinearLayoutManager(getActivity());
				this.layoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
				break;
			default:
				layoutManager = new LinearLayoutManager(getActivity());
				this.layoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
		}

		binding.recyclerView.setLayoutManager(layoutManager);
		binding.recyclerView.scrollToPosition(scrollPosition);
	}

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		// Save currently selected layout manager.
		savedInstanceState.putSerializable(KEY_LAYOUT_MANAGER, layoutManagerType);
		super.onSaveInstanceState(savedInstanceState);
	}

	/**
	 * Generates Strings for RecyclerView's adapter. This data would usually come
	 * from a local content provider or remote server.
	 */
	private void initDataset() {
		dataset = new String[DATASET_COUNT];
		for (int i = 0; i < DATASET_COUNT; i++) {
			dataset[i] = "This is element #" + i;
		}
	}

	private DugmeKlotKlasa[] initDatasetDugme() {
        DugmeKlotKlasa[] dsd = new DugmeKlotKlasa[90];
        for (int i = 0; i < dsd.length; i++) {
            dsd[i] = new DugmeKlotKlasa();
        }
        return dsd;
	}

	private enum LayoutManagerType {
		GRID_LAYOUT_MANAGER,
		LINEAR_LAYOUT_MANAGER
	}
}
