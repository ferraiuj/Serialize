package com.jacob.serialize.deserialization.ddatabase;

import static com.jacob.serialize.deserialization.SerializationReader.readByte;
import static com.jacob.serialize.deserialization.SerializationReader.readInt;
import static com.jacob.serialize.deserialization.SerializationReader.readShort;
import static com.jacob.serialize.deserialization.SerializationReader.readString;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.jacob.serialize.ContainerType;
import com.jacob.serialize.deserialization.dobject.DObj;

public class DDataBase {

	public static final byte[] HEADER = "JFDB".getBytes();
	public static final byte DATABASE_CONTAINER_TYPE = ContainerType.DATABASE;
	public short dbNameLength;
	public byte[] dbName;
	protected int size = HEADER.length + 1 + 2 + 4 + 2;
	private short objectCount;
	public List<DObj> objects = new ArrayList<DObj>();

	private DDataBase() {

	}

	public static DDataBase Deserialize(byte[] data) {

		int pointer = 0;
		assert (readString(data, pointer, HEADER.length).equals(HEADER));

		pointer += HEADER.length;

		byte containerType = readByte(data, pointer++);
		assert (containerType == DATABASE_CONTAINER_TYPE);

		DDataBase result = new DDataBase();
		
		result.dbNameLength = readShort(data, pointer);
		pointer += 2;
		result.dbName = readString(data, pointer, result.dbNameLength).getBytes();
		pointer += result.dbNameLength;
		result.size = readInt(data, pointer);
		pointer += 4;
		result.objectCount = readShort(data, pointer);
		pointer += 2;
		
		
		for(int i = 0; i < result.objectCount; i++) {
			DObj object = DObj.Deserialize(data, pointer);
			result.objects.add(object);
			pointer += object.getObjSize();
		}
		return result;
	}

	public String getDBName() {
		return new String(dbName, 0, dbNameLength);
	}

	public static DDataBase DeserializeFile(String path) {
		byte[] buffer = null;
		try {
			BufferedInputStream stream = new BufferedInputStream(new FileInputStream(path));
			buffer = new byte[stream.available()];
			stream.read(buffer);
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Deserialize(buffer);
	}
}
