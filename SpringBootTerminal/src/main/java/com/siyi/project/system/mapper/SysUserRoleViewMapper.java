package com.siyi.project.system.mapper;

import java.util.List;
import com.siyi.project.system.domain.SysUserRoleView;

/**
 * VIEWMapper接口
 *
 * @author siyi
 * @date 2023-03-06
 */
public interface SysUserRoleViewMapper
{
    /**
     * 查询VIEW
     *
     * @param userId VIEW主键
     * @return VIEW
     */
    public SysUserRoleView selectSysUserRoleViewByUserId(Long userId);

    /**
     * 查询VIEW列表
     *
     * @param sysUserRoleView VIEW
     * @return VIEW集合
     */
    public List<SysUserRoleView> selectSysUserRoleViewList(SysUserRoleView sysUserRoleView);

    /**
     * 新增VIEW
     *
     * @param sysUserRoleView VIEW
     * @return 结果
     */
    public int insertSysUserRoleView(SysUserRoleView sysUserRoleView);

    /**
     * 修改VIEW
     *
     * @param sysUserRoleView VIEW
     * @return 结果
     */
    public int updateSysUserRoleView(SysUserRoleView sysUserRoleView);

    /**
     * 删除VIEW
     *
     * @param userId VIEW主键
     * @return 结果
     */
    public int deleteSysUserRoleViewByUserId(Long userId);

    /**
     * 批量删除VIEW
     *
     * @param userIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysUserRoleViewByUserIds(Long[] userIds);
}
