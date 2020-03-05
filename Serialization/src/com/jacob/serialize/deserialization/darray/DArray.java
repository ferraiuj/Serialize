package com.jacob.serialize.deserialization.darray;

import static com.jacob.serialize.deserialization.SerializationReader.readByteArray;
import static com.jacob.serialize.deserialization.SerializationReader.readInt;
import static com.jacob.serialize.deserialization.SerializationReader.readShort;
import static com.jacob.serialize.deserialization.SerializationReader.readString;
import static com.jacob.serialize.deserialization.ArrayReader.*;

import com.jacob.serialize.ContainerType;
import com.jacob.serialize.DataType;

public class DArray {

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

	private DArray() {

	}

	public int getArraySize() {

		return size + getDataSize();
	}

	public int getDataSize() {
		switch (arrayDataType) {
		case DataType.BYTE:
			return byteData.length * DataType.getDataTypeSize(DataType.BYTE);

		case DataType.BOOLEAN:
			return boolData.length * DataType.getDataTypeSize(DataType.BOOLEAN);

		case DataType.CHAR:
			return charData.length * DataType.getDataTypeSize(DataType.CHAR);

		case DataType.DOUBLE:
			return doubleData.length * DataType.getDataTypeSize(DataType.DOUBLE);

		case DataType.FLOAT:
			return floatData.length * DataType.getDataTypeSize(DataType.FLOAT);

		case DataType.INT:
			return intData.length * DataType.getDataTypeSize(DataType.INT);

		case DataType.LONG:
			return longData.length * DataType.getDataTypeSize(DataType.LONG);

		case DataType.SHORT:
			return shortData.length * DataType.getDataTypeSize(DataType.SHORT);

		}
		return 0;
	}

	public static DArray Deserialize(byte[] dArrayByteArray, int dArrayPointer) {

		byte containerType = dArrayByteArray[dArrayPointer++];
		assert (containerType == ARRAY_CONTAINER_TYPE);

		DArray result = new DArray();

		result.arrayNameLength = readShort(dArrayByteArray, dArrayPointer);
		dArrayPointer += 2;
		result.arrayName = readString(dArrayByteArray, dArrayPointer, result.arrayNameLength).getBytes();
		dArrayPointer += result.arrayNameLength;

		result.size = readInt(dArrayByteArray, dArrayPointer);
		dArrayPointer += 4;

		result.arrayDataType = dArrayByteArray[dArrayPointer++];

		result.arrayElementCount = readInt(dArrayByteArray, dArrayPointer);
		dArrayPointer += 4;

		switch (result.arrayDataType) {
		case DataType.BYTE:
			result.byteData = new byte[result.arrayElementCount];
			readByteArray(dArrayByteArray, dArrayPointer, result.byteData);
			break;
		case DataType.BOOLEAN:
			result.boolData = new boolean[result.arrayElementCount];
			readBooleanArray(dArrayByteArray, dArrayPointer, result.boolData);
			break;
		case DataType.CHAR:
			result.charData = new char[result.arrayElementCount];
			readCharArray(dArrayByteArray, dArrayPointer, result.charData);
			break;
		case DataType.DOUBLE:
			result.doubleData = new double[result.arrayElementCount];
			readDoubleArray(dArrayByteArray, dArrayPointer, result.doubleData);
			break;
		case DataType.FLOAT:
			result.floatData = new float[result.arrayElementCount];
			readFloatArray(dArrayByteArray, dArrayPointer, result.floatData);
			break;
		case DataType.INT:
			result.intData = new int[result.arrayElementCount];
			readIntArray(dArrayByteArray, dArrayPointer, result.intData);
			break;
		case DataType.LONG:
			result.longData = new long[result.arrayElementCount];
			readLongArray(dArrayByteArray, dArrayPointer, result.longData);
			break;
		case DataType.SHORT:
			result.shortData = new short[result.arrayElementCount];
			readShortArray(dArrayByteArray, dArrayPointer, result.shortData);
			break;

		}
		dArrayPointer += result.arrayElementCount * DataType.getDataTypeSize(result.arrayDataType);
		return result;
	}
}
