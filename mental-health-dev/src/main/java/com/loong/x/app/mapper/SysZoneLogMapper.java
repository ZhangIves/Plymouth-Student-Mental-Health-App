package com.loong.x.app.mapper;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.loong.x.app.entity.SysZoneLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Description
 *
 * @author wcloong
 * @version 0.1.0
 * @since 2024/4/25 13:20
 */
@Mapper
public interface SysZoneLogMapper extends BaseMapper<SysZoneLog> {

    @Select({"SELECT \n" +
            "    z.id AS id, \n" +
            "    z.user_id AS userId, \n" +
            "    z.content AS content, \n" +
            "    z.create_time AS createTime, \n" +
            "    u.full_name AS fullName, \n" +
            "    u.avatar\n" +
            "FROM \n" +
            "    sys_zone_log z\n" +
            "JOIN \n" +
            "    sys_user u ON z.user_id = u.id\n" +
            "WHERE \n" +
            "    z.zone_id = #{zoneId}\n" +
            "ORDER BY \n" +
            "    z.create_time DESC;"})
    List<JSONObject> getCommentList(String zoneId);
}
