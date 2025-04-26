package com.loong.x.chat.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.pinyin.PinyinUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loong.x.app.entity.SysUser;
import com.loong.x.app.service.SysUserService;
import com.loong.x.chat.enums.CommonErrorEnum;
import com.loong.x.chat.enums.CommonStatusEnum;
import com.loong.x.chat.enums.InvitationStatusEnum;
import com.loong.x.chat.enums.ServiceErrorEnum;
import com.loong.x.chat.exception.ServiceException;
import com.loong.x.chat.mapper.InvitationMapper;
import com.loong.x.chat.model.entity.Friend;
import com.loong.x.chat.model.entity.FriendMsg;
import com.loong.x.chat.model.entity.Invitation;
import com.loong.x.chat.model.vo.InvitationVo;
import com.loong.x.chat.service.FriendMsgService;
import com.loong.x.chat.service.FriendService;
import com.loong.x.chat.service.InvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 好友邀请接口实现类
 * @Author: hdx
 * @Date: 2022/1/13 16:32
 * @Version: 1.0
 */
@Service
public class InvitationServiceImpl extends ServiceImpl<InvitationMapper, Invitation> implements InvitationService {

    @Resource
    private FriendService friendService;

    @Resource
    private FriendMsgService friendMsgService;

    @Resource
    private SimpMessageSendingOperations messagingTemplate;

    @Resource
    private SysUserService userService;


    @Override
    public InvitationVo add(InvitationVo friendInvitationVo,Long userId ) {
        Long friendId = friendInvitationVo.getFriendId();
        SysUser user = userService.getById(friendId);
        if (ObjectUtil.isNull(user)) {
            throw new ServiceException(ServiceErrorEnum.USER_IS_NO_EXIT);
        }
        // 校验是否已经是好友
        LambdaQueryWrapper<Friend> friendWrapper = new LambdaQueryWrapper<>();
        friendWrapper.eq(Friend::getUserId,userId).eq(Friend::getFriendId, friendId).eq(Friend::getStatus, CommonStatusEnum.YES.getResultCode());
        Friend friend = friendService.getOne(friendWrapper);
        if (ObjectUtil.isNotNull(friend)) {
            throw new ServiceException(ServiceErrorEnum.FRIEND_ALREADY_ERROR);
        }
        // 验证是否已在
        LambdaQueryWrapper<Invitation> invitationWrapper = new LambdaQueryWrapper<>();
        invitationWrapper.eq(Invitation::getUserId, userId).eq(Invitation::getFriendId, friendId).eq(Invitation::getStatus, CommonStatusEnum.INVITATION_WAIT.getResultCode());
        Invitation dbInvitation = this.getOne(invitationWrapper);
        if (ObjectUtil.isNotNull(dbInvitation)) {
            throw new ServiceException(ServiceErrorEnum.INVITATION_ALREADY_ERROR);
        }
        Invitation friendInvitation = new Invitation();
        friendInvitation.setUserId(userId);
        friendInvitation.setFriendId(friendId);
        friendInvitation.setFriendNickname(user.getFullName());
        friendInvitation.setUserNickname(userService.getById(userId).getFullName());
        friendInvitation.setUserAvatar(userService.getById(userId).getAvatar());
        friendInvitation.setFriendAvatar(user.getAvatar());
        if (!this.save(friendInvitation)) {
            throw new ServiceException(CommonErrorEnum.SAVE_ERROR);
        }
        InvitationVo resFriendInvitationVo = new InvitationVo();
        BeanUtil.copyProperties(friendInvitation, resFriendInvitationVo);
        LambdaQueryWrapper<Invitation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Invitation::getFriendId, friendId).eq(Invitation::getStatus, CommonStatusEnum.INVITATION_WAIT.getResultCode());
        List<Invitation> list = this.list(queryWrapper);
        // 发送邀请通知
        messagingTemplate.convertAndSend("/invitation/message/" + friendId, list.size());
        return resFriendInvitationVo;
    }

    @Override
    public void update(InvitationVo friendInvitationVo,Long userId ) {
        Long friendId = friendInvitationVo.getFriendId();
        SysUser user = userService.getById(friendId);
        if (ObjectUtil.isNull(user)) {
            throw new ServiceException(ServiceErrorEnum.USER_IS_NO_EXIT);
        }
        LambdaQueryWrapper<Invitation> query = new LambdaQueryWrapper();
        query.eq(ObjectUtil.isNotNull(userId), Invitation::getUserId, userId);
        query.eq(ObjectUtil.isNotNull(friendId), Invitation::getFriendId, friendId);
        // 获取指定添加好友数据
        Invitation friendInvitation = this.getOne(query);
        friendInvitation.setStatus(InvitationStatusEnum.WAIT.getResultCode());
        friendInvitation.setRemark(friendInvitationVo.getRemark());
        // 邀请数据个数
        LambdaQueryWrapper<Invitation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Invitation::getFriendId, friendId).eq(Invitation::getStatus, CommonStatusEnum.INVITATION_WAIT.getResultCode()).or().eq(Invitation::getStatus, CommonStatusEnum.INVITATION_REFUSE.getResultCode());
        List<Invitation> list = this.list(queryWrapper);
        // 发送邀请通知
        messagingTemplate.convertAndSend("/invitation/message/" + friendId, list.size());
        this.updateById(friendInvitation);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void handle(InvitationVo invitationVo) {
        Invitation invitation = this.getById(invitationVo.getId());
        byte status = invitationVo.getStatus();
        invitation.setRemark(invitationVo.getRemark());
        invitation.setReason(invitationVo.getReason());
        invitation.setStatus(status);
        if (CommonStatusEnum.INVITATION_PASS.getResultCode() == status && this.updateById(invitation)) {
            buildFriend(invitation);
            buildFriendMsg(invitation);
        } else {
            this.updateById(invitation);
        }
        LambdaQueryWrapper<Invitation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Invitation::getFriendId, invitation.getFriendId()).eq(Invitation::getStatus, CommonStatusEnum.INVITATION_WAIT.getResultCode());
        List<Invitation> list = this.list(queryWrapper);
        // 发送邀请通知
        messagingTemplate.convertAndSend("/invitation/message/" + invitationVo.getFriendId(), list.size());
    }

    private void buildFriend(Invitation invitation) {
        Long friendId = invitation.getFriendId();
        Long userId = invitation.getUserId();
        String friendNickname = invitation.getFriendNickname();
        String userNickname = invitation.getUserNickname();
        Friend user = new Friend();
        user.setUserId(userId);
        user.setFriendId(friendId);
        user.setNickname(friendNickname);
        String userFirstWord = StrUtil.sub(friendNickname, 0, 1);
        String userAlphabetic = PinyinUtil.getFirstLetter(userFirstWord, ",");
        user.setAlphabetic(userAlphabetic.toUpperCase());
        user.setStatus(CommonStatusEnum.YES.getResultCode());
        SysUser dbUser = userService.getById(friendId);
        user.setAvatar(dbUser.getAvatar());
        friendService.save(user);
        Friend friend = new Friend();
        friend.setUserId(friendId);
        friend.setFriendId(userId);
        friend.setNickname(userNickname);
        String friendFirstWord = StrUtil.sub(userNickname, 0, 1);
        String friendAlphabetic = PinyinUtil.getFirstLetter(friendFirstWord, ",");
        friend.setAlphabetic(friendAlphabetic.toUpperCase());
        friend.setStatus(CommonStatusEnum.YES.getResultCode());
        SysUser dbFriend = userService.getById(userId);
        friend.setAvatar(dbFriend.getAvatar());
        friendService.save(friend);
    }

    private void buildFriendMsg(Invitation invitation) {
        FriendMsg sysMsg = new FriendMsg();
        sysMsg.setStatus(CommonStatusEnum.MSG_STATUS_NORMAL.getResultCode());
        sysMsg.setMsgContent("以上是打招呼内容");
        sysMsg.setMsgType(CommonStatusEnum.MSG_TYPE_SYSTEM.getResultCode());
        sysMsg.setFromUserId(invitation.getUserId());
        sysMsg.setToUserId(invitation.getFriendId());
        friendMsgService.save(sysMsg);
        FriendMsg friendMsg = new FriendMsg();
        friendMsg.setStatus(CommonStatusEnum.MSG_STATUS_NORMAL.getResultCode());
        friendMsg.setMsgContent("我们已经是好友啦,开始聊天吧!");
        friendMsg.setMsgType(CommonStatusEnum.MSG_TYPE_TEXT.getResultCode());
        friendMsg.setFromUserId(invitation.getUserId());
        friendMsg.setToUserId(invitation.getFriendId());
        friendMsgService.save(friendMsg);
    }

    @Override
    public List<InvitationVo> list(InvitationVo friendInvitationVo) {
        Long userId = friendInvitationVo.getUserId();
        Long friendId = friendInvitationVo.getFriendId();
        LambdaQueryWrapper<Invitation> query = new LambdaQueryWrapper();
        query.eq(ObjectUtil.isNotNull(userId), Invitation::getUserId, userId);
        query.eq(ObjectUtil.isNotNull(friendId), Invitation::getFriendId, friendId);
        query.orderByDesc(Invitation::getCreateTime);
        List<Invitation> invitations = this.list(query);
        List<InvitationVo> invitationsVo = CollectionUtil.newArrayList();
        invitations.forEach(invitation -> {
            InvitationVo invitationVo = new InvitationVo();
            BeanUtil.copyProperties(invitation, invitationVo);
            invitationsVo.add(invitationVo);
        });
        return invitationsVo;
    }
}
