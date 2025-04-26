package com.loong.x.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loong.x.app.entity.SysComplaint;
import com.loong.x.app.dao.SysComplaintDao;
import com.loong.x.app.mapper.SysComplaintMapper;
import com.loong.x.app.service.SysComplaintService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (SysComplaint)表服务实现类
 *
 * @author makejava
 * @since 2024-04-26 16:41:35
 */
@Service("sysComplaintService")
public class SysComplaintServiceImpl extends ServiceImpl<SysComplaintMapper,SysComplaint> implements SysComplaintService {
}
