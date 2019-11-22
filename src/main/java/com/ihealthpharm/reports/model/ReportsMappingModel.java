package com.ihealthpharm.reports.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity(name = "reports_mapping")
@EqualsAndHashCode(of = "reportId",callSuper=false)
public class ReportsMappingModel implements Serializable {

	private static final long serialVersionUID = 9023481249252578288L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "REPORT_ID")
	private Integer reportId;

	@Column(name = "REPORT_CODE")
	private String reportCode;

	@Column(name = "REPORT_NAME")
	private String reportName;

	@Column(name = "INPUT_PARAMETERS")
	private String inputParameters;

	@Column(name = "REPORT_HEADER")
	private String reportHeader;

	@Column(name = "STORED_PROCEDURE_NAME")
	private String storedProcedureName;

	@Column(name = "REPORT_TITLE")
	private String title;

	@Column(name = "HEADER_CONTENT")
	private String headerContent;

	@Column(name = "FOOTER_CONTENT")
	private String footerContent;
	
	@Column(name = "SHOW_VERTICAL_LINES")
	private boolean showVerticalLines;
	
	@Column(name = "SHOW_BAR_CHARTS")
	private boolean showBarCharts;
	
	@Column(name = "CUSTOM_REPORT_GENERATOR")
	private String customReportGeneratorClazz;
	
	@Column(name = "CUSTOM_EXCEL_REPORT_GENERATOR")
	private String customExcelReportGeneratorClazz;
}
