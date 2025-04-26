package com.loong.x.app.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.loong.x.app.dto.LoginUserDto;
import com.loong.x.app.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Description
 *
 * @author wcloong
 * @version 0.1.0
 * @since 2023/8/1 13:52
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select({"SELECT username FROM USER WHERE (roles = #{roles}) ORDER BY id DESC LIMIT 1"})
    String getMaxUsername(String roles);

    @Select({"SELECT\n" +
            "          id, username,roles\n" +
            "        FROM USER"})
    List<User> getAllUserNameRoles();


    @Select({
            "SELECT \n" +
                    "id,username\n" +
                    "FROM\n" +
                    "USER\n" +
                    "WHERE\n" +
                    "roles <> '管理员'" +
                    "ORDER BY username ASC"
    })
    List<Map<String, Integer>> getUserId();


    @Select({
            "SELECT\n" +
                    "r.account_num,r.college,r.major,r.grade,r.class_num,\n" +
                    "u.id,u.name,u.username,u.password,u.phone,u.sex,u.roles,u.id_number,u.status,u.create_time,u.update_time\n" +
                    "          FROM\n" +
                    "register r,USER u\n" +
                    "          WHERE\n" +
                    "r.account_num = u.username\n" +
                    "                    AND r.account_num = #{accountNum}"
    })
    LoginUserDto getUserRegister(String accountNum);


    @Select({
            "SELECT \n" +
                    "id,username,name,roles as role\n" +
                    "FROM\n" +
                    "USER\n" +
                    "WHERE\n" +
                    "roles <> '学生'" +
                    "ORDER BY username ASC"
    })
    List<Map<String, Integer>> getUserInfo();


    @Select("SELECT * FROM user WHERE expiration_date >= #{sevenDaysLater} AND expiration_date <= #{now}")
    List<User> selectUsersExpiringInSevenDays(Date now);

    // 如果你的MyBatis-Plus版本支持Lambda表达式，可以使用以下方式
    default List<User> selectUsersExpiringInSevenDays() {
        Date now = new Date();
        Date sevenDaysLater = new Date(now.getTime() + 7 * 24 * 60 * 60 * 1000);
        return selectList(new LambdaQueryWrapper<User>()
                .ge(User::getMemberEndTime, sevenDaysLater)
                .le(User::getMemberEndTime, now));
    }


    @Select({"SELECT id,name,username from user where phone = #{phone}"})
    User selectUserByPhone(String phone);


    @Update({"UPDATE USER\n" +
            "SET member_end_time = DATE_ADD(member_end_time, INTERVAL #{monthTime} MONTH)\n" +
            "WHERE id = #{id};"})
    boolean addMemberTime(Long id,Integer monthTime);

    @Update({"UPDATE USER\n" +
            "SET member_end_time = DATE_ADD(NOW(), INTERVAL #{monthTime} MONTH),member_start_time = NOW() \n" +
            "WHERE id = #{id};"})
    boolean addNewMemberTime(Long id,Integer monthTime);
}
