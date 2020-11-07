package ray.mintcat.akirainteger.database

import io.izzel.taboolib.module.db.local.LocalPlayer
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.entity.Player

class DatabaseLocal : Database() {

    override fun download(player: Player): FileConfiguration {
        return LocalPlayer.get(player)
    }

    override fun upload(player: Player) {
    }
}