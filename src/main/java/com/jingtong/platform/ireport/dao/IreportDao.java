package com.jingtong.platform.ireport.dao;

import java.util.List;

import com.jingtong.platform.ireport.pojo.IreportType;

public interface IreportDao {
	/**
	 * 查询报表模板
	 */
	public List<IreportType> serachReportModle(IreportType  type);
	
	/**
	 * 保存报表模板
	 */
	public Long saveReportModle(IreportType  type);
	/**删除报表模板
	 */
	public int deleteReportModle(IreportType  type);
	/**修改报表模板
	 */
	public int modifyReportModle(IreportType  type);
	
	
}
