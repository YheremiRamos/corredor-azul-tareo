package pe.ctarequipa.tareo.application.port.out;

import pe.ctarequipa.tareo.application.model.Usuario;

public interface TokenGenerator {
    String generar(Usuario usuario);
}
