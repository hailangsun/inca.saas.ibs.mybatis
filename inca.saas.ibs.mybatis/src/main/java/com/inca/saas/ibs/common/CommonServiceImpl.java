package com.inca.saas.ibs.common;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapLikeType;
import com.inca.saas.ibs.util.export.CsvWriter;

@Service(CommonService.SERVICE_NAME)
@Transactional
public class CommonServiceImpl implements CommonService{
	final Log log = LogFactory.getLog(getClass());
	
	private static final String CSV = ".csv";
	
	private ObjectMapper om =new ObjectMapper();
	
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public CommonServiceImpl() {
		super();
	}

	@SuppressWarnings("unchecked")
	@Override
	public File getExportExcel(Map<String,Object> maps,Object... args) throws Exception {
		String sql 		= (String) maps.get("sql");
		String prefix 	= (String) maps.get("prefix");
		Map<String,ColumnConvert> convertMap = (Map<String, ColumnConvert>) maps.get("converMap");
		List<Map<String, Object>> mapList = getListMap((String)maps.get("columnsJson"));
		File csv = new File(prefix + CSV);
		jdbcTemplate.query(sql, args, new ResultSetExtractor() {
			
			@Override
			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				Vector<String>		vectorField		= new Vector<>();//field 
				Vector<String>		vectorHeaders 	= new Vector<>();//转换实际长度用
				// 要导出的表头
				for(int i=0; i<mapList.size();i++){
					Map<String, Object> map = mapList.get(i);
					if(!StringUtils.isEmpty(map.get("header")) && !StringUtils.isEmpty(map.get("field"))){
						vectorHeaders.add((String)map.get("header"));
						vectorField.add((String)map.get("field"));
					}
				}
				
				ResultSetMetaData metaData 			= rs.getMetaData();
				Set<String> metaDataColumns			= new HashSet();
				for (int i = 0; i < metaData.getColumnCount(); i++) {
					metaDataColumns.add(metaData.getColumnName(i + 1));
				}
				String[]  			headers 		= new String[vectorHeaders.size()];//导出的头
				String[]  			fields 			= new String[vectorField.size()];//field
				vectorHeaders.toArray(headers);
				vectorField.toArray(fields);
				CsvWriter wr = null;
				int rowIndex = 0;
				try {
					wr = new CsvWriter(new FileWriter(csv), ',');
					// 写入标题
					wr.writeRecord(headers);
					// 写入数据
					Map<String, Integer> fieldIndexes = new LinkedHashMap<>();
					while (rs.next()) {
						rowIndex++;
						
						String[] row = new String[vectorHeaders.size()];
						Map<String, Object> rowMap = new LinkedHashMap<>();
						
						for (int colIndex = 0; colIndex < vectorHeaders.size(); colIndex++) {
							if(rowIndex==1){
								fieldIndexes.put(fields[colIndex], colIndex);
							}
							if(!metaDataColumns.contains(fields[colIndex])){
								continue;
							}
							String colname=fields[colIndex];
							Object dbValue=rs.getObject(fields[colIndex]);
							rowMap.put(colname, dbValue);
							
							row[colIndex]=dbValue!=null?dbValue.toString():"";
						}
						//循环转换器
						Map<String, ColumnConvert> convertors = convertMap;
						if(convertors != null){
							Set<String> keySet = convertors.keySet();
							for (String field : keySet) {
								ColumnConvert columnConvert = convertors.get(field);
								try{
									String convertedValue=columnConvert.convert(rowMap, field, rowMap.get(field));
									Integer index =fieldIndexes.get(field);
									if(index!=null){
										row[index]=convertedValue;
									}
								}catch(Exception e){
									log.error("convert:",e);
									e.printStackTrace();
								}
							}
						}
						wr.writeRecord(row);
						if (rowIndex % 1000 == 0) {
							wr.flush();
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (wr != null)
						wr.close();
				}
				return rowIndex;
			}
		
		
		});
		
		return csv;
	}
	
	/**
	 * json转换List<Map<String,Object>>
	 * @param json
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getListMap(String json) throws Exception{
		MapLikeType mapType = om.getTypeFactory().constructMapLikeType(Map.class, String.class, Object.class);
		CollectionType  collectionType = om.getTypeFactory().constructCollectionType(Vector.class,mapType);
		List<Map<String, Object>> list = om.readValue(json, collectionType);
		return list;
	}
}
