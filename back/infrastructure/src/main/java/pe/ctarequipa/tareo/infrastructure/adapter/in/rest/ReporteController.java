package pe.ctarequipa.tareo.infrastructure.adapter.in.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pe.ctarequipa.tareo.application.port.in.ExportarReporteUseCase;
import pe.ctarequipa.tareo.application.port.in.ListarReportesUseCase;
import pe.ctarequipa.tareo.application.model.ReporteItem;
import pe.ctarequipa.tareo.infrastructure.adapter.in.rest.dto.ReporteResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reportes")
@RequiredArgsConstructor
public class ReporteController {

    private final ListarReportesUseCase listarReportesUseCase;
    private final ExportarReporteUseCase exportarReporteUseCase;

    @GetMapping
    public List<ReporteResponse> listar() {
        return listarReportesUseCase.listar().stream().map(this::toResponse).toList();
    }

    @GetMapping("/export")
    public ResponseEntity<byte[]> exportar(
            @RequestParam String formato,
            @RequestParam(required = false) Long periodoId,
            @RequestParam(required = false) String areaId) {
        byte[] content = exportarReporteUseCase.exportar(formato, periodoId, areaId);
        String mediaType = switch (formato.toLowerCase()) {
            case "pdf" -> "application/pdf";
            case "txt" -> "text/plain";
            default -> "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
        };
        String extension = switch (formato.toLowerCase()) {
            case "pdf" -> "pdf";
            case "txt" -> "txt";
            default -> "xlsx";
        };
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reporte." + extension)
                .contentType(MediaType.parseMediaType(mediaType))
                .body(content);
    }

    private ReporteResponse toResponse(ReporteItem item) {
        return new ReporteResponse(item.id(), item.nombre());
    }
}
