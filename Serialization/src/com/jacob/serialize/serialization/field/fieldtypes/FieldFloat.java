package com.jacob.serialize.serialization.field.fieldtypes;

import static com.jacob.serialize.serialization.field.FieldWriter.writeBytesFloat;

import com.jacob.serialize.DataType;
import com.jacob.serialize.serialization.field.Field;


public class FieldFloat extends Field {
	public FieldFloat(String fieldName, float fieldValue) {
		setFieldName(fieldName);
		fieldDataType = DataType.FLOAT;
		fieldData = new byte[DataType.getDataTypeSize(DataType.FLOAT)];
		writeBytesFloat(fieldData, 0, fieldValue);
	}
}
