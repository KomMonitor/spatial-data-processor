package org.n52.kommonitor.spatialdataprocessor.util;

import org.n52.kommonitor.spatialdataprocessor.util.datamanagement.DataManagementClient;
import org.springframework.stereotype.Component;

/**
 * Class encapsulating all supportive Beans that can be accessed by a Process
 */
@Component
public class ProcessorUtils {

    private DataManagementClient dataManagementClient;

    public ProcessorUtils(DataManagementClient dataManagementClient) {
        this.dataManagementClient = dataManagementClient;
    }

    public DataManagementClient dataMangementClient() {
        return dataManagementClient;
    }
}
