package com.ihealthpharm.reports.helper;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.ihealthpharm.commons.JsonUtility;
import com.ihealthpharm.reports.dto.ReportsSearchCriteria;
import com.ihealthpharm.reports.model.ReportsMappingModel;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ReportsCommonUtility {

	public String prepareSQL(ReportsMappingModel model) {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(" { ");
		stringBuffer.append(" CALL ");
		stringBuffer.append(model.getStoredProcedureName());
		stringBuffer.append(" } ");
		return stringBuffer.toString();
	}

	@SuppressWarnings("unchecked")
	public String prepareWhereClause(ReportsMappingModel model, Map<String, Object> dataMap) {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(" WHERE 1 = 1 ");

		try {
		 
		String inputParameters = model.getInputParameters();
		  if(!ObjectUtils.isEmpty(inputParameters)) {
			List<ReportsSearchCriteria> list = JsonUtility.jsonToList(inputParameters, ReportsSearchCriteria.class);

			if (!ObjectUtils.isEmpty(list)) {
				for (ReportsSearchCriteria criteria : list) {
					if(dataMap.containsKey(criteria.getColumnName())) {
						Object value = dataMap.get(criteria.getColumnName());
						
						
						
						if(criteria.getColumnName().equals("COUNTER_PARTY_ACCOUNT_TYPE")|| criteria.getColumnName().equals("COUNTER_PARTY")) {
							stringBuffer.append(" OR ");
						}else {
							stringBuffer.append(" AND ");
						}
						
						//TODO need tune this logic
						if (StringUtils.equalsIgnoreCase("String", criteria.getType())) {
							if (StringUtils.equalsIgnoreCase("EQ", criteria.getOperator())) {
								stringBuffer.append(criteria.getAlias());
								stringBuffer.append(criteria.getColumnName());
								stringBuffer.append(" = ");
								stringBuffer.append('"');
								stringBuffer.append(value);
								stringBuffer.append('"');
								stringBuffer.append(" ");
							} else if (StringUtils.equalsIgnoreCase("LIKE", criteria.getOperator())) {
								stringBuffer.append(criteria.getAlias());
								stringBuffer.append(criteria.getColumnName());
								stringBuffer.append(" LIKE ");
								stringBuffer.append('"');
								stringBuffer.append(value);
								stringBuffer.append('%');
								stringBuffer.append('"');
								stringBuffer.append(" ");
							}
							else if (StringUtils.equalsIgnoreCase("NEQ", criteria.getOperator())) {
								stringBuffer.append(criteria.getAlias());
								stringBuffer.append(criteria.getColumnName());
								stringBuffer.append(" != ");
								stringBuffer.append('"');
								stringBuffer.append(value);
								stringBuffer.append('"');
								stringBuffer.append(" ");
							}

						}
						else if (StringUtils.equalsIgnoreCase("Number", criteria.getType())) {
							if (StringUtils.equalsIgnoreCase("EQ", criteria.getOperator())) {
								stringBuffer.append(criteria.getAlias());
								stringBuffer.append(criteria.getColumnName());
								stringBuffer.append(" = ");
								stringBuffer.append(value);
								stringBuffer.append(" ");
							}
							else if (StringUtils.equalsIgnoreCase("GE", criteria.getOperator())) {
								stringBuffer.append(criteria.getAlias());
								stringBuffer.append(criteria.getColumnName());
								stringBuffer.append(" >= ");
								stringBuffer.append(value);
								stringBuffer.append(" ");
							}
							else if (StringUtils.equalsIgnoreCase("LE", criteria.getOperator())) {
								stringBuffer.append(criteria.getAlias());
								stringBuffer.append(criteria.getColumnName());
								stringBuffer.append(" <= ");
								stringBuffer.append(value);
								stringBuffer.append(" ");
							}
							else if (StringUtils.equalsIgnoreCase("LIKE", criteria.getOperator())) {
								stringBuffer.append(criteria.getAlias());
								stringBuffer.append(criteria.getColumnName());
								stringBuffer.append(" LIKE ");
//								stringBuffer.append("%");
								stringBuffer.append(value);
								stringBuffer.append("%");
								stringBuffer.append(" ");
							
							}

						}
						//Date formate should be YYYY-MM-DD
						else if (StringUtils.equalsIgnoreCase("Date", criteria.getType())) {
							
							
							if (StringUtils.equalsIgnoreCase("EQ", criteria.getOperator())) {
								stringBuffer.append("DATE_FORMAT(" +criteria.getAlias() + criteria.getColumnName() +",'%Y-%m-%d') ");
								stringBuffer.append(" = ");
								stringBuffer.append("'"+value+"'");
								stringBuffer.append(" ");
							}
							else if (StringUtils.equalsIgnoreCase("GE", criteria.getOperator())) {
								stringBuffer.append("DATE_FORMAT(" +criteria.getAlias() + criteria.getColumnName() +",'%Y-%m-%d') ");
								stringBuffer.append(" >= ");
								stringBuffer.append("'"+value+"'");
								stringBuffer.append(" ");
							}
							else if (StringUtils.equalsIgnoreCase("LE", criteria.getOperator())) {
								stringBuffer.append("DATE_FORMAT(" +criteria.getAlias() + criteria.getColumnName() +",'%Y-%m-%d') ");
								stringBuffer.append(" <= ");
								stringBuffer.append("'"+value+"'");
								stringBuffer.append(" ");
							}

						}
					}
				}
			  }

			}
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
		}

		return stringBuffer.toString();
	}

}
