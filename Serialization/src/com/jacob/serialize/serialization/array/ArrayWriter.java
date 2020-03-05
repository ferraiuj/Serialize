package com.jacob.serialize.serialization.array;


import static com.jacob.serialize.serialization.field.FieldWriter.writeBytesBool;
import static com.jacob.serialize.serialization.field.FieldWriter.writeBytesChar;
import static com.jacob.serialize.serialization.field.FieldWriter.writeBytesDouble;
import static com.jacob.serialize.serialization.field.FieldWriter.writeBytesFloat;
import static com.jacob.serialize.serialization.field.FieldWriter.writeBytesInt;
import static com.jacob.serialize.serialization.field.FieldWriter.writeBytesLong;
import static com.jacob.serialize.serialization.field.FieldWriter.writeBytesShort;

public class ArrayWriter {
	
	public static int writeArrayByte(byte[] byteArray, int pointer, byte[] src) {
		assert(byteArray.length > pointer + src.length );
		for (int i = 0; i < src.length; i++) {
			byteArray[pointer++] = src[i];
		}
		return pointer;
	}
	public static int writeArrayShort(byte[] byteArray, int pointer, short[] src) {
		assert(byteArray.length > pointer + src.length );
		for (int i = 0; i < src.length; i++) {
			pointer = writeBytesShort(byteArray, pointer, src[i]);
		}
		return pointer;
	}
	public static int writeArrayChar(byte[] byteArray, int pointer, char[] src) {
		assert(byteArray.length > pointer + src.length );
		for (int i = 0; i < src.length; i++) {
			pointer = writeBytesChar(byteArray, pointer, src[i]);
		}
		return pointer;
	}
	public static int writeArrayFloat(byte[] byteArray, int pointer, float[] src) {
		assert(byteArray.length > pointer + src.length );
		for (int i = 0; i < src.length; i++) {
			pointer = writeBytesFloat(byteArray, pointer, src[i]);
		}
		return pointer;
	}
	public static int writeArrayLong(byte[] byteArray, int pointer, long[] src) {
		assert(byteArray.length > pointer + src.length );
		for (int i = 0; i < src.length; i++) {
			pointer = writeBytesLong(byteArray, pointer, src[i]);
		}
		return pointer;
	}
	public static int writeArrayInt(byte[] byteArray, int pointer, int[] src) {
		assert(byteArray.length > pointer + src.length );
		for (int i = 0; i < src.length; i++) {
			pointer = writeBytesInt(byteArray, pointer, src[i]);
		}
		return pointer;
	}
	public static int writeArrayDouble(byte[] byteArray, int pointer, double[] src) {
		assert(byteArray.length > pointer + src.length );
		for (int i = 0; i < src.length; i++) {
			pointer = writeBytesDouble(byteArray, pointer, src[i]);
		}
		return pointer;
	}
	public static int writeArrayBool(byte[] byteArray, int pointer, boolean[] src) {
		assert(byteArray.length > pointer + src.length );
		for (int i = 0; i < src.length; i++) {
			pointer = writeBytesBool(byteArray, pointer, src[i]);
		}
		return pointer;
	}
	
}
