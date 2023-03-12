package com.siyi.project.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.siyi.framework.aspectj.lang.annotation.Log;
import com.siyi.framework.aspectj.lang.enums.BusinessType;
import com.siyi.project.system.domain.SysUserRoleView;
import com.siyi.project.system.service.ISysUserRoleViewService;
import com.siyi.framework.web.controller.BaseController;
import com.siyi.framework.web.domain.AjaxResult;
import com.siyi.common.utils.poi.ExcelUtil;
import com.siyi.framework.web.page.TableDataInfo;

/**
 * VIEWController
 *
 * @author siyi
 * @date 2023-03-06
 */
@RestController
@RequestMapping("/business/view")
public class SysUserRoleViewController extends BaseController
{
    @Resource
    private ISysUserRoleViewService sysUserRoleViewService;

    /**
     * 查询VIEW列表
     */
    @PreAuthorize("@ss.hasPermi('business:view:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysUserRoleView sysUserRoleView)
    {
        startPage();
        List<SysUserRoleView> list = sysUserRoleViewService.selectSysUserRoleViewList(sysUserRoleView);
        return getDataTable(list);
    }

    /**
     * 导出VIEW列表
     */
    @PreAuthorize("@ss.hasPermi('business:view:export')")
    @Log(title = "VIEW", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysUserRoleView sysUserRoleView)
    {
        List<SysUserRoleView> list = sysUserRoleViewService.selectSysUserRoleViewList(sysUserRoleView);
        ExcelUtil<SysUserRoleView> util = new ExcelUtil<SysUserRoleView>(SysUserRoleView.class);
        util.exportExcel(response, list, "VIEW数据");
    }

    /**
     * 获取VIEW详细信息
     */
    @PreAuthorize("@ss.hasPermi('business:view:query')")
    @GetMapping(value = "/{userId}")
    public AjaxResult getInfo(@PathVariable("userId") Long userId)
    {
        return success(sysUserRoleViewService.selectSysUserRoleViewByUserId(userId));
    }

    /**
     * 新增VIEW
     */
    @PreAuthorize("@ss.hasPermi('business:view:add')")
    @Log(title = "VIEW", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysUserRoleView sysUserRoleView)
    {
        return toAjax(sysUserRoleViewService.insertSysUserRoleView(sysUserRoleView));
    }

    /**
     * 修改VIEW
     */
    @PreAuthorize("@ss.hasPermi('business:view:edit')")
    @Log(title = "VIEW", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysUserRoleView sysUserRoleView)
    {
        return toAjax(sysUserRoleViewService.updateSysUserRoleView(sysUserRoleView));
    }

    /**
     * 删除VIEW
     */
    @PreAuthorize("@ss.hasPermi('business:view:remove')")
    @Log(title = "VIEW", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds)
    {
        return toAjax(sysUserRoleViewService.deleteSysUserRoleViewByUserIds(userIds));
    }
}
