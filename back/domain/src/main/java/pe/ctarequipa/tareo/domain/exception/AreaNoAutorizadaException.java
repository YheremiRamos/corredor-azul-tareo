package pe.ctarequipa.tareo.domain.exception;

public class AreaNoAutorizadaException extends RuntimeException {
    public AreaNoAutorizadaException(String areaId) {
        super("No tienes permisos para acceder al area seleccionada: " + areaId);
    }
}
