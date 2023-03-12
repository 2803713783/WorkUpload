package com.siyi.project.business.service.impl;

import java.util.List;
import com.siyi.common.utils.DateUtils;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.siyi.project.business.mapper.JobMapper;
import com.siyi.project.business.domain.Job;
import com.siyi.project.business.service.IJobService;

/**
 * 作业信息Service业务层处理
 *
 * @author siyi
 * @date 2023-03-05
 */
@Service
public class JobServiceImpl implements IJobService
{
    @Resource
    private JobMapper jobMapper;

    /**
     * 查询作业信息
     *
     * @param jobId 作业信息主键
     * @return 作业信息
     */
    @Override
    public Job selectJobByJobId(Long jobId)
    {
        return jobMapper.selectJobByJobId(jobId);
    }

    /**
     * 查询作业信息列表
     *
     * @param job 作业信息
     * @return 作业信息
     */
    @Override
    public List<Job> selectJobList(Job job)
    {
        return jobMapper.selectJobList(job);
    }

    /**
     * 新增作业信息
     *
     * @param job 作业信息
     * @return 结果
     */
    @Override
    public int insertJob(Job job)
    {
        job.setCreateTime(DateUtils.getNowDate());
        return jobMapper.insertJob(job);
    }

    /**
     * 修改作业信息
     *
     * @param job 作业信息
     * @return 结果
     */
    @Override
    public int updateJob(Job job)
    {
        job.setUpdateTime(DateUtils.getNowDate());
        return jobMapper.updateJob(job);
    }

    /**
     * 批量删除作业信息
     *
     * @param jobIds 需要删除的作业信息主键
     * @return 结果
     */
    @Override
    public int deleteJobByJobIds(Long[] jobIds)
    {
        return jobMapper.deleteJobByJobIds(jobIds);
    }

    /**
     * 删除作业信息信息
     *
     * @param jobId 作业信息主键
     * @return 结果
     */
    @Override
    public int deleteJobByJobId(Long jobId)
    {
        return jobMapper.deleteJobByJobId(jobId);
    }
}
