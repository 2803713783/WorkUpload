package com.siyi.project.business.controller;

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
import com.siyi.project.business.domain.Job;
import com.siyi.project.business.service.IJobService;
import com.siyi.framework.web.controller.BaseController;
import com.siyi.framework.web.domain.AjaxResult;
import com.siyi.common.utils.poi.ExcelUtil;
import com.siyi.framework.web.page.TableDataInfo;

/**
 * 作业信息Controller
 *
 * @author siyi
 * @date 2023-03-05
 */
@RestController
@RequestMapping("/business/job")
public class JobController extends BaseController
{
    @Resource
    private IJobService jobService;

    /**
     * 查询作业信息列表
     */
    @PreAuthorize("@ss.hasPermi('business:job:list')")
    @GetMapping("/list")
    public TableDataInfo list(Job job)
    {
        startPage();
        List<Job> list = jobService.selectJobList(job);
        return getDataTable(list);
    }

    /**
     * 导出作业信息列表
     */
    @PreAuthorize("@ss.hasPermi('business:job:export')")
    @Log(title = "作业信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Job job)
    {
        List<Job> list = jobService.selectJobList(job);
        ExcelUtil<Job> util = new ExcelUtil<Job>(Job.class);
        util.exportExcel(response, list, "作业信息数据");
    }

    /**
     * 获取作业信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('business:job:query')")
    @GetMapping(value = "/{jobId}")
    public AjaxResult getInfo(@PathVariable("jobId") Long jobId)
    {
        return success(jobService.selectJobByJobId(jobId));
    }

    /**
     * 新增作业信息
     */
    @PreAuthorize("@ss.hasPermi('business:job:add')")
    @Log(title = "作业信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Job job)
    {
        return toAjax(jobService.insertJob(job));
    }

    /**
     * 修改作业信息
     */
    @PreAuthorize("@ss.hasPermi('business:job:edit')")
    @Log(title = "作业信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Job job)
    {
        return toAjax(jobService.updateJob(job));
    }

    /**
     * 删除作业信息
     */
    @PreAuthorize("@ss.hasPermi('business:job:remove')")
    @Log(title = "作业信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{jobIds}")
    public AjaxResult remove(@PathVariable Long[] jobIds)
    {
        return toAjax(jobService.deleteJobByJobIds(jobIds));
    }
}
