package com.yusufsezer.view;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.yusufsezer.projection.IViewInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.awt.Color;
import java.util.Map;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.web.servlet.view.document.AbstractPdfView;

public class PDFView extends AbstractPdfView {

    private final MessageSourceAccessor messageSourceAccessor;

    public PDFView(MessageSourceAccessor messageSourceAccessor) {
        this.messageSourceAccessor = messageSourceAccessor;
    }

    @Override
    protected void buildPdfDocument(Map<String, Object> model,
            Document document,
            PdfWriter writer,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {

        Iterable<IViewInfo> views = (Iterable<IViewInfo>) model.get("people");

        document.setMargins(0f, 0f, 0f, 0f);
        document.addAuthor("Yusuf Sezer(www.yusufsezer.com)");
        document.addCreationDate();
        document.addCreator("www.yusufsezer.com");

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setSpacingAfter(10f);
        table.setSpacingBefore(10f);
        table.setTotalWidth(PageSize.A4.getWidth());

        String name = messageSourceAccessor.getMessage("site.admin.page.bio.name");
        String profession = messageSourceAccessor.getMessage("site.admin.page.bio.profession");
        String date = messageSourceAccessor.getMessage("site.admin.page.bio.date");
        String status = messageSourceAccessor.getMessage("site.admin.page.bio.status");
        addRow(table, name, profession, date, status);

        for (IViewInfo view : views) {
            name = view.fullName();
            profession = view.getProfession();
            date = String.valueOf(view.getCreatedDate());
            status = String.valueOf(view.getStatus());
            addRow(table, name, profession, date, status);
        }

        document.add(table);
    }

    private void addRow(PdfPTable table, String... cell) {
        for (int i = 0, len = cell.length; i < len; i++) {
            table.addCell(newCell(cell[i]));
        }
    }

    private PdfPCell newCell(String text) {
        PdfPCell pdfPCell = new PdfPCell(new Paragraph(text));
        pdfPCell.setBorderColor(Color.BLACK);
        pdfPCell.setPadding(5f);
        return pdfPCell;
    }

}
