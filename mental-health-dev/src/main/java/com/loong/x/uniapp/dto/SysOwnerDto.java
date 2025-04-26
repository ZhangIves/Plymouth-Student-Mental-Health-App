package com.loong.x.uniapp.dto;

import com.loong.x.app.entity.SysImage;
import com.loong.x.app.entity.SysOwner;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Description
 *
 * @author wcloong
 * @version 0.1.0
 * @since 2024/5/8 10:41
 */
@Data
public class SysOwnerDto{
    private SysOwner owner;
    private List<SysImage> sysImageList;

}
