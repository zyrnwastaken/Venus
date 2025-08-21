package cc.zyrn.venus.queue;

import cc.zyrn.venus.Venus;
import cc.zyrn.venus.queue.priority.Priority;
import org.bukkit.entity.Player;

import java.util.*;

public class QueueHandler {

    private final Venus venus;

    private final List<Priority> priorities;
    private final Map<String, Queue> queueMap;

    private Priority defaultPriority;

    public QueueHandler(Venus venus) {
        this.venus = venus;

        this.priorities = new ArrayList<>();
        this.queueMap = new HashMap<>();

        this.loadPriorities();
    }

    private void loadPriorities() {
        int defaultPriority = venus.getConfig().getInt("default-priority");

        if (defaultPriority == -1) {
            if (priorities.isEmpty()) {
                defaultPriority = 0;
            } else {
                priorities.sort(Comparator.comparing(Priority::priority));
                defaultPriority = priorities.getLast().priority();
            }
        }

        this.defaultPriority = new Priority("Default", "queue.default", defaultPriority);
        this.priorities.add(this.defaultPriority);
    }

    public final Priority getPriority(Player player) {
        return priorities.stream().filter(priority -> player.hasPermission(priority.permission()))
                .findAny().orElse(defaultPriority);

    }

}
