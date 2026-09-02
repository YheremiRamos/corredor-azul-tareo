package pe.ctarequipa.tareo.infrastructure.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pe.ctarequipa.tareo.domain.exception.AreaNoAutorizadaException;

@Service
@RequiredArgsConstructor
public class AreaAuthorizationService {

    public void verificarAcceso(String areaId) {
        if (areaId == null || areaId.isBlank()) {
            return;
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !(auth.getPrincipal() instanceof JwtPrincipal principal)) {
            throw new AreaNoAutorizadaException(areaId);
        }
        if ("RRHH".equals(principal.rol())) {
            return;
        }
        if (principal.areaIds() == null || !principal.areaIds().contains(areaId)) {
            throw new AreaNoAutorizadaException(areaId);
        }
    }

    public String currentUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() instanceof JwtPrincipal principal) {
            return principal.userId();
        }
        return "system";
    }

    public Long currentUserIdLong() {
        try {
            return Long.parseLong(currentUserId());
        } catch (NumberFormatException e) {
            return 0L;
        }
    }

    public String currentUserNombre() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() instanceof JwtPrincipal principal) {
            return principal.nombre();
        }
        return "system";
    }
}
