package com.jacob.serialize.serialization.field.fieldtypes;

import static com.jacob.serialize.serialization.field.FieldWriter.writeBytesInt;

import com.jacob.serialize.DataType;
import com.jacob.serialize.serialization.field.Field;


public class FieldInt extends Field {

	public FieldInt(String fieldName, int fieldValue) {
		setFieldName(fieldName);
		fieldDataType = DataType.INT;
		fieldData = new byte[DataType.getDataTypeSize(DataType.INT)];
		writeBytesInt(fieldData, 0, fieldValue);
	}
}
