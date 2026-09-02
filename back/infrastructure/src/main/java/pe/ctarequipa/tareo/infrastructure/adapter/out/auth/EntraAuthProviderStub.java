package pe.ctarequipa.tareo.infrastructure.adapter.out.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Stub para integracion futura con Microsoft Entra ID.
 */
@Slf4j
@Component
public class EntraAuthProviderStub {

    public boolean isEnabled() {
        log.debug("Entra ID auth stub: no implementado");
        return false;
    }
}
