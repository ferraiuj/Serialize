package com.jacob.serialize.serialization.field.fieldtypes;

import static com.jacob.serialize.serialization.field.FieldWriter.writeBytesBool;

import com.jacob.serialize.DataType;
import com.jacob.serialize.serialization.field.Field;

public class FieldBool extends Field {
	
	public FieldBool(String fieldName, boolean fieldValue) {
		setFieldName(fieldName);
		fieldDataType = DataType.BOOLEAN;
		fieldData = new byte[DataType.getDataTypeSize(DataType.BOOLEAN)];
		writeBytesBool(fieldData, 0, fieldValue);
	}
}
