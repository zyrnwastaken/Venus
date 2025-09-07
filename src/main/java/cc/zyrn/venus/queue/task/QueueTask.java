package cc.zyrn.venus.queue.task;

import cc.zyrn.venus.Venus;
import cc.zyrn.venus.queue.QueueHandler;
import lombok.AllArgsConstructor;
import org.bukkit.scheduler.BukkitRunnable;

@AllArgsConstructor
public class QueueTask extends BukkitRunnable {

    private final QueueHandler queueHandler;
    private final Venus venus;

    @Override
    public void run() {
        queueHandler.getQueueMap().values().forEach(queue -> {
            if (queue.isClosed() || queue.getQueue().isEmpty()) return;

            queue.moveQueue(venus);
        });
    }
}
