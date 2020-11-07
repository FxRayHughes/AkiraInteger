package ray.mintcat.akirainteger.database

import io.izzel.taboolib.cronus.bridge.CronusBridge
import io.izzel.taboolib.cronus.bridge.database.IndexType
import io.izzel.taboolib.kotlin.Tasks
import io.izzel.taboolib.module.nms.NMS
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.entity.Player
import ray.mintcat.akirainteger.AkiraInteger

class DatabaseMongodb:Database() {

    private val collection = CronusBridge.get(
            AkiraInteger.settings.getString("Database.LinkURL.client"),
            AkiraInteger.settings.getString("Database.LinkURL.database"),
            AkiraInteger.settings.getString("Database.LinkURL.collection"),
            IndexType.UUID)!!

    override fun download(player: Player): FileConfiguration {
        return collection.get(player.uniqueId.toString()).run {
            if (this.contains("username")) {
                this.set("username", player.name)
            }
            this
        }
    }

    override fun upload(player: Player) {
        if (NMS.handle().isRunning) {
            Tasks.task(true) {
                collection.update(player.uniqueId.toString())
            }
        } else {
            collection.update(player.uniqueId.toString())
        }
    }
}