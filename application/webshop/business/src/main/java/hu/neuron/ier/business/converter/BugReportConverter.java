package hu.neuron.ier.business.converter;

import hu.neuron.ier.business.vo.BugReportVO;
import hu.neuron.ier.core.entity.BugReport;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Singleton;
import javax.interceptor.Interceptors;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

@Singleton
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class BugReportConverter {

	@Autowired
	@Qualifier("mapper")
	Mapper mapper;

	public BugReportVO toVO(BugReport bugReport) {
		if (bugReport == null) {
			return null;
		}
		return mapper.map(bugReport, BugReportVO.class);
	}

	public BugReport toEntity(BugReportVO reportVO) {
		if (reportVO == null) {
			return null;
		}
		return mapper.map(reportVO, BugReport.class);
	}

	public List<BugReportVO> toVO(List<BugReport> bugReports) {
		if (bugReports == null) {
			return null;
		}
		List<BugReportVO> vos = new ArrayList<BugReportVO>();
		for (BugReport bugReport : bugReports) {
			vos.add(toVO(bugReport));
		}
		return vos;
	}

	public List<BugReport> toEntity(List<BugReportVO> bugReportVOs) {
		if (bugReportVOs == null) {
			return null;
		}
		List<BugReport> bugReports = new ArrayList<BugReport>();
		for (BugReportVO bugReportVO : bugReportVOs) {
			bugReports.add(toEntity(bugReportVO));
		}
		return bugReports;
	}
}
