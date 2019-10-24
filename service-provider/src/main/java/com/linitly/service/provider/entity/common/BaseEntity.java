package com.linitly.service.provider.entity.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.linitly.service.provider.helper.constant.GlobalConstant;
import com.linitly.service.provider.helper.groups.DeleteValidGroup;
import com.linitly.service.provider.helper.groups.UpdateValidGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author linxiunan
 * @Description 基础实体类
 * @date 2018年10月18日
 */
@Data
public class BaseEntity implements Serializable {

    @ApiModelProperty(value = "id")
    @NotNull(message = GlobalConstant.ID_NOTNULL_TIP, groups = {UpdateValidGroup.class, DeleteValidGroup.class})
    @Range(min = GlobalConstant.ID_MIN, message = GlobalConstant.ID_ERROR_TIP)
    private Long id;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = GlobalConstant.COMMON_DATE_FORMAT)
    private Date createdTime;

    @ApiModelProperty(value = "最后一次更新时间")
    @JsonFormat(pattern = GlobalConstant.COMMON_DATE_FORMAT)
    private Date lastModifiedTime;

    @ApiModelProperty(value = "启用禁用状态", notes = "1 启用(默认) 0  禁用")
    private Integer enabled;

}
