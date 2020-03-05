package com.jacob.serialize.serialization.database;

import static com.jacob.serialize.serialization.field.FieldWriter.writeByteArray;
import static com.jacob.serialize.serialization.field.FieldWriter.writeBytes;
import static com.jacob.serialize.serialization.field.FieldWriter.writeBytesInt;
import static com.jacob.serialize.serialization.field.FieldWriter.writeBytesShort;

import java.util.ArrayList;
import java.util.List;

import com.jacob.serialize.ContainerType;
import com.jacob.serialize.serialization.object.Obj;

public class DataBase {
	
	public static final byte [] HEADER = "JFDB".getBytes();
	public static final byte DATABASE_CONTAINER_TYPE = ContainerType.DATABASE;
	public short dbNameLength;
	public byte[] dbName;
	private int size = HEADER.length+ 1 + 2 + 4 + 2;
	private short objectCount;
	private List<Obj> objects = new ArrayList<Obj>();
	
	public DataBase(String dbNamePar) {
		setDBName(dbNamePar);
	}
	public void setDBName(String dbNamePar) {
		assert (dbNamePar.length() < Short.MAX_VALUE);

		if (this.dbName != null) {
			size -= this.dbName.length;
		}
		dbNameLength = (short) dbNamePar.length();
		this.dbName = dbNamePar.getBytes();
		size += dbNameLength;
	}

	public void addObject(Obj object) {
		objects.add(object);
		size += object.getObjSize();
		objectCount = (short) objects.size();
	}

	public int getDBSize() {

		return size;
	}

	public int getDBBytes(byte[] dbByteArray, int dbPointer) {

		dbPointer = writeByteArray(dbByteArray,dbPointer, HEADER);
		dbPointer = writeBytes(dbByteArray, dbPointer, DATABASE_CONTAINER_TYPE);
		dbPointer = writeBytesShort(dbByteArray, dbPointer, dbNameLength);
		dbPointer = writeByteArray(dbByteArray, dbPointer, dbName);
		dbPointer = writeBytesInt(dbByteArray, dbPointer, size);
		
	
		dbPointer = writeBytesShort(dbByteArray, dbPointer, objectCount);
		for (Obj object : objects) {
			dbPointer = object.getObjBytes(dbByteArray, dbPointer);
		}
		return dbPointer;
	}

	
}
