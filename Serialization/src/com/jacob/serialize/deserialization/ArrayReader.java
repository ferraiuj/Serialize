package com.jacob.serialize.deserialization;
import static com.jacob.serialize.deserialization.SerializationReader.readBoolean;
import static com.jacob.serialize.deserialization.SerializationReader.readChar;
import static com.jacob.serialize.deserialization.SerializationReader.readDouble;
import static com.jacob.serialize.deserialization.SerializationReader.readFloat;
import static com.jacob.serialize.deserialization.SerializationReader.readInt;
import static com.jacob.serialize.deserialization.SerializationReader.readLong;
import static com.jacob.serialize.deserialization.SerializationReader.readShort;

import com.jacob.serialize.DataType;

public class ArrayReader {

	public static void readShortArray(byte[] src, int pointer, short[] shortArray) {
		//return (short) (((src[pointer] & 0xff) << 8) | ((src[pointer + 1] & 0xff)));
		
		for(int i = 0; i < shortArray.length; i++) {
			shortArray[i] = readShort(src, pointer);
			pointer += DataType.getDataTypeSize(DataType.SHORT);
		}	
		//return (short) (((src[pointer]) << 8) | ((src[pointer + 1])));
	}

	public static void readCharArray(byte[] src, int pointer, char[] charArray) {
		for(int i = 0; i < charArray.length; i++) {
			charArray[i] = readChar(src, pointer);
			pointer +=DataType.getDataTypeSize(DataType.CHAR);
		}	
	}

	public static void readIntArray(byte[] src, int pointer, int[] intArray) {
		//return ByteBuffer.wrap(src, pointer, 4).getInt();
		for(int i = 0; i < intArray.length; i++) {
			intArray[i] = readInt(src, pointer);
			pointer +=DataType.getDataTypeSize(DataType.INT);
		}		}

	public static void readLongArray(byte[] src, int pointer, long[] longArray) {
		for(int i = 0; i < longArray.length; i++) {
			longArray[i] = readLong(src, pointer);
			pointer +=DataType.getDataTypeSize(DataType.LONG);
		}	}

	public static void readFloatArray(byte[] src, int pointer, float[] floatArray) {
		for(int i = 0; i < floatArray.length; i++) {
			floatArray[i] = readFloat(src, pointer);
			pointer += DataType.getDataTypeSize(DataType.FLOAT);
		}	
	}

	public static void readDoubleArray(byte[] src, int pointer, double[] doubleArray) {
		for(int i = 0; i < doubleArray.length; i++) {
			doubleArray[i] = readDouble(src, pointer);
			pointer +=DataType.getDataTypeSize(DataType.DOUBLE);
		}	
	}

	public static void readBooleanArray(byte[] src, int pointer, boolean[] booleanArray) {
		for(int i = 0; i < booleanArray.length; i++) {
			booleanArray[i] = readBoolean(src, pointer);
			pointer +=DataType.getDataTypeSize(DataType.BOOLEAN);
		}	
	}
	/*
	 * public static String readStringArray(byte[] src, int pointer, int length
	 * ) { return new String(src, pointer, length); }
	 */
}
