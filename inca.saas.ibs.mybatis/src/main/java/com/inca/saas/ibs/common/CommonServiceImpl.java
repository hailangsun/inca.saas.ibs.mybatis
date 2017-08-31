package com.inca.saas.ibs.common;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(CommonService.SERVICE_NAME)
@Transactional
public class CommonServiceImpl implements CommonService{
	final Log log = LogFactory.getLog(getClass());

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public CommonServiceImpl() {
		super();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void getExportExcel() throws Exception {
		
		String sql = "";
		Object[] args =null  ;
		jdbcTemplate.query(sql, args, new ResultSetExtractor() {
			@Override
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				
				return rs;
			}
		});
		
	}
}
