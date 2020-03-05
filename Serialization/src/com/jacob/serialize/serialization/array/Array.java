package com.jacob.serialize.serialization.array;

import static com.jacob.serialize.serialization.array.ArrayWriter.writeArrayBool;
import static com.jacob.serialize.serialization.array.ArrayWriter.writeArrayByte;
import static com.jacob.serialize.serialization.array.ArrayWriter.writeArrayChar;
import static com.jacob.serialize.serialization.array.ArrayWriter.writeArrayDouble;
import static com.jacob.serialize.serialization.array.ArrayWriter.writeArrayFloat;
import static com.jacob.serialize.serialization.array.ArrayWriter.writeArrayInt;
import static com.jacob.serialize.serialization.array.ArrayWriter.writeArrayLong;
import static com.jacob.serialize.serialization.array.ArrayWriter.writeArrayShort;
import static com.jacob.serialize.serialization.field.FieldWriter.writeByteArray;
import static com.jacob.serialize.serialization.field.FieldWriter.writeBytes;
import static com.jacob.serialize.serialization.field.FieldWriter.writeBytesInt;
import static com.jacob.serialize.serialization.field.FieldWriter.writeBytesShort;

import com.jacob.serialize.ContainerType;
import com.jacob.serialize.DataType;


public class Array {

	public static final byte ARRAY_CONTAINER_TYPE = ContainerType.ARRAY;
	public short arrayNameLength;
	public byte[] arrayName;
	public int size = 1 + 2 + 4 + 1 + 4;
	public byte arrayDataType;
	public int arrayElementCount;
	public byte[] byteData;
	
	protected int[] intData;
	protected double[] doubleData;
	protected boolean[] boolData;
	protected short[] shortData;
	protected char[] charData;
	protected float[] floatData;
	protected long[] longData;
	

	public void setArrayName(String arrayNamePar) {
		assert (arrayNamePar.length() < Short.MAX_VALUE);

		if (this.arrayName != null) {
			size -= this.arrayName.length;
		}
		arrayNameLength = (short) arrayNamePar.length();
		this.arrayName = arrayNamePar.getBytes();
		size += arrayNameLength;
	}

	public int getArrayBytes(byte[] arrayByteArray, int arrayPointer) {

		arrayPointer = writeBytes(arrayByteArray, arrayPointer, ARRAY_CONTAINER_TYPE);
		arrayPointer = writeBytesShort(arrayByteArray, arrayPointer, arrayNameLength);
		arrayPointer = writeByteArray(arrayByteArray, arrayPointer, arrayName);
		arrayPointer = writeBytesInt(arrayByteArray, arrayPointer, size);
		arrayPointer = writeBytes(arrayByteArray, arrayPointer, arrayDataType);
		arrayPointer = writeBytesInt(arrayByteArray, arrayPointer, arrayElementCount);

		switch (arrayDataType) {
		case DataType.BYTE:
			arrayPointer = writeArrayByte(arrayByteArray, arrayPointer, byteData);
			break;
		case DataType.BOOLEAN:
			arrayPointer = writeArrayBool(arrayByteArray, arrayPointer, boolData);
			break;
		case DataType.CHAR:
			arrayPointer = writeArrayChar(arrayByteArray, arrayPointer, charData);
			break;
		case DataType.DOUBLE:
			arrayPointer = writeArrayDouble(arrayByteArray, arrayPointer, doubleData);
			break;
		case DataType.FLOAT:
			arrayPointer = writeArrayFloat(arrayByteArray, arrayPointer, floatData);
			break;
		case DataType.INT:
			arrayPointer = writeArrayInt(arrayByteArray, arrayPointer, intData);
			break;
		case DataType.LONG:
			arrayPointer = writeArrayLong(arrayByteArray, arrayPointer, longData);
			break;
		case DataType.SHORT:
			arrayPointer = writeArrayShort(arrayByteArray, arrayPointer, shortData);
			break;

		}

		return arrayPointer;
	}

	public int getArraySize() {
		
		return size; 
	}
	protected void updateSize() {
		size += getDataSize();
	}
	  public int getDataSize() {
			switch (arrayDataType) {
			case DataType.BYTE: return byteData.length * DataType.getDataTypeSize(DataType.BYTE);
			
			case DataType.BOOLEAN:return boolData.length * DataType.getDataTypeSize(DataType.BOOLEAN);
				
			case DataType.CHAR: return charData.length * DataType.getDataTypeSize(DataType.CHAR);
				
			case DataType.DOUBLE: return doubleData.length * DataType.getDataTypeSize(DataType.DOUBLE);
				
			case DataType.FLOAT: return floatData.length * DataType.getDataTypeSize(DataType.FLOAT);
				
			case DataType.INT: return intData.length * DataType.getDataTypeSize(DataType.INT);
				
			case DataType.LONG: return longData.length * DataType.getDataTypeSize(DataType.LONG);
				
			case DataType.SHORT: return shortData.length * DataType.getDataTypeSize(DataType.SHORT);
				

			}
			return 0;
	  }
	 
}
