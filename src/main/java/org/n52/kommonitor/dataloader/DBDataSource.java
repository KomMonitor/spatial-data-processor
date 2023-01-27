package org.n52.kommonitor.dataloader;

import java.util.List;

public class DBDataSource implements FeatureDataSource{

    public static final String DATA_SOURCE_TYPE = "db";

    private String name;
    private String table;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    @Override
    public String getType() {
        return DATA_SOURCE_TYPE;
    }
}
