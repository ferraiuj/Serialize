package com.jacob.serialize.deserialization;

public class SerializationReader {

	public static byte readByte(byte[] src, int pointer) {
		return src[pointer];
	}
	public static void readByteArray(byte[] src, int pointer, byte[] byteArray) {
		for(int i = 0; i < byteArray.length; i++) {
			byteArray[i] = src[pointer + i];
		}	
	}
	public static short readShort(byte[] src, int pointer) {
		return (short) (((src[pointer] & 0xff) << 8) | ((src[pointer + 1] & 0xff)));
		//return (short) (((src[pointer]) << 8) | ((src[pointer + 1])));
	}

	public static char readChar(byte[] src, int pointer) {
		return (char) (((src[pointer] & 0xff) << 8) | ((src[pointer + 1] & 0xff)));
	}

	public static int readInt(byte[] src, int pointer) {
		//return ByteBuffer.wrap(src, pointer, 4).getInt();
		return (int) (((src[pointer] & 0xff) << 24) | ((src[pointer + 1] & 0xff) << 16) | ((src[pointer + 2] & 0xff) << 8) | (src[pointer + 3] & 0xff));
	}

	public static long readLong(byte[] src, int pointer) {
		return (long) ((((src[pointer] & 0xffL)) << 56) | ((src[pointer+ 1] & 0xffL) << 48) | ((src[pointer+ 2] & 0xffL) << 40) | ((src[pointer + 3] & 0xffL) << 32) 
				| ((src[pointer + 4] & 0xffL) << 24) | ((src[pointer + 5] & 0xffL) << 16) | ((src[pointer + 6] & 0xffL) << 8) | ((src[pointer] & 0xffL)));
	}

	public static float readFloat(byte[] src, int pointer) {
		return Float.intBitsToFloat(readInt(src, pointer));
	}

	public static double readDouble(byte[] src, int pointer) {
		return Double.longBitsToDouble(readLong(src, pointer));
	}

	public static boolean readBoolean(byte[] src, int pointer) {
		assert(src[pointer] == 0 || src[pointer] == 1);
		return src[pointer] == 1;
	}
	public static String readString(byte[] src, int pointer, int length ) {
		return new String(src, pointer, length);
	}
}
