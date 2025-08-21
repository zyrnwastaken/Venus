package cc.zyrn.venus.queue;

import cc.zyrn.venus.profile.Profile;

import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Queue {

    private final PriorityQueue<Profile> queue;

    private final String queueName, serverName;
    private final int maximumSize;

    private boolean closed;

    public Queue(String queueName, String serverName, int maximumSize) {
        this.queueName = queueName;
        this.serverName = serverName;
        this.maximumSize = maximumSize;

        this.queue = new PriorityQueue<>();

        this.closed = false;
    }

}
