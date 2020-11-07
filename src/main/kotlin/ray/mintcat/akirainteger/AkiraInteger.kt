package ray.mintcat.akirainteger

import io.izzel.taboolib.loader.Plugin
import io.izzel.taboolib.module.config.TConfig
import io.izzel.taboolib.module.inject.TInject
import ray.mintcat.akirainteger.database.DatabaseLocal
import ray.mintcat.akirainteger.database.DatabaseMongodb

object AkiraInteger : Plugin() {

    @TInject(value = ["settings.yml"], locale = "LOCALE-PRIORITY")
    lateinit var settings: TConfig
        private set

    val database = {
        when(settings.getString("Database.Type")){
            "Local" -> DatabaseLocal()
            "Mongodb" -> DatabaseMongodb()
            else -> DatabaseLocal()
        }
    }

    val titel = settings.getStringColored("Plugin.Title")
}