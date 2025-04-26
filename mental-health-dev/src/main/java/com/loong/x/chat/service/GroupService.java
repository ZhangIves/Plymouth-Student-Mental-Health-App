package com.loong.x.chat.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.loong.x.chat.model.entity.Group;
import com.loong.x.chat.model.vo.GroupUserJoinVo;
import com.loong.x.chat.model.vo.GroupUserVo;
import com.loong.x.chat.model.vo.GroupVo;
import com.loong.x.chat.model.vo.MsgHandleVo;

/**
 * Description
 * 群组接口
 * @author wcloong
 * @version 0.1.0
 * @since 2022/2/7 15:50
 */
public interface GroupService extends IService<Group> {

    /**
     * 群组保存
     *
     */
    GroupVo add(GroupVo groupVo,Long userId);

    /**
     * 群组分页列表
     */
    IPage<GroupVo> page(GroupUserVo groupUserVo, int current, int size,Long userId);

    /**
     * 群组信息
     */
    GroupVo detail(String groupId);

    /**
     * 拉人入群
     */
    void pull(GroupUserJoinVo groupUserJoinVo,Long userId);

    /**
     * 加入群组
     */
    void join(GroupUserJoinVo groupUserJoinVo);

    /**
     * 好友消息撤回
     */
    void msgHandle(MsgHandleVo msgHandleVo);
}
