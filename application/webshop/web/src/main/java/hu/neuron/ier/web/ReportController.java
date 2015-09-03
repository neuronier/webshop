package hu.neuron.ier.web;

import hu.neuron.ier.business.purchase.PurchaseServiceRemote;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

@ViewScoped
@ManagedBean(name = "reportController")
public class ReportController {
	private LineChartModel incomeModel;
	private LineChartModel salesModel;
	private Date reportFrom;
	private Date reportTo;
	private List<ReportElement> salesNumberList;
	private List<ReportElement> incomeNumberList;
	
	@EJB(name="PurchaseService", mappedName="PurchaseService")
	private PurchaseServiceRemote purchaseService;
	
	public List<ReportElement> getSalesNumberList() {
		return salesNumberList;
	}

	public void setSalesNumberList(List<ReportElement> salesNumberList) {
		this.salesNumberList = salesNumberList;
	}

	public Date getReportFrom() {
		return reportFrom;
	}

	public void setReportFrom(Date reportFrom) {
		this.reportFrom = reportFrom;
	}

	public Date getReportTo() {
		return reportTo;
	}

	public void setReportTo(Date reportTo) {
		this.reportTo = reportTo;
	}

	@PostConstruct
    public void init() {
		Calendar cal = Calendar.getInstance();

		cal.set(2015, 1, 1);
		reportFrom = cal.getTime();

		cal.set(2016, 5, 1);
		reportTo = cal.getTime();
		
		salesNumberList = new ArrayList<>();
		incomeNumberList = new ArrayList<>();
		
        generateSalesNumberList(reportFrom, reportTo);
        generateIncomeNumberList(reportFrom, reportTo);
        
        generateSalesChart();
        generateIncomeChart();
    }
	
	public void generateSalesChart() {
		salesModel = new LineChartModel();
		LineChartSeries number = new LineChartSeries();
		
		number.setFill(true);
		number.setLabel("Sales");
		for (ReportElement reportElement : salesNumberList) {
			String date = new SimpleDateFormat("yyyy-MM-dd").format(reportElement.getDate());
			number.set(date, reportElement.getNumber());
		}
		
		salesModel.addSeries(number);

		salesModel.setTitle("Sales Chart");
		salesModel.setLegendPosition("ne");
		salesModel.setStacked(true);
		salesModel.setShowPointLabels(true);

		Axis xAxis = new CategoryAxis("Years");
		salesModel.getAxes().put(AxisType.X, xAxis);
		Axis yAxis = salesModel.getAxis(AxisType.Y);
		yAxis.setLabel("Sales");
		yAxis.setMin(0);
		
	}

	public void generateIncomeChart() {
		incomeModel = new LineChartModel();

		LineChartSeries number = new LineChartSeries();
		number.setFill(true);
		number.setLabel("Income");
		for (ReportElement reportElement : incomeNumberList) {
			String date = new SimpleDateFormat("yyyy-MM-dd").format(reportElement.getDate());
			number.set(date, reportElement.getNumber());
		}

		incomeModel.addSeries(number);

		incomeModel.setTitle("Income Chart");
		incomeModel.setLegendPosition("ne");
		incomeModel.setStacked(true);
		incomeModel.setShowPointLabels(true);

		Axis xAxis = new CategoryAxis("Dates");
		incomeModel.getAxes().put(AxisType.X, xAxis);
		Axis yAxis = incomeModel.getAxis(AxisType.Y);
		yAxis.setLabel("Income");
		yAxis.setMin(0);
		
	}
	
	
	public List<ReportElement> getIncomeNumberList() {
		return incomeNumberList;
	}

	public void setIncomeNumberList(List<ReportElement> incomeNumberList) {
		this.incomeNumberList = incomeNumberList;
	}

	public void generateIncomeNumberList(Date start, Date end){
		incomeNumberList = new ArrayList<>();
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(end);
		endCalendar.add(Calendar.MONTH, 1);
		Calendar currentCalendar = Calendar.getInstance();
		currentCalendar.setTime(start);
		currentCalendar.add(Calendar.MONTH, 1);

		while (currentCalendar.before(endCalendar)) {
			try {
				ReportElement element = new ReportElement();
				element.setDate(currentCalendar.getTime());
				element.setNumber(purchaseService.findIncomeByMonth(currentCalendar.get(Calendar.YEAR), currentCalendar.get(Calendar.MONTH)));

				incomeNumberList.add(element);

				currentCalendar.add(Calendar.MONTH, 1);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void generateSalesNumberList(Date start, Date end){
		salesNumberList = new ArrayList<>();
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(end);
		endCalendar.add(Calendar.MONTH, 1);
		Calendar currentCalendar = Calendar.getInstance();
		currentCalendar.setTime(start);
		currentCalendar.add(Calendar.MONTH, 1);

		while (currentCalendar.before(endCalendar)) {
			ReportElement element = new ReportElement();
			element.setDate(currentCalendar.getTime());
			element.setNumber(purchaseService.findCountByMonth(currentCalendar.get(Calendar.YEAR), currentCalendar.get(Calendar.MONTH)));

			salesNumberList.add(element);

			currentCalendar.add(Calendar.MONTH, 1);
		}
	}
	
	public void onTabChange(TabChangeEvent event) {
		FacesMessage msg = new FacesMessage("Tab Changed", "Active Tab: "
				+ event.getTab().getTitle());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		String tabId; 
		tabId = event.getTab().getId();
		if (tabId.equals("income")) {
			generateIncomeNumberList(reportFrom, reportTo);
			generateIncomeChart();
		} else if (tabId.equals("sales")){
			generateSalesNumberList(reportFrom, reportTo);
			generateSalesChart();
		}
	}
	
	public void salesNumGenerateAction() {
		generateSalesNumberList(reportFrom, reportTo);
		generateSalesChart();
		RequestContext.getCurrentInstance().update("reportForm:tabView:tabViewId:sales");
	}

	public void incomeNumGenerateAction() {
		generateIncomeNumberList(reportFrom, reportTo);
		generateIncomeChart();
		RequestContext.getCurrentInstance().update("reportForm:tabView:tabViewId:income");
	}

	public LineChartModel getIncomeModel() {
		return incomeModel;
	}

	public void setIncomeModel(LineChartModel incomeModel) {
		this.incomeModel = incomeModel;
	}

	public LineChartModel getSalesModel() {
		return salesModel;
	}

	public void setSalesModel(LineChartModel salesModel) {
		this.salesModel = salesModel;
	}

	
	
	
}
