//package com.inca.saas.ibs.gsp.validityReportRecord;
//
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.inca.saas.ibs.entity.ValidityReportRecord;
//
//@Service(ValidityReportRecordService.SERVICE_NAME)
//public class ValidityReportRecordServiceImpl implements ValidityReportRecordService {
//
//	@Override
//	public List<ValidityReportRecord> findAll() {
//		return baseMapper.findAll();
//	}
//	
//	@Autowired
//	ValidityReportRecordService validityReportRecordService;
//
//	@Override
//	public Map<String, Object> getMiniuiSearch(ValidityReportRecordQuery query) throws Exception {
//		
//		String keyword 		= query.getKeyword();// 关键字段过滤
//		int start 			= query.getPageIndex();
//		String sortField 	= query.getSortField();// 排序字段名如:status
//		String sortOrder 	= query.getSortOrder();// 排序是降序还是升序如:asc
//		int pagesize 		= query.getPageSize();
//		
//		
//		System.out.println(1111);
//		// TODO Auto-generated method stub
//		return null;
//	}
//
////	@Override
////	public List<ValidityReportRecord> getMiniuiSearch(ValidityReportRecordQuery query) {
////		EntityWrapper ew = new EntityWrapper();
////		ew.setEntity(new ValidityReportRecord());
////		
////		
////		String id = "1";
////		
////		ew.where("id = {0}", 1).orderBy("id");
////		
////		List<ValidityReportRecord> list = validityReportRecordService.selectList(ew);
//////		Page page2 = validityReportRecordService.selectPage(page, ew);
////	
////		return list;
////	}
//}
