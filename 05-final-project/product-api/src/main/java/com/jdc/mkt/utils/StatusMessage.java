package com.jdc.mkt.utils;

import com.jdc.mkt.api.outputs.Info;

import lombok.Data;

@Data
public class StatusMessage<ID> {

	private ID id;
	private String message;
	
	public StatusMessage<ID> success(Info info){
		if(null != info.getId()) {
			var mess = new StatusMessage<ID>();
			mess.setId(id);
			mess.setMessage("successfully save !");
			return mess;
		}
		return null;
	}
	
}
