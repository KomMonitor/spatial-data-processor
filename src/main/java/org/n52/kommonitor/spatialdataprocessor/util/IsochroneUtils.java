package org.n52.kommonitor.spatialdataprocessor.util;

import com.fasterxml.jackson.databind.JsonNode;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.factory.CommonFactoryFinder;
import org.opengis.filter.Filter;
import org.opengis.filter.FilterFactory2;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;


@Component
public class IsochroneUtils {
    private static final String PROP_RANGES_PATH = "/info/query/ranges";
    private static final String PROP_VALUE_NAME = "value";

    public List<Double> getRanges(JsonNode isochroneNode) {
        String rangeValue = isochroneNode.at(PROP_RANGES_PATH).asText();
        return Arrays.stream(rangeValue.split(",")).map(Double::parseDouble).toList();
    }

    public SimpleFeatureCollection subsetRange(SimpleFeatureCollection fc, double v) {
        FilterFactory2 ff = CommonFactoryFinder.getFilterFactory2();
        double lower = v - 0.001;
        double upper = v + 0.001;
        Filter filter = ff.between(ff.property(PROP_VALUE_NAME), ff.literal(lower), ff.literal(upper));
        return fc.subCollection(filter);
    }

}