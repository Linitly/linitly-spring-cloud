package com.linitly.service.provider.dao;

import com.linitly.service.provider.entity.common.BaseLog;

import java.util.List;
import java.util.Map;

public interface LogMapper {

    void insert(BaseLog baseLog);

    void insertAll(Map<String, Object> map);
}
