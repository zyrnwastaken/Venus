package cc.zyrn.venus.queue;

import cc.zyrn.venus.Venus;
import cc.zyrn.venus.queue.priority.Priority;
import cc.zyrn.venus.queue.task.QueueTask;
import lombok.Getter;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.*;

@Getter
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
        this.loadQueues();

        venus.getServer().getScheduler().runTaskTimer(venus, new QueueTask(this, venus), 20L, 20L);
    }

    private void loadQueues() {
        final FileConfiguration queueFile = venus.getPriorityFile().getConfig();

        queueFile.getConfigurationSection("queues").getKeys(false).forEach(s -> {
            final ConfigurationSection configurationSection = queueFile.getConfigurationSection("queues." + s);

            if (configurationSection == null)
                return;

            final String queueName = configurationSection.getName();
            queueMap.put(queueName, new Queue(queueName, configurationSection.getString("server-name"),
                    configurationSection.getString("display-name"),
                    configurationSection.getInt("maximum-size"),
                    configurationSection.getInt("selector-slot"),
                    configurationSection.getBoolean("start-closed")));
        });
    }

    private void loadPriorities() {
        final FileConfiguration priorityFile = venus.getPriorityFile().getConfig();

        priorityFile.getConfigurationSection("priorities").getKeys(false).forEach(s -> {
            priorities.add(new Priority(s, "queue." + s, priorityFile.getInt("priorities." + s)));
        });

        int defaultPriority = priorityFile.getInt("default-priority");

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

    public final Optional<Queue> getQueue(int slot) {
        return queueMap.values().stream().filter(queue -> queue.getSlot() == slot).findFirst();
    }

    public final Priority getPriority(Player player) {
        return priorities.stream().filter(priority -> player.hasPermission(priority.permission()))
                .findAny().orElse(defaultPriority);
    }

}
