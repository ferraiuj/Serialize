package com.jacob.serialize.serialization.field.fieldtypes;

import static com.jacob.serialize.serialization.field.FieldWriter.writeBytesChar;

import com.jacob.serialize.DataType;
import com.jacob.serialize.serialization.field.Field;
public class FieldChar extends Field {
	public FieldChar(String fieldName, char fieldValue) {
		setFieldName(fieldName);
		fieldDataType = DataType.CHAR;
		fieldData = new byte[DataType.getDataTypeSize(DataType.CHAR)];
		writeBytesChar(fieldData, 0, fieldValue);
	}
}
