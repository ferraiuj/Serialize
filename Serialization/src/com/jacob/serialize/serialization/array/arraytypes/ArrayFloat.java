package com.jacob.serialize.serialization.array.arraytypes;

import com.jacob.serialize.DataType;
import com.jacob.serialize.serialization.array.Array;

public class ArrayFloat extends Array {
	
	

	public ArrayFloat(String arrayName, float[] arrayValue) {

		setArrayName(arrayName);
		arrayDataType = DataType.FLOAT;
		arrayElementCount = arrayValue.length;
		floatData = arrayValue;
		updateSize();
	}
}
