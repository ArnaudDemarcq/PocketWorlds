mvn clean install checkstyle:checkstyle-aggregate javadoc:aggregate pmd:pmd -Dcheckstyle.config.location=checkstyle_config.xml -Dcheckstyle.suppressions.location=checkstyle_suppression.xml
