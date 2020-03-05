package com.jacob.serialize.serialization.array.arraytypes;

import com.jacob.serialize.DataType;
import com.jacob.serialize.serialization.array.Array;

public class ArrayChar extends Array {
	
	
	public ArrayChar(String arrayName, char[] arrayValue) {

		setArrayName(arrayName);
		arrayDataType = DataType.CHAR;
		arrayElementCount = arrayValue.length;
		charData = arrayValue;
		updateSize();
	}
}
