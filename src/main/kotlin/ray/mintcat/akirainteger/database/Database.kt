package ray.mintcat.akirainteger.database

import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.entity.Player

abstract class Database {

    abstract fun download(player: Player): FileConfiguration

    abstract fun upload(player: Player)

}