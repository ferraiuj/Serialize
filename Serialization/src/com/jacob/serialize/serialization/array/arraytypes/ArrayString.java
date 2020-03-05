package com.jacob.serialize.serialization.array.arraytypes;

import static com.jacob.serialize.serialization.array.ArrayWriter.writeArrayChar;
import static com.jacob.serialize.serialization.field.FieldWriter.writeByteArray;
import static com.jacob.serialize.serialization.field.FieldWriter.writeBytes;
import static com.jacob.serialize.serialization.field.FieldWriter.writeBytesInt;
import static com.jacob.serialize.serialization.field.FieldWriter.writeBytesShort;

import com.jacob.serialize.ContainerType;
import com.jacob.serialize.DataType;

public class ArrayString {

	public static final byte STRING_CONTAINER_TYPE = ContainerType.STRING;
	public short stringNameLength;
	public byte[] stringName;
	public int count;
	public int size = 1 + 2 + 4 + 4;
	public char[] characters;

	private ArrayString(){
		
	}
	public void setStringName(String stringNamePar) {
		assert (stringNamePar.length() < Short.MAX_VALUE);

		if (this.stringName != null) {
			size -= this.stringName.length;
		}
		stringNameLength = (short) stringNamePar.length();
		this.stringName = stringNamePar.getBytes();
		size += stringNameLength;
	}

	public int getStringBytes(byte[] stringByteArray, int stringPointer) {
		
		stringPointer = writeBytes(stringByteArray, stringPointer, STRING_CONTAINER_TYPE);
		stringPointer = writeBytesShort(stringByteArray, stringPointer, stringNameLength);
		stringPointer = writeByteArray(stringByteArray, stringPointer, stringName);
		stringPointer = writeBytesInt(stringByteArray, stringPointer, size);
		stringPointer = writeBytesInt(stringByteArray, stringPointer, count);
		stringPointer = writeArrayChar(stringByteArray, stringPointer, characters);
		//System.out.println("Spointer: " + stringPointer);
		return stringPointer;
	}

	public int getStringSize() {
System.out.println("size: " + size);
		return size;
		
	}

	protected void updateSize() {
		size += getDataSize();
	}

	public int getDataSize() {
		return characters.length * DataType.getDataTypeSize(DataType.CHAR);
	}
	public static ArrayString Create(String stringName, String stringData) {
		ArrayString string = new ArrayString();
		string.setStringName(stringName);
		string.count = stringData.length();
		string.characters = stringData.toCharArray();
		string.updateSize();
		return string;
	}
}
