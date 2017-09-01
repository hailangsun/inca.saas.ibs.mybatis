package com.inca.saas.ibs.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.inca.saas.ibs.entity.ValidityReportRecord;

@Mapper
public interface ValidityReportRecordMapper extends BaseMapper<ValidityReportRecord>{

//	@Select("select id,rsa_validity_report_id from gsp_validity_report_record")
//	List<ValidityReportRecord> findAll();

}
