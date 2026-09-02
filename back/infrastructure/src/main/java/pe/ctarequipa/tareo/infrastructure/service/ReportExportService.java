package pe.ctarequipa.tareo.infrastructure.service;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import pe.ctarequipa.tareo.application.port.in.ExportarReporteUseCase;
import pe.ctarequipa.tareo.application.port.out.TareoRepository;
import pe.ctarequipa.tareo.domain.model.Tareo;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportExportService implements ExportarReporteUseCase {

    private final TareoRepository tareoRepository;

    @Override
    public byte[] exportar(String formato, Long periodoId, String areaId) {
        List<Tareo> tareos = tareoRepository.findAll().stream()
                .filter(t -> periodoId == null || periodoId.equals(t.periodoId()))
                .filter(t -> areaId == null || areaId.equals(t.areaId()))
                .toList();

        return switch (formato.toLowerCase()) {
            case "pdf" -> exportPdf(tareos);
            case "txt" -> exportTxt(tareos);
            default -> exportExcel(tareos);
        };
    }

    private byte[] exportExcel(List<Tareo> tareos) {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Tareo");
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("ID");
            header.createCell(1).setCellValue("Periodo");
            header.createCell(2).setCellValue("Area");
            header.createCell(3).setCellValue("Estado Q1");
            header.createCell(4).setCellValue("Estado Q2");
            int rowNum = 1;
            for (Tareo t : tareos) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(t.id());
                row.createCell(1).setCellValue(t.periodoId());
                row.createCell(2).setCellValue(t.areaId());
                row.createCell(3).setCellValue(t.estadoQ1());
                row.createCell(4).setCellValue(t.estadoQ2());
            }
            workbook.write(out);
            return out.toByteArray();
        } catch (Exception e) {
            throw new IllegalStateException("Error generando Excel", e);
        }
    }

    private byte[] exportPdf(List<Tareo> tareos) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Document document = new Document();
            PdfWriter.getInstance(document, out);
            document.open();
            document.add(new Paragraph("Reporte de Tareo - FATIMA"));
            for (Tareo t : tareos) {
                document.add(new Paragraph("Tareo " + t.id() + " | Area " + t.areaId()
                        + " | Q1:" + t.estadoQ1() + " Q2:" + t.estadoQ2()));
            }
            document.close();
            return out.toByteArray();
        } catch (Exception e) {
            throw new IllegalStateException("Error generando PDF", e);
        }
    }

    private byte[] exportTxt(List<Tareo> tareos) {
        StringBuilder sb = new StringBuilder("Reporte de Tareo - FATIMA\n");
        for (Tareo t : tareos) {
            sb.append("Tareo ").append(t.id())
                    .append(" | Area ").append(t.areaId())
                    .append(" | Q1:").append(t.estadoQ1())
                    .append(" Q2:").append(t.estadoQ2())
                    .append('\n');
        }
        return sb.toString().getBytes(StandardCharsets.UTF_8);
    }
}
