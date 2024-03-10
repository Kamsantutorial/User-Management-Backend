package com.backend.internal.usermanagement.common.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.backend.internal.usermanagement.entity.base.BaseEntity;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ListUtil {

    public <T> List<List<T>> splitIntoBatches(List<T> dataList, int batchSize) {
        List<List<T>> batches = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i += batchSize) {
            int endIndex = Math.min(i + batchSize, dataList.size());
            batches.add(dataList.subList(i, endIndex));
        }
        return batches;
    }

    public <T extends BaseEntity<Long>> Long findLastId(List<T> data) {
        // If passed entry is null or empty, return 0 ( This handles the first iteration
        // case )
        if (data == null || data.isEmpty())
            return 0L;

        // Do the logic to sort the customers list by customer_id of each
        // Customer object
        // Return the last entry
        return data.get(data.size() - 1).getId();
    }

    public <T> List<T> removeDuplicateItems(List<T> data) {
        Set<T> set = new HashSet<>(data);
        data.clear();
        data.addAll(set);
        return data;
    }
}
