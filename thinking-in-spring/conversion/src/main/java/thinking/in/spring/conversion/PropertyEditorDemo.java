package thinking.in.spring.conversion;

import java.beans.PropertyEditor;

/**
 * {@link java.beans.PropertyEditor} 示例
 */
public class PropertyEditorDemo {


    public static void main(String[] args) {

        String text = "name = 杨";

        PropertyEditor propertyEditor = new StringToPropertiesPropertyEditor();

        propertyEditor.setAsText(text);

        System.out.println(propertyEditor.getValue());

        System.out.println(propertyEditor.getAsText());
    }
}
