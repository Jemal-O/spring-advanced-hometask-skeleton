package ua.epam.spring.hometask.utils;

import com.lowagie.text.Document;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class PdfViewResolver extends AbstractPdfView {
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		@SuppressWarnings("unchecked")
		Map<String, String> data = (Map<String, String>) model.get("data");

		Table table = new Table(2);

		for (Map.Entry<String, String> entry : data.entrySet()) {
			table.addCell(entry.getKey());
			table.addCell(entry.getValue());
		}

		document.add(table);
	}
}
