package cc.zyrn.venus.queue;

import cc.zyrn.venus.Venus;
import cc.zyrn.venus.profile.Profile;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.PriorityQueue;

@Setter
@Getter
public class Queue {

    private final PriorityQueue<Profile> queue;

    private final String queueName, serverName, displayName;
    private final int maximumSize, slot;

    private boolean closed;

    public Queue(String queueName, String serverName, String displayName, int maximumSize, int slot, boolean closed) {
        this.queueName = queueName;
        this.displayName = displayName;
        this.serverName = serverName;
        this.maximumSize = maximumSize;

        this.queue = new PriorityQueue<>();
        this.slot = slot;
        this.closed = closed;
    }

    public final void moveQueue(Venus venus) {
        final Profile profile = queue.poll();

        if (profile == null)
            return;

        final Player player = Bukkit.getPlayer(profile.getUuid());

        if (player == null)
            return;

        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(serverName);

        player.sendPluginMessage(venus, "BungeeCord", out.toByteArray());
    }

}
