package com.inca.saas.ibs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.inca.saas.ibs.entity.ValidityReportRecord;

@Mapper
public interface ValidityReportRecordMapper {

//	@Select("select id,rsa_validity_report_id from gsp_validity_report_record")
	List<ValidityReportRecord> findAll();

}
