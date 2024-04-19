package com.yusufsezer.view;

import java.util.Map;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.web.servlet.view.document.AbstractXlsxView;
import com.yusufsezer.projection.IViewInfo;

public class ExcelView extends AbstractXlsxView {

    MessageSourceAccessor messageSourceAccessor;

    public ExcelView(MessageSourceAccessor messageSourceAccessor) {
        this.messageSourceAccessor = messageSourceAccessor;
    }

    @Override
    protected void buildExcelDocument(Map<String, Object> model,
            Workbook workbook,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {

        Iterable<IViewInfo> views = (Iterable<IViewInfo>) model.get("people");

        String pageTitle = messageSourceAccessor.getMessage("site.admin.page.bio");
        Sheet sheet = workbook.createSheet(pageTitle);
        sheet.setFitToPage(true);

        int rowNum = 0;
        Row row = sheet.createRow(rowNum++);
        String name = messageSourceAccessor.getMessage("site.admin.page.bio.name");
        String profession = messageSourceAccessor.getMessage("site.admin.page.bio.profession");
        String date = messageSourceAccessor.getMessage("site.admin.page.bio.date");
        String status = messageSourceAccessor.getMessage("site.admin.page.bio.status");
        addRow(row, name, profession, date, status);

        for (IViewInfo view : views) {
            row = sheet.createRow(rowNum++);
            name = view.fullName();
            profession = view.getProfession();
            date = String.valueOf(view.getCreatedDate());
            status = String.valueOf(view.getStatus());
            addRow(row, name, profession, date, status);
        }

    }

    private void addRow(Row row, String... cell) {
        for (int i = 0, len = cell.length; i < len; i++) {
            row.createCell(i).setCellValue(cell[i]);
        }
    }

}
