package org.n52.kommonitor.spatialdataprocessor.util;

import org.n52.kommonitor.spatialdataprocessor.operations.OperationException;
import org.opengis.feature.Property;
import org.opengis.feature.simple.SimpleFeature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FeatureUtils {
	
	private static String SIMPLIFY_GEOMETRIES;
	
    public static String getSimplifyGeometries() {
		return SIMPLIFY_GEOMETRIES;
	}

    @Value("${kommonitor.processor.simplify-spatial-unit-geometries}")
	public void setSimplifyGeometries(String simplifyGeometries) {
    	SIMPLIFY_GEOMETRIES = simplifyGeometries;
	}

	/**
     * Gets the value from a {@link SimpleFeature} property as String
     *
     * @param feature the {@link SimpleFeature} to fetch the property from
     * @param propertyName name of the property
     * @return the value of the fetched feature property as String
     * @throws OperationException if the property value could not be parsed as String
     */
    public String getPropertyValueAsString(SimpleFeature feature, String propertyName) throws OperationException {
        Property property = getProperty(feature, propertyName);
        return String.valueOf(property.getValue());
    }

    /**
     * Gets the value from a {@link Property} as Float
     *
     * @param property the {@link Property} to fetch the value from
     * @return the value of the fetched feature property as Float
     * @throws OperationException if the property value could not be parsed as Float
     */
    public float getPropertyValueAsFloat(Property property) throws OperationException {
        try {
            return parsePropertyValueAsFloat(property.getValue());
        } catch (NumberFormatException ex) {
            throw new OperationException(String.format("Could not decode property '%s' as '%s'", property.getName().getLocalPart(), Float.class.getName()));
        }
    }

    private Property getProperty(SimpleFeature feature, String propertyName) throws OperationException {
        Property property = feature.getProperty(propertyName);
        if (property == null) {
            throw new OperationException(String.format("Property '%s' does not exist.", propertyName));
        }
        return property;
    }

    private float parsePropertyValueAsFloat(Object value) throws NumberFormatException {
        if (value instanceof Float) {
            return (Float) value;
        } else if (value instanceof String) {
            // ensure that String does not contain any comma as decimal seperator
            value = ((String) value).replace(",", ".");
            if (((String) value).isEmpty()) {
                return Float.NaN;
            }
            if (((String) value).equalsIgnoreCase("null") || ((String) value).equalsIgnoreCase("undefined")) {
                return Float.NaN;
            }
            return Float.parseFloat((String) value);
        } else if (value instanceof Number) {
            return ((Number) value).floatValue();
        } else {
            throw new NumberFormatException(String.format("No valid Float value: %s", value));
        }
    }
}
