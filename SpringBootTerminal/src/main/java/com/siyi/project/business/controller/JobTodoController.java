package com.siyi.project.business.controller;

import java.io.File;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.siyi.common.utils.file.FileUploadUtils;
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
import com.siyi.project.business.domain.JobTodo;
import com.siyi.project.business.service.IJobTodoService;
import com.siyi.framework.web.controller.BaseController;
import com.siyi.framework.web.domain.AjaxResult;
import com.siyi.common.utils.poi.ExcelUtil;
import com.siyi.framework.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 作业上传Controller
 *
 * @author siyi
 * @date 2023-03-05
 */
@RestController
@RequestMapping("/business/jobTodo")
public class JobTodoController extends BaseController
{
    @Resource
    private IJobTodoService jobTodoService;

    /**
     * 查询作业上传列表
     */
    @PreAuthorize("@ss.hasPermi('business:jobTodo:list')")
    @GetMapping("/list")
    public TableDataInfo list(JobTodo jobTodo)
    {
        startPage();
        List<JobTodo> list = jobTodoService.selectJobTodoList(jobTodo);
        return getDataTable(list);
    }

    /**
     * 导出作业上传列表
     */
    @PreAuthorize("@ss.hasPermi('business:jobTodo:export')")
    @Log(title = "作业上传", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, JobTodo jobTodo)
    {
        List<JobTodo> list = jobTodoService.selectJobTodoList(jobTodo);
        ExcelUtil<JobTodo> util = new ExcelUtil<JobTodo>(JobTodo.class);
        util.exportExcel(response, list, "作业上传数据");
    }

    /**
     * 获取作业上传详细信息
     */
    @PreAuthorize("@ss.hasPermi('business:jobTodo:query')")
    @GetMapping(value = "/{jobId}")
    public AjaxResult getInfo(@PathVariable("jobId") Long jobId)
    {
        return success(jobTodoService.selectJobTodoByJobId(jobId));
    }

    /**
     * 新增作业上传
     */
    @PreAuthorize("@ss.hasPermi('business:jobTodo:add')")
    @Log(title = "作业上传", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody JobTodo jobTodo, MultipartFile[] files, String signPath)
    {
        StringBuffer tempSrc = new StringBuffer("");
        for (int i = 0; i < files.length; i++) {
            tempSrc.append(files[i].getOriginalFilename());
            if (i!= files.length-1){
                tempSrc.append(",");
            }
        }
        jobTodo.setWorksSrc(String.valueOf(tempSrc));
        FileUploadUtils.saveMultiFile(files,signPath);
        return toAjax(jobTodoService.insertJobTodo(jobTodo));
    }

    /**
     * 修改作业上传
     */
    @PreAuthorize("@ss.hasPermi('business:jobTodo:edit')")
    @Log(title = "作业上传", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody JobTodo jobTodo)
    {
        return toAjax(jobTodoService.updateJobTodo(jobTodo));
    }

    /**
     * 删除作业上传
     */
    @PreAuthorize("@ss.hasPermi('business:jobTodo:remove')")
    @Log(title = "作业上传", businessType = BusinessType.DELETE)
	@DeleteMapping("/{jobIds}")
    public AjaxResult remove(@PathVariable Long[] jobIds)
    {
        return toAjax(jobTodoService.deleteJobTodoByJobIds(jobIds));
    }
}
