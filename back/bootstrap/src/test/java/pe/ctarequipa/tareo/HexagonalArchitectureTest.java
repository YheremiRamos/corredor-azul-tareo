package pe.ctarequipa.tareo;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.jupiter.api.Test;

class HexagonalArchitectureTest {

    private static final String BASE = "pe.ctarequipa.tareo";

    @Test
    void domainNoDependeDeInfraestructuraNiSpring() {
        JavaClasses classes = new ClassFileImporter().importPackages(BASE + ".domain");
        var rule = ArchRuleDefinition.noClasses()
                .that().resideInAPackage(BASE + ".domain..")
                .should().dependOnClassesThat().resideInAnyPackage(
                        "org.springframework..",
                        "jakarta.persistence..",
                        "lombok..",
                        BASE + ".infrastructure..",
                        BASE + ".application.."
                );
        rule.check(classes);
    }

    @Test
    void applicationNoDependeDeInfraestructura() {
        JavaClasses classes = new ClassFileImporter().importPackages(BASE + ".application");
        var rule = ArchRuleDefinition.noClasses()
                .that().resideInAPackage(BASE + ".application..")
                .should().dependOnClassesThat().resideInAPackage(BASE + ".infrastructure..");
        rule.check(classes);
    }
}
