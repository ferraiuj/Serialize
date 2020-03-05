package com.jacob.serialize.serialization.field.fieldtypes;

import static com.jacob.serialize.serialization.field.FieldWriter.writeBytesDouble;

import com.jacob.serialize.DataType;
import com.jacob.serialize.serialization.field.Field;

public class FieldDouble extends Field {
	public FieldDouble(String fieldName, double fieldValue) {
		setFieldName(fieldName);
		fieldDataType = DataType.DOUBLE;
		fieldData = new byte[DataType.getDataTypeSize(DataType.DOUBLE)];
		writeBytesDouble(fieldData, 0, fieldValue);
	}
}
