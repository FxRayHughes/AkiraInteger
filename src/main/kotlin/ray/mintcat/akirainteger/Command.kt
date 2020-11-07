package ray.mintcat.akirainteger

import io.izzel.taboolib.module.command.base.*
import io.izzel.taboolib.module.db.local.Local
import io.izzel.taboolib.module.tellraw.TellrawJson
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import ray.mintcat.akirainteger.feed.ToolsFeed

@BaseCommand(name = "akirainteger", aliases = ["ai","aki","wiz","wizard"], permission = "Wizard.wizard.command")
class Command:BaseMainCommand() {

    @SubCommand
    var look: BaseSubCommand = object : BaseSubCommand() {
        override fun getDescription(): String {
            return "查看变量"
        }
        override fun getArguments(): Array<Argument> {
            return arrayOf(Argument("目标"), Argument("变量名"))
        }
        override fun onCommand(sender: CommandSender, command: Command, s: String, args: Array<String>) {
            val player = Bukkit.getPlayerExact(args[0])
            if (player == null) {
                sender.sendMessage("${AkiraInteger.titel}§7目标 §f${args[0]} §7离线.")
                return
            }
            TellrawJson.create()
                    .append("${AkiraInteger.titel}§7目标 §f${args[0]} §7的 §f${args[1]} §7为§f ${ToolsFeed.getInteger(player,args[1],"不存在")}")
                    .hoverText("${args[0]} -> ${args[1]} : ${ToolsFeed.getInteger(player,args[1],"不存在")}")
                    .send(player)
        }
    }

    @SubCommand
    var edit: BaseSubCommand = object : BaseSubCommand() {
        override fun getDescription(): String {
            return "操作变量"
        }
        override fun getArguments(): Array<Argument> {
            return arrayOf(
                    Argument("目标"),
                    Argument("变量名"),
                    Argument("公式") { listOf("请输入公式", "不可输入空格", "可以使用PAPI变量", "默认参数为data") })
        }
        override fun onCommand(sender: CommandSender, command: Command, s: String, args: Array<String>) {
            val player = Bukkit.getPlayerExact(args[0])
            if (player == null) {
                sender.sendMessage("${AkiraInteger.titel}§7目标 §f${args[0]} §7离线.")
                return
            }
            ToolsFeed.editInteger(player,args[1],args[2])
        }
    }

}