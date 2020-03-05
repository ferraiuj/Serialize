package com.jacob.serialize.serialization.array.arraytypes;

import com.jacob.serialize.DataType;
import com.jacob.serialize.serialization.array.Array;

public class ArrayInt extends Array {

	public ArrayInt(String arrayName, int[] arrayValue) {

		setArrayName(arrayName);
		arrayDataType = DataType.INT;
		arrayElementCount = arrayValue.length;
		intData = arrayValue;
		updateSize();
	}
}
