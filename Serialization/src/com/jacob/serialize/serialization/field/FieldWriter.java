package com.jacob.serialize.serialization.field;


import com.jacob.serialize.DataType;

public class FieldWriter {

	public static final byte[] HEADER = "JF".getBytes();
	public static final short VERSION = 0x0100; // big endian

	public static int writeByteArray(byte[] byteArray, int pointer, byte[] src) {
		assert(byteArray.length > pointer + src.length );
		for (int i = 0; i < src.length; i++) {
			byteArray[pointer++] = src[i];
		}
		return pointer;
	}
	
	public static int writeBytes(byte[] byteArray, int pointer, byte byteValueWrite) {
		assert(byteArray.length > pointer + DataType.getDataTypeSize(DataType.BYTE) );
		byteArray[pointer++] = byteValueWrite;
		return pointer;
	}

	public static int writeBytesShort(byte[] byteArray, int pointer, short byteValueWrite) {
		assert(byteArray.length > pointer + DataType.getDataTypeSize(DataType.SHORT) );
		
		byteArray[pointer++] = (byte) ((byteValueWrite >> 8) & 0xff);
		byteArray[pointer++] = (byte) ((byteValueWrite >> 0) & 0xff);
		//System.out.println(byteValueWrite);
		return pointer;
	}

	public static int writeBytesChar(byte[] byteArray, int pointer, char byteValueWrite) {
		assert(byteArray.length > pointer + DataType.getDataTypeSize(DataType.CHAR) );
		byteArray[pointer++] = (byte) ((byteValueWrite >> 8) & 0xff);
		byteArray[pointer++] = (byte) ((byteValueWrite >> 0) & 0xff);
		return pointer;
	}

	public static int writeBytesInt(byte[] byteArray, int pointer, int byteValueWrite) {
		assert(byteArray.length > pointer + DataType.getDataTypeSize(DataType.INT) );
		byteArray[pointer++] = (byte) ((byteValueWrite >> 24) & 0xff);
		byteArray[pointer++] = (byte) ((byteValueWrite >> 16) & 0xff);
		byteArray[pointer++] = (byte) ((byteValueWrite >> 8) & 0xff);
		byteArray[pointer++] = (byte) ((byteValueWrite >> 0) & 0xff);
		return pointer;
	}

	public static int writeBytesLong(byte[] byteArray, int pointer, long byteValueWrite) {
		assert(byteArray.length > pointer + DataType.getDataTypeSize(DataType.LONG) );
		byteArray[pointer++] = (byte) ((byteValueWrite >> 56) & 0xff);
		byteArray[pointer++] = (byte) ((byteValueWrite >> 48) & 0xff);
		byteArray[pointer++] = (byte) ((byteValueWrite >> 40) & 0xff);
		byteArray[pointer++] = (byte) ((byteValueWrite >> 32) & 0xff);
		byteArray[pointer++] = (byte) ((byteValueWrite >> 24) & 0xff);
		byteArray[pointer++] = (byte) ((byteValueWrite >> 16) & 0xff);
		byteArray[pointer++] = (byte) ((byteValueWrite >> 8) & 0xff);
		byteArray[pointer++] = (byte) ((byteValueWrite >> 0) & 0xff);
		return pointer;
	}

	public static int writeBytesFloat(byte[] byteArray, int pointer, float byteValueWrite) {
		assert(byteArray.length > pointer + DataType.getDataTypeSize(DataType.FLOAT) );
		int byteConvertData = Float.floatToIntBits(byteValueWrite);
		return writeBytesFloat(byteArray, pointer, byteConvertData);
	}

	public static int writeBytesDouble(byte[] byteArray, int pointer, double byteValueWrite) {
		assert(byteArray.length > pointer + DataType.getDataTypeSize(DataType.DOUBLE) );
		long byteConvertData = Double.doubleToLongBits(byteValueWrite);
		return writeBytesFloat(byteArray, pointer, byteConvertData);
	}

	public static int writeBytesBool(byte[] byteArray, int pointer, boolean byteValueBool) {
		assert(byteArray.length > pointer + DataType.getDataTypeSize(DataType.BOOLEAN) );
		byteArray[pointer++] = (byte) (byteValueBool ? 1 : 0);
		return pointer;
	}

	public static int writeBytesString(byte[] byteArray, int pointer, String byteValueString) {
		pointer = writeBytesShort(byteArray, pointer, (short) byteValueString.length());
		return writeByteArray(byteArray, pointer, byteValueString.getBytes());
	}
	
}
