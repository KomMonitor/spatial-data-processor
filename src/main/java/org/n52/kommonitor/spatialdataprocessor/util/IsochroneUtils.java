package org.n52.kommonitor.spatialdataprocessor.util;

import com.fasterxml.jackson.databind.JsonNode;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.factory.CommonFactoryFinder;
import org.n52.kommonitor.spatialdataprocessor.operations.OperationException;
import org.opengis.filter.Filter;
import org.opengis.filter.FilterFactory2;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Component
public class IsochroneUtils {
    private static final String PROP_RANGES_PATH = "/metadata/query/range";
    private static final String PROP_VALUE_NAME = "value";

    public List<Double> getRanges(JsonNode isochroneNode) throws OperationException {
        JsonNode rangeValueNode = isochroneNode.at(PROP_RANGES_PATH);

        if (rangeValueNode == null || rangeValueNode.isMissingNode() || rangeValueNode.isEmpty() || !rangeValueNode.isArray()) {
            throw new OperationException("Isochrones FeatureCollection does not contain a 'range' field.");
        } else {
            try {
                List<Double> ranges = new ArrayList<>();
            	Iterator<JsonNode> rangeIterator = rangeValueNode.elements();
        		
        		while(rangeIterator.hasNext()) {
        			double value= rangeIterator.next().asDouble();
        			ranges.add(value);
        		}
        		
        		return ranges;
            } catch (NumberFormatException e) {
                throw new OperationException("Could not determine isochrone ranges.", e);
            }
        }
    }

    public SimpleFeatureCollection subsetRange(SimpleFeatureCollection fc, double v) {
        FilterFactory2 ff = CommonFactoryFinder.getFilterFactory2();
        double lower = v - 0.001;
        double upper = v + 0.001;
        Filter filter = ff.between(ff.property(PROP_VALUE_NAME), ff.literal(lower), ff.literal(upper));
        return fc.subCollection(filter);
    }

}