package com.siyi.project.business.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.siyi.framework.aspectj.lang.annotation.Excel;
import com.siyi.framework.web.domain.BaseEntity;

/**
 * 作业信息对象 bus_job
 *
 * @author siyi
 * @date 2023-03-05
 */
public class Job extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 作业ID */
    private Long jobId;

    /** 作业编码 */
    @Excel(name = "作业编码")
    private String jobCode;

    /** 作业名称 */
    @Excel(name = "作业名称")
    private String jobName;

    /** 课程的ID */
    @Excel(name = "课程的ID")
    private Long courseId;

    /** 作业描述 */
    @Excel(name = "作业描述")
    private String jobDescription;

    /** 显示顺序 */
    @Excel(name = "显示顺序")
    private Integer jobSort;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    public void setJobId(Long jobId)
    {
        this.jobId = jobId;
    }

    public Long getJobId()
    {
        return jobId;
    }
    public void setJobCode(String jobCode)
    {
        this.jobCode = jobCode;
    }

    public String getJobCode()
    {
        return jobCode;
    }
    public void setJobName(String jobName)
    {
        this.jobName = jobName;
    }

    public String getJobName()
    {
        return jobName;
    }
    public void setCourseId(Long courseId)
    {
        this.courseId = courseId;
    }

    public Long getCourseId()
    {
        return courseId;
    }
    public void setJobDescription(String jobDescription)
    {
        this.jobDescription = jobDescription;
    }

    public String getJobDescription()
    {
        return jobDescription;
    }
    public void setJobSort(Integer jobSort)
    {
        this.jobSort = jobSort;
    }

    public Integer getJobSort()
    {
        return jobSort;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("jobId", getJobId())
            .append("jobCode", getJobCode())
            .append("jobName", getJobName())
            .append("courseId", getCourseId())
            .append("jobDescription", getJobDescription())
            .append("jobSort", getJobSort())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
