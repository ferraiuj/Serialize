package com.jacob.serialize.serialization.array.arraytypes;

import com.jacob.serialize.DataType;
import com.jacob.serialize.serialization.array.Array;
public class ArrayDouble extends Array {
	
	
	public ArrayDouble(String arrayName, double[] arrayValue) {

		setArrayName(arrayName);
		arrayDataType = DataType.DOUBLE;
		arrayElementCount = arrayValue.length;
		doubleData = arrayValue;
		updateSize();
	}
}
