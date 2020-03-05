package com.jacob.serialize.serialization.array.arraytypes;

import com.jacob.serialize.DataType;
import com.jacob.serialize.serialization.array.Array;

public class ArrayShort extends Array {
	
	public ArrayShort(String arrayName, short[] arrayValue) {

		setArrayName(arrayName);
		arrayDataType = DataType.SHORT;
		arrayElementCount = arrayValue.length;
		shortData = arrayValue;
		updateSize();
	}
}
