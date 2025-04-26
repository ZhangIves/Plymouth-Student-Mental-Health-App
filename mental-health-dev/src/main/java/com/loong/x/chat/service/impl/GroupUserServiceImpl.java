package com.loong.x.chat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loong.x.chat.mapper.GroupUserMapper;
import com.loong.x.chat.model.entity.GroupUser;
import com.loong.x.chat.service.GroupUserService;
import org.springframework.stereotype.Service;

/**
 * @Description: 群组人员接口实现类
 * @author: hdx
 * @Date: 2022-08-25 09:33
 * @version: 1.0
 **/
@Service
public class GroupUserServiceImpl extends ServiceImpl<GroupUserMapper, GroupUser> implements GroupUserService {

}
