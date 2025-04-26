package com.loong.x.chat.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.loong.x.chat.model.vo.FriendVo;
import com.loong.x.chat.service.FriendService;
import com.loong.x.common.utils.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 好友控制器
 * @Author: hdx
 * @Date: 2022/1/13 16:39
 * @Version: 1.0
 */

@RestController
@RequestMapping("/friend")
public class FriendController {

    @Resource
    private FriendService friendService;

    /**
     * 好友保存
     * @param friendVo
     * @return
     */
    @PostMapping
    public R add(final @RequestBody  FriendVo friendVo, HttpServletRequest request) {
        String header = request.getHeader("X-Token");
        long parseLong = Long.parseLong(header);
        FriendVo resFriendVo = friendService.add(friendVo,parseLong);
        return R.success(resFriendVo);
    }

    /**
     * 好友列表
     * @param friendVo
     * @param current
     * @param size
     * @return
     */
    @GetMapping
    public R get(final FriendVo friendVo,
                            final @RequestParam(value = "current", required = false, defaultValue = "1") Integer current,
                            final @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
                 HttpServletRequest request) {
        String header = request.getHeader("X-Token");
        long parseLong = Long.parseLong(header);
        IPage<FriendVo> page = friendService.page(friendVo, current, size,parseLong);
        return R.success(page);
    }

    /**
     * 好友详情
     * @param friendId
     * @return
     */
    @GetMapping("/{friendId}")
    public R detail(@PathVariable String friendId, HttpServletRequest request) {
        String header = request.getHeader("X-Token");
        long parseLong = Long.parseLong(header);
        FriendVo friendVo = friendService.detail(friendId,parseLong);
        return R.success(friendVo);
    }

    /**
     * 好友更新
     * @param friendVo
     * @return
     */
    @PutMapping
    public R update(final @RequestBody FriendVo friendVo) {
        if (friendService.update(friendVo)) {
            return R.success(null);
        }
        return R.error("更新失败");
    }

    /**
     * 好友删除
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public R delete(@PathVariable String id) {
        if (friendService.delete(id)) {
            return R.success(null);
        }
        return R.error("删除失败");
    }
}
