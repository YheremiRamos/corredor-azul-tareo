package pe.ctarequipa.tareo.application.port.in;

import pe.ctarequipa.tareo.application.port.in.command.HabilitarTareoCommand;
import pe.ctarequipa.tareo.domain.model.Tareo;

public interface HabilitarTareoUseCase {
    Tareo habilitar(HabilitarTareoCommand command);
}
