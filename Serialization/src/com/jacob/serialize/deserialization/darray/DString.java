package com.jacob.serialize.deserialization.darray;

import static com.jacob.serialize.deserialization.ArrayReader.readCharArray;
import static com.jacob.serialize.deserialization.SerializationReader.readInt;
import static com.jacob.serialize.deserialization.SerializationReader.readShort;
import static com.jacob.serialize.deserialization.SerializationReader.readString;

import com.jacob.serialize.ContainerType;
import com.jacob.serialize.DataType;

public class DString {
	
	public static final byte STRING_CONTAINER_TYPE = ContainerType.STRING;
	public short stringNameLength;
	public byte[] stringName;
	public int count;
	public int size = 1 + 2 + 4 + 4;
	public char[] characters;
	private DString() {
		
	}
	public String getStringName() {
		return new String(characters);
	}
	public int getStringSize() {

		//System.out.println("size: " + size);
		return size;
	}
	public static DString Deserialize(byte[] dStringArray, int dStringPointer) {
		
		byte containerType = dStringArray[dStringPointer++];
		assert (containerType == STRING_CONTAINER_TYPE);

		DString result = new DString();

		result.stringNameLength = readShort(dStringArray, dStringPointer);
		dStringPointer += 2;
		result.stringName = readString(dStringArray, dStringPointer, result.stringNameLength).getBytes();
		dStringPointer += result.stringNameLength;

		result.size = readInt(dStringArray, dStringPointer);
		dStringPointer += 4;
				
		result.count = readInt(dStringArray, dStringPointer);
		dStringPointer += 4;
		
		result.characters = new char[result.count];
		readCharArray(dStringArray, dStringPointer, result.characters);
		
		

		dStringPointer += DataType.getDataTypeSize(DataType.CHAR);
		
		return result;
	}
}
