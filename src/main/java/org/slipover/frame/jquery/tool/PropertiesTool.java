package org.slipover.frame.jquery.tool;

import org.slipover.frame.jquery.core.SpringContainer;

import java.util.function.Supplier;

public interface PropertiesTool {

    default String getValue(String name) {
        return SpringContainer.getValue(name);
    }

    default String getValue(String name, String defaultValue) {
        String value = getValue(name);
        if (value == null) {
            value = defaultValue;
        }
        return value;
    }

    default String getValue(String name, Supplier<String> defaultValue) {
        String value = getValue(name);
        if (value == null) {
            value = defaultValue.get();
        }
        return value;
    }

}
