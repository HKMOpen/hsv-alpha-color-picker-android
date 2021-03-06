/*
 * Copyright (C) 2015 Martin Stone
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

package com.rarepebble.colorpicker;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.FrameLayout;

public class ColorPickerView extends FrameLayout {

	private ObservableColor observableColor = new ObservableColor(0);
	private final SwatchView swatchView;

	public ColorPickerView(Context context) {
		this(context, null);
	}

	public ColorPickerView(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.picker, this);

		swatchView = (SwatchView)findViewById(R.id.swatchView);
		swatchView.observeColor(observableColor);

		HueSatView hueSatView = (HueSatView)findViewById(R.id.hueSatView);
		hueSatView.observeColor(observableColor);

		ValueView valueView = (ValueView)findViewById(R.id.valueView);
		valueView.observeColor(observableColor);

		AlphaView alphaView = (AlphaView)findViewById(R.id.alphaView);
		alphaView.observeColor(observableColor);

		EditText hexEdit = (EditText)findViewById(R.id.hexEdit);
		HexEdit.setUpListeners(hexEdit, observableColor);

		// We get all our state saved and restored for free,
		// thanks to the EditText and its listeners!
	}

	public int getColor() {
		return observableColor.getColor();
	}

	public void setColor(int color) {
		swatchView.setOldColor(color);
		observableColor.updateColor(color, null);
	}
}
