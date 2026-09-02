package pe.ctarequipa.tareo.application.port.in;

import pe.ctarequipa.tareo.application.port.in.command.CrearPeriodoCommand;
import pe.ctarequipa.tareo.domain.model.Periodo;

public interface CrearPeriodoUseCase {
    Periodo crear(CrearPeriodoCommand command);
}
