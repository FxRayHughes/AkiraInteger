package ray.mintcat.akirainteger.feed

import io.izzel.taboolib.TabooLibAPI
import io.izzel.taboolib.util.Features
import org.bukkit.entity.Player
import ray.mintcat.akirainteger.AkiraInteger

object ToolsFeed {

    //获取参数
    fun getInteger(player: Player, key: String): Any {
        val data = AkiraInteger.database().download(player)
        return data.get("AkiraInteger.${key}")
    }

    fun getInteger(player: Player, key: String, def: Any): Any {
        val data = AkiraInteger.database().download(player)
        return data.get("AkiraInteger.${key}", def)
    }

    //修改参数
    fun editInteger(player: Player, key: String, value: Any) {
        val data = AkiraInteger.database().download(player)
        val str = value.toString().replace("data", data.get("AkiraInteger.${key}").toString())
        val info = TabooLibAPI.getPluginBridge().setPlaceholders(player, str)
        data.set("AkiraInteger.${key}", (Features.compileScript(info)?.eval() ?: ""))
    }
}