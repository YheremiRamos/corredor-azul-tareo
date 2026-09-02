package pe.ctarequipa.tareo.application.port.out;

public interface Notificador {
    void enviar(String destinatario, String asunto, String cuerpo);
}
