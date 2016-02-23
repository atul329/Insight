package com.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.DAO.IssueDescriptionDAO;
import com.DAO.IssueDetailDAO;
import com.google.gson.Gson;
import com.model.IssueDescription;
import com.model.IssueDetail;


public class ExcelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CellStyle cs = null;
	private CellStyle csBold = null;
	private CellStyle csTop = null;
	private CellStyle csRight = null;
	private CellStyle csBottom = null;
	private CellStyle csLeft = null;
	private CellStyle csTopLeft = null;
	private CellStyle csTopRight = null;
	private CellStyle csBottomLeft = null;
	private CellStyle csBottomRight = null;
	
    public ExcelServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doPost(request, response);	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		if (action.equalsIgnoreCase("exportToExcel")) {

			int trackerId = Integer.parseInt(request.getParameter("trackerId"));
			System.out
					.println("Form successfully submitted. Inside Excel Servlet :)");
			int team = Integer.parseInt(request.getParameter("teamId").trim());
			String status = request.getParameter("status");
			IssueDescriptionDAO issueDescriptionDAO = new IssueDescriptionDAO();
			ArrayList<IssueDescription> issueDescriptions = new ArrayList<IssueDescription>();
			String sheetName = "";
			String fileName = "";
			switch (trackerId) {
			case 1:
				sheetName = "SME Issue Tracker";
				fileName = "SME_ISSUE_TRACKER.xls";
				break;
			case 2:
				sheetName = "WorkAround Tracker";
				fileName = "WORK_AROUND_TRACKER.xls";
				break;
			case 3:
				sheetName = "Health Check Tracker";
				fileName = "HEALTH_CHECK_TRACKER.xls";
				break;
			case 4:
				sheetName = "Internal Tracker";
				fileName = "INTERNAL_TRACKER.xls";
				break;
			}
			issueDescriptions = issueDescriptionDAO.fetchTeamAndStatusIssue(team, status, trackerId);
			try {
				HSSFWorkbook workbook = new HSSFWorkbook();
				Sheet sheet = workbook.createSheet(sheetName);

				// Setup some styles that we need for the Cells
				setCellStyles(workbook);
				CellStyle cellStyle = workbook.createCellStyle();
				CreationHelper createHelper = workbook.getCreationHelper();
				cellStyle.setDataFormat(createHelper.createDataFormat()
						.getFormat("MMM dd, yyyy"));
				// Set Column Widths
				sheet.setColumnWidth(0, 2500);
				sheet.setColumnWidth(1, 2500);
				sheet.setColumnWidth(2, 6000);
				sheet.setColumnWidth(3, 10000);
				sheet.setColumnWidth(4, 2000);
				sheet.setColumnWidth(5, 2000);
				sheet.setColumnWidth(6, 2000);
				sheet.setColumnWidth(7, 2000);
				sheet.setColumnWidth(8, 2000);
				sheet.setColumnWidth(9, 2000);
				sheet.setColumnWidth(10, 2000);
				// Setup the Page margins - Left, Right, Top and Bottom
				sheet.setMargin(Sheet.LeftMargin, 0.25);
				sheet.setMargin(Sheet.RightMargin, 0.25);
				sheet.setMargin(Sheet.TopMargin, 0.75);
				sheet.setMargin(Sheet.BottomMargin, 0.75);

				// Setup the Header and Footer Margins
				sheet.setMargin(Sheet.HeaderMargin, 0.25);
				sheet.setMargin(Sheet.FooterMargin, 0.25);
				int rowIndex = -1;
				Row row = null;
				Cell c = null;
				// Generate Excel based on different tracker
				if(trackerId==1 || trackerId==4 || trackerId==3){
					row = sheet.createRow(++rowIndex);
					c = row.createCell(0);
					c.setCellValue("Issue Log Date");
					c.setCellStyle(csBold);
					c = row.createCell(1);
					c.setCellValue("Team");
					c.setCellStyle(csBold);
					c = row.createCell(2);
					c.setCellValue("Issue Name");
					c.setCellStyle(csBold);
					c = row.createCell(3);
					c.setCellValue("Issue Description");
					c.setCellStyle(csBold);
					c = row.createCell(4);
					c.setCellValue("Status");
					c.setCellStyle(csBold);
					c = row.createCell(5);
					c.setCellValue("Remarks");
					c.setCellStyle(csBold);
					c = row.createCell(6);
					c.setCellValue("Pending With");
					c.setCellStyle(csBold);
					c = row.createCell(7);
					c.setCellValue("Defect Id");
					c.setCellStyle(csBold);
					c = row.createCell(8);
					c.setCellValue("Fix TimeLine");
					c.setCellStyle(csBold);
					c = row.createCell(9);
					c.setCellValue("Closed Date");
					c.setCellStyle(csBold);
					c = row.createCell(10);
					c.setCellValue("Last Update");
					c.setCellStyle(csBold);
					for (IssueDescription issueDescription : issueDescriptions) {
						row = sheet.createRow(++rowIndex);
						c = row.createCell(0);
						c.setCellValue(issueDescription.getIssueLogDate());
						c.setCellStyle(cellStyle);
						c = row.createCell(1);
						c.setCellValue(issueDescription.getUpssTeam().getTeamName());
						c.setCellStyle(cs);
						c = row.createCell(2);
						c.setCellValue(issueDescription.getIssueName());
						c.setCellStyle(cs);
						c = row.createCell(3);
						c.setCellValue(issueDescription.getIssueDesc());
						c.setCellStyle(cs);
						c = row.createCell(4);
						c.setCellValue(issueDescription.getStatus());
						c.setCellStyle(cs);
						c = row.createCell(5);
						c.setCellValue(issueDescription.getRemarks());
						c.setCellStyle(cs);
						c = row.createCell(6);
						c.setCellValue(issueDescription.getPendingWith());
						c.setCellStyle(cs);
						c = row.createCell(7);
						c.setCellValue(issueDescription.getDefectId());
						c.setCellStyle(cs);
						c = row.createCell(8);
						c.setCellValue(issueDescription.getFixTimeLine());
						c.setCellStyle(cs);
						c = row.createCell(9);
						c.setCellValue(issueDescription.getClosedDate());
						c.setCellStyle(cs);
						c = row.createCell(10);
						c.setCellValue(issueDescription.getSysUpdateDate());
						c.setCellStyle(cellStyle);
					}
				}else if(trackerId==2){
					row = sheet.createRow(++rowIndex);
					c = row.createCell(0);
					c.setCellValue("Issue Log Date");
					c.setCellStyle(csBold);
					c = row.createCell(1);
					c.setCellValue("Team");
					c.setCellStyle(csBold);
					c = row.createCell(2);
					c.setCellValue("Issue Name");
					c.setCellStyle(csBold);
					c = row.createCell(3);
					c.setCellValue("Issue Description");
					c.setCellStyle(csBold);
					c = row.createCell(4);
					c.setCellValue("Deployed");
					c.setCellStyle(csBold);
					c = row.createCell(5);
					c.setCellValue("Frequency");
					c.setCellStyle(csBold);
					c = row.createCell(6);
					c.setCellValue("Approx Count");
					c.setCellStyle(csBold);
					c = row.createCell(7);
					c.setCellValue("Remarks");
					c.setCellStyle(csBold);
					c = row.createCell(8);
					c.setCellValue("Status");
					c.setCellStyle(csBold);
					c = row.createCell(9);
					c.setCellValue("Fix TimeLine");
					c.setCellStyle(csBold);
					c = row.createCell(10);
					c.setCellValue("Pending With");
					c.setCellStyle(csBold);
					c = row.createCell(11);
					c.setCellValue("Defect Id");
					c.setCellStyle(csBold);
					c = row.createCell(12);
					c.setCellValue("Ticket");
					c.setCellStyle(csBold);
					c = row.createCell(13);
					c.setCellValue("Effort");
					c.setCellStyle(csBold);
					c = row.createCell(14);
					c.setCellValue("Last Update");
					c.setCellStyle(csBold);
					IssueDetailDAO issueDetailDAO = new IssueDetailDAO();
					ArrayList<IssueDetail> issueDetaList = issueDetailDAO.fetchAllForWA();
					for (IssueDescription issueDescription : issueDescriptions) {
						IssueDetail issueDetail = new IssueDetail();
						for(IssueDetail issueDetail1 : issueDetaList){
							if(issueDetail1.getIssueDescription().getIssueId() == issueDescription.getIssueId())
								issueDetail = issueDetail1;
						}
						row = sheet.createRow(++rowIndex);
						c = row.createCell(0);
						c.setCellValue(issueDescription.getIssueLogDate());
						c.setCellStyle(cellStyle);
						c = row.createCell(1);
						c.setCellValue(issueDescription.getUpssTeam().getTeamName());
						c.setCellStyle(cs);
						c = row.createCell(2);
						c.setCellValue(issueDescription.getIssueName());
						c.setCellStyle(cs);
						c = row.createCell(3);
						c.setCellValue(issueDescription.getIssueDesc());
						c.setCellStyle(cs);
						c = row.createCell(4);
						c.setCellValue(issueDetail.getDeployed());
						c.setCellStyle(cs);
						c = row.createCell(5);
						c.setCellValue(issueDetail.getFrequency());
						c.setCellStyle(cs);
						c = row.createCell(6);
						c.setCellValue(issueDetail.getCount());
						c.setCellStyle(cs);
						c = row.createCell(7);
						c.setCellValue(issueDetail.getRemarks());
						c.setCellStyle(cs);
						c = row.createCell(8);
						c.setCellValue(issueDescription.getStatus());
						c.setCellStyle(cs);
						c = row.createCell(9);
						c.setCellValue(issueDescription.getFixTimeLine());
						c.setCellStyle(cs);
						c = row.createCell(10);
						c.setCellValue(issueDescription.getPendingWith());
						c.setCellStyle(cellStyle);
						c = row.createCell(11);
						c.setCellValue(issueDescription.getDefectId());
						c.setCellStyle(cellStyle);
						c = row.createCell(12);
						c.setCellValue(issueDetail.getTicket());
						c.setCellStyle(cellStyle);
						c = row.createCell(13);
						c.setCellValue(issueDetail.getEffort());
						c.setCellStyle(cellStyle);
						c = row.createCell(14);
						c.setCellValue(issueDescription.getSysUpdateDate());
						c.setCellStyle(cellStyle);
					}
				}
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition",
						"attachment;filename="+fileName);
				OutputStream out = response.getOutputStream();
				workbook.write(out);
				out.close();
			} catch (Exception e) {
				System.out.println(e);
			}
/*			String json = new Gson().toJson(1); // Response for export to excel
			System.out.println("Value of JSON :: "+json);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);*/
		}
	}

	public void setCellStyles(Workbook wb) {
		// font size 10
		Font f = wb.createFont();
		f.setFontHeightInPoints((short) 10);
		// Simple style
		cs = wb.createCellStyle();
		cs.setFont(f);

		// Bold Fond
		Font bold = wb.createFont();
		bold.setBoldweight(Font.BOLDWEIGHT_BOLD);
		bold.setFontHeightInPoints((short) 10);

		// Bold style
		csBold = wb.createCellStyle();
		csBold.setBorderBottom(CellStyle.BORDER_THIN);
		csBold.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		csBold.setFont(bold);

		// Setup style for Top Border Line
		csTop = wb.createCellStyle();
		csTop.setBorderTop(CellStyle.BORDER_THIN);
		csTop.setTopBorderColor(IndexedColors.BLACK.getIndex());
		csTop.setFont(f);

		// Setup style for Right Border Line
		csRight = wb.createCellStyle();
		csRight.setBorderRight(CellStyle.BORDER_THIN);
		csRight.setRightBorderColor(IndexedColors.BLACK.getIndex());
		csRight.setFont(f);

		// Setup style for Bottom Border Line
		csBottom = wb.createCellStyle();
		csBottom.setBorderBottom(CellStyle.BORDER_THIN);
		csBottom.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		csBottom.setFont(f);

		// Setup style for Left Border Line
		csLeft = wb.createCellStyle();
		csLeft.setBorderLeft(CellStyle.BORDER_THIN);
		csLeft.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		csLeft.setFont(f);

		// Setup style for Top/Left corner cell Border Lines
		csTopLeft = wb.createCellStyle();
		csTopLeft.setBorderTop(CellStyle.BORDER_THIN);
		csTopLeft.setTopBorderColor(IndexedColors.BLACK.getIndex());
		csTopLeft.setBorderLeft(CellStyle.BORDER_THIN);
		csTopLeft.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		csTopLeft.setFont(f);

		// Setup style for Top/Right corner cell Border Lines
		csTopRight = wb.createCellStyle();
		csTopRight.setBorderTop(CellStyle.BORDER_THIN);
		csTopRight.setTopBorderColor(IndexedColors.BLACK.getIndex());
		csTopRight.setBorderRight(CellStyle.BORDER_THIN);
		csTopRight.setRightBorderColor(IndexedColors.BLACK.getIndex());
		csTopRight.setFont(f);

		// Setup style for Bottom/Left corner cell Border Lines
		csBottomLeft = wb.createCellStyle();
		csBottomLeft.setBorderBottom(CellStyle.BORDER_THIN);
		csBottomLeft.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		csBottomLeft.setBorderLeft(CellStyle.BORDER_THIN);
		csBottomLeft.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		csBottomLeft.setFont(f);

		// Setup style for Bottom/Right corner cell Border Lines
		csBottomRight = wb.createCellStyle();
		csBottomRight.setBorderBottom(CellStyle.BORDER_THIN);
		csBottomRight.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		csBottomRight.setBorderRight(CellStyle.BORDER_THIN);
		csBottomRight.setRightBorderColor(IndexedColors.BLACK.getIndex());
		csBottomRight.setFont(f);
	}

}
