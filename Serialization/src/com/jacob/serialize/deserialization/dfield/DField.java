package com.jacob.serialize.deserialization.dfield;

import static com.jacob.serialize.deserialization.SerializationReader.readShort;
import static com.jacob.serialize.deserialization.SerializationReader.readString;
import static com.jacob.serialize.deserialization.SerializationReader.readByteArray;
import static com.jacob.serialize.serialization.field.FieldWriter.writeByteArray;
import static com.jacob.serialize.serialization.field.FieldWriter.writeBytes;
import static com.jacob.serialize.serialization.field.FieldWriter.writeBytesShort;

import com.jacob.serialize.ContainerType;
import com.jacob.serialize.DataType;

public class DField {

	public static final byte FIELD_CONTAINER_TYPE = ContainerType.FIELD;
	public short fieldNameLength;
	public byte[] fieldName;
	public byte fieldDataType;
	public byte[] fieldData;

	private DField() {

	}

	public String getFieldName() {
		return new String(fieldName, 0, fieldNameLength);
	}
	public int getFieldSize() {
		assert(fieldData.length == DataType.getDataTypeSize(fieldDataType));
		//hardcoded numbers are the size of data allocated to 
		return 1 + 2 + fieldName.length + 1 + fieldData.length;
	}
	public static DField Deserialize(byte[] dfieldByteArray, int dFieldPointer) {
		byte containerType = dfieldByteArray[dFieldPointer++];
		assert (containerType == FIELD_CONTAINER_TYPE);

		DField result = new DField();

		result.fieldNameLength = readShort(dfieldByteArray, dFieldPointer);
		dFieldPointer += 2;
		result.fieldName = readString(dfieldByteArray, dFieldPointer, result.fieldNameLength).getBytes();
		dFieldPointer += result.fieldNameLength;

		result.fieldDataType = dfieldByteArray[dFieldPointer++];

		result.fieldData = new byte[DataType.getDataTypeSize(result.fieldDataType)];
		readByteArray(dfieldByteArray, dFieldPointer, result.fieldData);
		dFieldPointer += DataType.getDataTypeSize(result.fieldDataType);
		return result;
	}
}
