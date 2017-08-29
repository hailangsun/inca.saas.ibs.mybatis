package com.inca.saas.ibs.support;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class BaseDao{
	Logger logger = LoggerFactory.getLogger(getClass());

	public JdbcTemplate getJdbcTemplate() {
		return (JdbcTemplate) SpringContextHolder.getApplicationContext().getBean("jdbcTemplate");
	}

	public QueryResult<?> search(String sql, Query query, Object... args) {
		return search(getJdbcTemplate(), sql, query, args);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public QueryResult search(JdbcTemplate jdbcTemplate, String baseSql, Query query, Object... args) {
		logger.info("=======================================================================================");
		final QueryResult result = new QueryResult();
		String pageablesql = buildPageableSql(baseSql, query);
		logger.info(query.getLoggerPrefix() + " sql :" + pageablesql);
		List data = jdbcTemplate.queryForList(pageablesql, args);
		jdbcTemplate.query(buildCountSql(baseSql), args, rs -> {
			result.setTotal(rs.getInt(1));
		});
		result.addData(data);
		logger.info("=======================================================================================");
		return result;
	}

	public String buildPageableSql(String baseSql, Query query) {
		StringBuilder sb = new StringBuilder();
		String sortField = query.getSortField();
		String sortOrder = query.getSortOrder();
		sb.append("select * from ( ");
		sb.append(baseSql);
		sb.append(" ) t");
		if (!StringUtils.isEmpty(sortField)) {
			if ("desc".equals(sortOrder) == false)
				sortOrder = "asc";
			sb.append(" order by " + sortField + " " + sortOrder);
		} else if (!StringUtils.isEmpty(query.getDefaultSort())) {
			sb.append(" order by " + query.getDefaultSort());
		}
		int limit = query.getPageSize();
		int offset = limit * query.getPageIndex();
		sb.append(" limit ");
		sb.append(query.getPageSize());
		sb.append(" offset ");
		sb.append(offset);
		return sb.toString();
	}

	public String buildCountSql(String baseSql) {
		return "select count(1) from (" + baseSql + ") a";
	}
}
