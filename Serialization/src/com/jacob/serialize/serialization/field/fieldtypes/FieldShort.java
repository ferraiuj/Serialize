package com.jacob.serialize.serialization.field.fieldtypes;

import static com.jacob.serialize.serialization.field.FieldWriter.writeBytesShort;

import com.jacob.serialize.DataType;
import com.jacob.serialize.serialization.field.Field;


public class FieldShort extends Field {
	public FieldShort(String fieldName, short fieldValue) {
		setFieldName(fieldName);
		fieldDataType = DataType.SHORT;
		fieldData = new byte[DataType.getDataTypeSize(DataType.SHORT)];
		writeBytesShort(fieldData, 0, fieldValue);
	}
}
