package com.siyi.project.business.service.impl;

import java.util.List;
import com.siyi.common.utils.DateUtils;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.siyi.project.business.mapper.JobTodoMapper;
import com.siyi.project.business.domain.JobTodo;
import com.siyi.project.business.service.IJobTodoService;

/**
 * 作业上传Service业务层处理
 *
 * @author siyi
 * @date 2023-03-05
 */
@Service
public class JobTodoServiceImpl implements IJobTodoService
{
    @Resource
    private JobTodoMapper jobTodoMapper;

    /**
     * 查询作业上传
     *
     * @param jobId 作业上传主键
     * @return 作业上传
     */
    @Override
    public JobTodo selectJobTodoByJobId(Long jobId)
    {
        return jobTodoMapper.selectJobTodoByJobId(jobId);
    }

    /**
     * 查询作业上传列表
     *
     * @param jobTodo 作业上传
     * @return 作业上传
     */
    @Override
    public List<JobTodo> selectJobTodoList(JobTodo jobTodo)
    {
        return jobTodoMapper.selectJobTodoList(jobTodo);
    }

    /**
     * 新增作业上传
     *
     * @param jobTodo 作业上传
     * @return 结果
     */
    @Override
    public int insertJobTodo(JobTodo jobTodo)
    {
        jobTodo.setCreateTime(DateUtils.getNowDate());
        return jobTodoMapper.insertJobTodo(jobTodo);
    }

    /**
     * 修改作业上传
     *
     * @param jobTodo 作业上传
     * @return 结果
     */
    @Override
    public int updateJobTodo(JobTodo jobTodo)
    {
        jobTodo.setUpdateTime(DateUtils.getNowDate());
        return jobTodoMapper.updateJobTodo(jobTodo);
    }

    /**
     * 批量删除作业上传
     *
     * @param jobIds 需要删除的作业上传主键
     * @return 结果
     */
    @Override
    public int deleteJobTodoByJobIds(Long[] jobIds)
    {
        return jobTodoMapper.deleteJobTodoByJobIds(jobIds);
    }

    /**
     * 删除作业上传信息
     *
     * @param jobId 作业上传主键
     * @return 结果
     */
    @Override
    public int deleteJobTodoByJobId(Long jobId)
    {
        return jobTodoMapper.deleteJobTodoByJobId(jobId);
    }
}
