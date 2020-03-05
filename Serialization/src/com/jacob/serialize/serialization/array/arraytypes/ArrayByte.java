package com.jacob.serialize.serialization.array.arraytypes;

import com.jacob.serialize.DataType;
import com.jacob.serialize.serialization.array.Array;

public class ArrayByte extends Array {

	
	public ArrayByte(String arrayName, byte[] arrayValue) {
		setArrayName(arrayName);
		arrayDataType = DataType.BYTE;
		arrayElementCount = arrayValue.length;
		byteData = arrayValue;
		updateSize();
	}
}
