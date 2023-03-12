package com.siyi.project.business.service;

import java.util.List;
import com.siyi.project.business.domain.Job;

/**
 * 作业信息Service接口
 * 
 * @author siyi
 * @date 2023-03-05
 */
public interface IJobService 
{
    /**
     * 查询作业信息
     * 
     * @param jobId 作业信息主键
     * @return 作业信息
     */
    public Job selectJobByJobId(Long jobId);

    /**
     * 查询作业信息列表
     * 
     * @param job 作业信息
     * @return 作业信息集合
     */
    public List<Job> selectJobList(Job job);

    /**
     * 新增作业信息
     * 
     * @param job 作业信息
     * @return 结果
     */
    public int insertJob(Job job);

    /**
     * 修改作业信息
     * 
     * @param job 作业信息
     * @return 结果
     */
    public int updateJob(Job job);

    /**
     * 批量删除作业信息
     * 
     * @param jobIds 需要删除的作业信息主键集合
     * @return 结果
     */
    public int deleteJobByJobIds(Long[] jobIds);

    /**
     * 删除作业信息信息
     * 
     * @param jobId 作业信息主键
     * @return 结果
     */
    public int deleteJobByJobId(Long jobId);
}
