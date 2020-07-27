package thinking.in.spring.boot.configuration.manual.config;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import thinking.in.spring.boot.configuration.manual.annotation.color.Blue;
import thinking.in.spring.boot.configuration.manual.annotation.color.Green;

public class ColorImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {

        return new String[]{Blue.class.getName(), Green.class.getName()};
    }
}
