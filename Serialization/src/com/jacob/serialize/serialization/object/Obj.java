package com.jacob.serialize.serialization.object;

import static com.jacob.serialize.serialization.field.FieldWriter.writeByteArray;
import static com.jacob.serialize.serialization.field.FieldWriter.writeBytes;
import static com.jacob.serialize.serialization.field.FieldWriter.writeBytesInt;
import static com.jacob.serialize.serialization.field.FieldWriter.writeBytesShort;

import java.util.ArrayList;
import java.util.List;

import com.jacob.serialize.ContainerType;
import com.jacob.serialize.serialization.array.Array;
import com.jacob.serialize.serialization.array.arraytypes.ArrayString;
import com.jacob.serialize.serialization.field.Field;

public class Obj {

	public static final byte OBJ_CONTAINER_TYPE = ContainerType.OBJECT;
	public short objNameLength;
	public byte[] objName;	
	private int size = 1 + 2 + 4 + 2 + 2 + 2;
	private short fieldCount;
	private List<Field> fields = new ArrayList<Field>();
	private short stringCount;
	private List<ArrayString> strings = new ArrayList<ArrayString>();
	private short arrayCount;
	private List<Array> arrays = new ArrayList<Array>();


	public Obj(String objName) {
		setObjName(objName);
	}

	public void setObjName(String objNamePar) {
		assert (objNamePar.length() < Short.MAX_VALUE);

		if (this.objName != null) {
			size -= this.objName.length;
		}
		objNameLength = (short) objNamePar.length();
		this.objName = objNamePar.getBytes();
		size += objNameLength;
	}

	public void addField(Field objField) {
		fields.add(objField);
		size += objField.getFieldSize();
		fieldCount = (short) fields.size();
	}
	public void addString(ArrayString objString) {
		strings.add(objString);
		size += objString.getStringSize();
		stringCount = (short) strings.size();
	}

	public void addArray(Array objArray) {
		arrays.add(objArray);
		size += objArray.getArraySize();
		arrayCount = (short) arrays.size();
		
	}

	public int getObjSize() {

		return size;
	}

	public int getObjBytes(byte[] objByteArray, int objPointer) {

		objPointer = writeBytes(objByteArray, objPointer, OBJ_CONTAINER_TYPE);
		objPointer = writeBytesShort(objByteArray, objPointer, objNameLength);
		objPointer = writeByteArray(objByteArray, objPointer, objName);
		objPointer = writeBytesInt(objByteArray, objPointer, size);
		
		
		objPointer = writeBytesShort(objByteArray, objPointer, fieldCount);
		for (Field field : fields) {
			objPointer = field.getFieldBytes(objByteArray, objPointer);
		}
		objPointer = writeBytesShort(objByteArray, objPointer, stringCount);
		for (ArrayString string : strings) {
			objPointer = string.getStringBytes(objByteArray, objPointer);
		}
		System.out.println("pointer: " + objPointer);

		objPointer = writeBytesShort(objByteArray, objPointer, arrayCount);
		//System.out.println(arrayCount);
		for (Array array : arrays) {
			objPointer = array.getArrayBytes(objByteArray, objPointer);
		}
		
		return objPointer;
	}

}
