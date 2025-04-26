package com.loong.x.uniapp.chat.controller;

import com.loong.x.chat.model.vo.InvitationVo;
import com.loong.x.chat.service.InvitationService;
import com.loong.x.common.utils.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Description
 * 好友邀请
 *
 * @author wcloong
 * @version 0.1.0
 * @since 2024/5/16 10:16
 */
@RestController
@RequestMapping("/clientApi/invitation")
public class ClientInvitationController {

    @Resource
    private InvitationService friendInvitationService;

    /**
     * 好友邀请
     *
     * @param friendInvitationVo
     * @return
     */
    @PostMapping
    public R add(final @RequestBody InvitationVo friendInvitationVo, HttpServletRequest request) {
        String header = request.getHeader("Access-Token");
        long parseLong = Long.parseLong(header);
        InvitationVo resFriendInvitationVo = friendInvitationService.add(friendInvitationVo, parseLong);
        return R.success(resFriendInvitationVo);
    }

    /**
     * 好友重新邀请
     *
     * @param friendInvitationVo
     * @return
     */
    @PutMapping
    public R update(final @RequestBody InvitationVo friendInvitationVo, HttpServletRequest request) {
        String header = request.getHeader("Access-Token");
        long parseLong = Long.parseLong(header);
        friendInvitationService.update(friendInvitationVo, parseLong);
        return R.success(null);
    }

    /**
     * 好友邀请-同意/拒绝
     *
     * @param friendInvitationVo
     * @return
     */
    @PutMapping("/handle")
    public R handle(final @RequestBody InvitationVo friendInvitationVo) {
        friendInvitationService.handle(friendInvitationVo);
        return R.success(null);
    }

    /**
     * 好友邀请列表数据
     *
     * @param friendInvitationVo
     * @return
     */
    @GetMapping
    public R get(final InvitationVo friendInvitationVo, HttpServletRequest request) {
        String token = request.getHeader("Access-Token");
        if(token.equals("")){
            return R.error("请先登录");
        }
        List<InvitationVo> friendInvitations = friendInvitationService.list(friendInvitationVo);
        return R.success(friendInvitations);
    }

}
