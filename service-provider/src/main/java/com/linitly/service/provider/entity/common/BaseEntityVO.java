package com.linitly.service.provider.entity.common;

import com.linitly.service.provider.helper.constant.GlobalConstant;
import com.linitly.service.provider.helper.groups.DeleteValidGroup;
import com.linitly.service.provider.helper.groups.UpdateValidGroup;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

@Data
public class BaseEntityVO {

    @NotNull(message = GlobalConstant.ID_NOTNULL_TIP, groups = {UpdateValidGroup.class, DeleteValidGroup.class})
    @Range(min = GlobalConstant.ID_MIN, message = GlobalConstant.ID_ERROR_TIP)
    private Long id;
}
