package com.jacob.serialize.serialization.field.fieldtypes;

import static com.jacob.serialize.serialization.field.FieldWriter.writeBytesLong;

import com.jacob.serialize.DataType;
import com.jacob.serialize.serialization.field.Field;


public class FieldLong extends Field {
	public FieldLong(String fieldName, long fieldValue) {
		setFieldName(fieldName);
		fieldDataType = DataType.LONG;
		fieldData = new byte[DataType.getDataTypeSize(DataType.LONG)];
		writeBytesLong(fieldData, 0, fieldValue);
	}
}
