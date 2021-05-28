package com.tw.skillapp;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.tw.skillapp");

        noClasses()
            .that()
                .resideInAnyPackage("com.tw.skillapp.service..")
            .or()
                .resideInAnyPackage("com.tw.skillapp.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..com.tw.skillapp.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
