package pe.ctarequipa.tareo.application.port.in;

import pe.ctarequipa.tareo.application.port.in.command.CulminarQuincenaCommand;
import pe.ctarequipa.tareo.domain.model.Tareo;

public interface CulminarQuincenaUseCase {
    Tareo culminar(CulminarQuincenaCommand command);
}
