package com.linitly.service.provider.entity.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.linitly.service.provider.helper.constant.GlobalConstant;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description 基础删除实体类
 * @author linxiunan
 * @date 2018年10月18日
 */
@Data
public class BaseDeleteEntity extends BaseEntity {

	@ApiModelProperty(value = "删除时间")
	@JsonFormat(pattern = GlobalConstant.COMMON_DATE_FORMAT)
	public Date deleteTime;
}
