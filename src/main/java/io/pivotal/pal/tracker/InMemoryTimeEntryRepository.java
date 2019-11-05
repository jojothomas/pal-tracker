package io.pivotal.pal.tracker;

import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class InMemoryTimeEntryRepository implements  TimeEntryRepository {

    private HashMap<Long, TimeEntry> timeEntryHashMap = new HashMap<Long,TimeEntry>();

    private long currentId = 1L;

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        Long id = currentId++;

        TimeEntry timeEntry1 = new TimeEntry(
                id,
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate(),
                timeEntry.getHours());

        timeEntryHashMap.put(id,timeEntry1);
        return timeEntry1;
    }

    @Override
    public TimeEntry find(Long id) {
         return timeEntryHashMap.get(id);
    }

    @Override
    public List<TimeEntry> list() {
        List<TimeEntry> timeEntryList = new ArrayList<TimeEntry>();
        timeEntryList.addAll(timeEntryHashMap.values());
        return timeEntryList;
    }

    @Override
    public TimeEntry update(Long id, TimeEntry timeEntry) {
        TimeEntry timeEntry1 = timeEntryHashMap.get(id);
        if(timeEntry1 != null) {
            timeEntry.setId(id);
            timeEntryHashMap.replace(id,timeEntry);
            return timeEntry;
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        timeEntryHashMap.remove(id);
    }
}
