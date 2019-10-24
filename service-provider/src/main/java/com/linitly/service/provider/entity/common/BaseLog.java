package com.linitly.service.provider.entity.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BaseLog extends BaseEntity {

	private static final long serialVersionUID = 3675275872643587133L;

	@ApiModelProperty(value = "ip")
	private String ip;

	@ApiModelProperty(value = "操作")
	private String operation;

	@ApiModelProperty(value = "操作的实体id")
	private Long entityId;

	@ApiModelProperty(value = "日志信息")
	private String log;

	@ApiModelProperty(value = "改变的json")
	private String changeJson;

	@ApiModelProperty(value = "表名")
	private String tableName;

	@ApiModelProperty(value = "操作人id或openId")
	private String operatorId;

	@ApiModelProperty(value = "操作人类型")
	private Integer operatorType;
}
