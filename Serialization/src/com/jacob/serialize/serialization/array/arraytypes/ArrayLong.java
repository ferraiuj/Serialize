package com.jacob.serialize.serialization.array.arraytypes;

import com.jacob.serialize.DataType;
import com.jacob.serialize.serialization.array.Array;

public class ArrayLong extends Array {
	
	public ArrayLong(String arrayName, long[] arrayValue) {

		setArrayName(arrayName);
		arrayDataType = DataType.LONG;
		arrayElementCount = arrayValue.length;
		longData = arrayValue;
		updateSize();
	}
}
