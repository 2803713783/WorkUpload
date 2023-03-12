package com.siyi.project.system.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.siyi.project.system.mapper.SysUserRoleViewMapper;
import com.siyi.project.system.domain.SysUserRoleView;
import com.siyi.project.system.service.ISysUserRoleViewService;

import javax.annotation.Resource;

/**
 * VIEWService业务层处理
 *
 * @author siyi
 * @date 2023-03-06
 */
@Service
public class SysUserRoleViewServiceImpl implements ISysUserRoleViewService
{
    @Resource
    private SysUserRoleViewMapper sysUserRoleViewMapper;

    /**
     * 查询VIEW
     *
     * @param userId VIEW主键
     * @return VIEW
     */
    @Override
    public SysUserRoleView selectSysUserRoleViewByUserId(Long userId)
    {
        return sysUserRoleViewMapper.selectSysUserRoleViewByUserId(userId);
    }

    /**
     * 查询VIEW列表
     *
     * @param sysUserRoleView VIEW
     * @return VIEW
     */
    @Override
    public List<SysUserRoleView> selectSysUserRoleViewList(SysUserRoleView sysUserRoleView)
    {
        return sysUserRoleViewMapper.selectSysUserRoleViewList(sysUserRoleView);
    }

    /**
     * 新增VIEW
     *
     * @param sysUserRoleView VIEW
     * @return 结果
     */
    @Override
    public int insertSysUserRoleView(SysUserRoleView sysUserRoleView)
    {
        return sysUserRoleViewMapper.insertSysUserRoleView(sysUserRoleView);
    }

    /**
     * 修改VIEW
     *
     * @param sysUserRoleView VIEW
     * @return 结果
     */
    @Override
    public int updateSysUserRoleView(SysUserRoleView sysUserRoleView)
    {
        return sysUserRoleViewMapper.updateSysUserRoleView(sysUserRoleView);
    }

    /**
     * 批量删除VIEW
     *
     * @param userIds 需要删除的VIEW主键
     * @return 结果
     */
    @Override
    public int deleteSysUserRoleViewByUserIds(Long[] userIds)
    {
        return sysUserRoleViewMapper.deleteSysUserRoleViewByUserIds(userIds);
    }

    /**
     * 删除VIEW信息
     *
     * @param userId VIEW主键
     * @return 结果
     */
    @Override
    public int deleteSysUserRoleViewByUserId(Long userId)
    {
        return sysUserRoleViewMapper.deleteSysUserRoleViewByUserId(userId);
    }
}
