package pe.ctarequipa.tareo.application.port.in;

import pe.ctarequipa.tareo.application.model.MatrizTareo;

public interface ConsultarMatrizUseCase {
    MatrizTareo consultar(Long tareoId, int quincena);
}
