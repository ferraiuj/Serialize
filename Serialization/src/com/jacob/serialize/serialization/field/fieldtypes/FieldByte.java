package com.jacob.serialize.serialization.field.fieldtypes;

import static com.jacob.serialize.serialization.field.FieldWriter.writeBytes;

import com.jacob.serialize.DataType;
import com.jacob.serialize.serialization.field.Field;

public class FieldByte extends Field {
	
	public FieldByte(String fieldName, byte fieldValue) {
		setFieldName(fieldName);
		fieldDataType = DataType.BYTE;
		fieldData = new byte[DataType.getDataTypeSize(DataType.BYTE)];
		writeBytes(fieldData, 0, fieldValue);
	}
}
