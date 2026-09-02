package pe.ctarequipa.tareo.application.port.in;

import pe.ctarequipa.tareo.application.model.AuthResult;
import pe.ctarequipa.tareo.application.port.in.command.LoginCommand;

public interface AuthUseCase {
    AuthResult login(LoginCommand command);
    void logout();
}
