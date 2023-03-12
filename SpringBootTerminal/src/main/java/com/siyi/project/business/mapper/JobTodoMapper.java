package com.siyi.project.business.mapper;

import java.util.List;
import com.siyi.project.business.domain.JobTodo;

/**
 * 作业上传Mapper接口
 * 
 * @author siyi
 * @date 2023-03-05
 */
public interface JobTodoMapper 
{
    /**
     * 查询作业上传
     * 
     * @param jobId 作业上传主键
     * @return 作业上传
     */
    public JobTodo selectJobTodoByJobId(Long jobId);

    /**
     * 查询作业上传列表
     * 
     * @param jobTodo 作业上传
     * @return 作业上传集合
     */
    public List<JobTodo> selectJobTodoList(JobTodo jobTodo);

    /**
     * 新增作业上传
     * 
     * @param jobTodo 作业上传
     * @return 结果
     */
    public int insertJobTodo(JobTodo jobTodo);

    /**
     * 修改作业上传
     * 
     * @param jobTodo 作业上传
     * @return 结果
     */
    public int updateJobTodo(JobTodo jobTodo);

    /**
     * 删除作业上传
     * 
     * @param jobId 作业上传主键
     * @return 结果
     */
    public int deleteJobTodoByJobId(Long jobId);

    /**
     * 批量删除作业上传
     * 
     * @param jobIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteJobTodoByJobIds(Long[] jobIds);
}
