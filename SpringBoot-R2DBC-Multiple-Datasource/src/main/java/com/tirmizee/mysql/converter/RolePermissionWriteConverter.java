package com.tirmizee.mysql.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.r2dbc.mapping.OutboundRow;
import org.springframework.r2dbc.core.Parameter;

import com.tirmizee.mysql.entities.RolePermission;


@WritingConverter
public class RolePermissionWriteConverter implements Converter<RolePermission, OutboundRow> {

	@Override
	public OutboundRow convert(RolePermission source) {
		 OutboundRow row = new OutboundRow();
		 row.append("id", Parameter.from(source.getPerId()));
		 return row;
	}

}
