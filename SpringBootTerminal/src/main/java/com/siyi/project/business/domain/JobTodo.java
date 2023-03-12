package com.siyi.project.business.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.siyi.framework.aspectj.lang.annotation.Excel;
import com.siyi.framework.web.domain.BaseEntity;

/**
 * 作业上传对象 bus_job_todo
 *
 * @author siyi
 * @date 2023-03-05
 */
public class JobTodo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 作业ID */
    private Long jobId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 作品地址 */
    @Excel(name = "作品地址")
    private String worksSrc;

    /** 作品简介 */
    @Excel(name = "作品简介")
    private String worksBrief;

    /** 作品描述 */
    @Excel(name = "作品描述")
    private String worksDescription;

    /** 作品类型 */
    @Excel(name = "作品类型")
    private Long worksType;

    /** 显示顺序 */
    @Excel(name = "显示顺序")
    private Long worksSort;

    /** 封面图片 */
    @Excel(name = "封面图片")
    private String worksCover;

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
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setWorksSrc(String worksSrc)
    {
        this.worksSrc = worksSrc;
    }

    public String getWorksSrc()
    {
        return worksSrc;
    }
    public void setWorksBrief(String worksBrief)
    {
        this.worksBrief = worksBrief;
    }

    public String getWorksBrief()
    {
        return worksBrief;
    }
    public void setWorksDescription(String worksDescription)
    {
        this.worksDescription = worksDescription;
    }

    public String getWorksDescription()
    {
        return worksDescription;
    }
    public void setWorksType(Long worksType)
    {
        this.worksType = worksType;
    }

    public Long getWorksType()
    {
        return worksType;
    }
    public void setWorksSort(Long worksSort)
    {
        this.worksSort = worksSort;
    }

    public Long getWorksSort()
    {
        return worksSort;
    }
    public void setWorksCover(String worksCover)
    {
        this.worksCover = worksCover;
    }

    public String getWorksCover()
    {
        return worksCover;
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
            .append("userId", getUserId())
            .append("worksSrc", getWorksSrc())
            .append("worksBrief", getWorksBrief())
            .append("worksDescription", getWorksDescription())
            .append("worksType", getWorksType())
            .append("worksSort", getWorksSort())
            .append("worksCover", getWorksCover())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
