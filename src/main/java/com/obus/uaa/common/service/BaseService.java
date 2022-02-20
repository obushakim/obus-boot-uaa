package com.obus.uaa.common.service;

import com.obus.uaa.common.model.base.BaseRequest;
import com.obus.uaa.common.model.base.BaseResponse;

public interface BaseService<T extends BaseRequest, V extends BaseResponse> {
	V execute(T input);
}
