package com.jacob.serialize.deserialization.dobject;

import static com.jacob.serialize.deserialization.SerializationReader.readInt;
import static com.jacob.serialize.deserialization.SerializationReader.readShort;
import static com.jacob.serialize.deserialization.SerializationReader.readString;

import java.util.ArrayList;
import java.util.List;

import com.jacob.serialize.ContainerType;
import com.jacob.serialize.deserialization.darray.DArray;
import com.jacob.serialize.deserialization.darray.DString;
import com.jacob.serialize.deserialization.dfield.DField;

public class DObj {

	public static final byte OBJ_CONTAINER_TYPE = ContainerType.OBJECT;
	public short objNameLength;
	public byte[] objName;
	protected int size = 1 + 2 + 4 + 2 + 2 + 2;
	protected short fieldCount;
	protected List<DField> fields = new ArrayList<DField>();
	protected short stringCount;
	protected List<DString> strings = new ArrayList<DString>();
	protected short arrayCount;
	protected List<DArray> arrays = new ArrayList<DArray>();

	private static final int sizeOffset = 1 + 2 + 4;

	private DObj() {

	}

	public String getObjName() {
		return new String(objName, 0, objNameLength);
	}

	public int getObjSize() {

		return size;
	}

	public static DObj Deserialize(byte[] objByteArray, int objPointer) {

		byte containerType = objByteArray[objPointer++];
		assert (containerType == OBJ_CONTAINER_TYPE);

		DObj result = new DObj();

		result.objNameLength = readShort(objByteArray, objPointer);
		objPointer += 2;
		result.objName = readString(objByteArray, objPointer, result.objNameLength).getBytes();
		objPointer += result.objNameLength;

		result.size = readInt(objByteArray, objPointer);
		objPointer += 4;

		// early-out: objPointer += result.size - sizeOffset -
		// result.objNameLength;

		result.fieldCount = readShort(objByteArray, objPointer);
		// System.out.println(result.fieldCount);
		objPointer += 2;

		for (int i = 0; i < result.fieldCount; i++) {
			DField field = DField.Deserialize(objByteArray, objPointer);
			result.fields.add(field);
			objPointer += field.getFieldSize();
		}
		result.stringCount = readShort(objByteArray, objPointer);
		objPointer += 2;

		for (int i = 0; i < result.stringCount; i++) {

			DString string = DString.Deserialize(objByteArray, objPointer);
			result.strings.add(string);
			objPointer += string.getStringSize();

		}
		System.out.println("SDpointer: " + objPointer);

		result.arrayCount = readShort(objByteArray, objPointer);
		// System.out.println(result.arrayCount);
		objPointer += 2;

		for (int i = 0; i < result.arrayCount; i++) {
			DArray array = DArray.Deserialize(objByteArray, objPointer);
			result.arrays.add(array);
			objPointer += array.getArraySize();
		}

		return result;
	}
}
