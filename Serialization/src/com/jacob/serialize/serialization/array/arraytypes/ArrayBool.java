package com.jacob.serialize.serialization.array.arraytypes;

import com.jacob.serialize.DataType;
import com.jacob.serialize.serialization.array.Array;

public class ArrayBool extends Array {
	
	public ArrayBool(String arrayName, boolean[] arrayValue) {

		setArrayName(arrayName);
		arrayDataType = DataType.BOOLEAN;
		arrayElementCount = arrayValue.length;
		boolData = arrayValue;
		updateSize();
	}
	
}
