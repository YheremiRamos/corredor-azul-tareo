package pe.ctarequipa.tareo.application.port.in;

import pe.ctarequipa.tareo.application.port.in.command.GuardarAsistenciasCommand;

public interface GuardarAsistenciasUseCase {
    void guardar(GuardarAsistenciasCommand command);
}
