package com.jacob.serialize.serialization.field;
import static com.jacob.serialize.serialization.field.FieldWriter.writeByteArray;
import static com.jacob.serialize.serialization.field.FieldWriter.writeBytes;
import static com.jacob.serialize.serialization.field.FieldWriter.writeBytesShort;

import com.jacob.serialize.ContainerType;
import com.jacob.serialize.DataType;
public class Field {

	public static final byte FIELD_CONTAINER_TYPE = ContainerType.FIELD;
	public short fieldNameLength;
	public byte[] fieldName;
	public byte fieldDataType;
	public byte[] fieldData;        

	
	public void setFieldName(String fieldNamePar) {
		assert(fieldNamePar.length() < Short.MAX_VALUE);
		fieldNameLength = (short) fieldNamePar.length();
		this.fieldName = fieldNamePar.getBytes();
		
	}
	public int getFieldSize() {
		assert(fieldData.length == DataType.getDataTypeSize(fieldDataType));
		//hardcoded numbers are the size of data allocated to 
		return 1 + 2 + fieldName.length + 1 + fieldData.length;
	}
	public int getFieldBytes(byte[] fieldByteArray, int fieldPointer) {
		fieldPointer = writeBytes(fieldByteArray, fieldPointer, FIELD_CONTAINER_TYPE );
		fieldPointer = writeBytesShort(fieldByteArray, fieldPointer, fieldNameLength );
		fieldPointer = writeByteArray(fieldByteArray, fieldPointer, fieldName );
		fieldPointer = writeBytes(fieldByteArray, fieldPointer, fieldDataType );
		fieldPointer = writeByteArray(fieldByteArray, fieldPointer, fieldData );
		
		return fieldPointer;
	}
}
