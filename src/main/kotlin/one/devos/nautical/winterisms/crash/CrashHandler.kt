package one.devos.nautical.winterisms.crash

import com.google.gson.JsonParser
import com.google.gson.stream.JsonReader
import net.minecraft.CrashReport
import net.minecraft.ReportType
import net.minecraft.Util
import one.devos.nautical.winterisms.Winterisms
import one.devos.nautical.winterisms.config.Config
import java.awt.Desktop
import java.lang.StringBuilder
import java.net.URI
import java.net.URLEncoder
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.nio.charset.StandardCharsets

/**
 * Originally written by Maximumpower55, as a part of the TeaBridge project, adapted for my usage
 *
 * src: https://github.com/devOS-Sanity-Edition/TeaBridge/blob/main/src/main/java/one/devos/nautical/teabridge/util/CrashHandler.java
 *
 * License: MIT
 */
object CrashHandler {
    var didGameCrash: Boolean = false
    val mclogsEndpoint: URI = URI.create("https://api.mclo.gs/1/log")
    val httpClient = HttpClient.newHttpClient()
    var crashLogLink = ""

    fun oops(crash: CrashReport) {
        didGameCrash = true
        if (Config.uploadCrashToMCLogs) {
            try {
                val response: HttpResponse<String> = httpClient.send(HttpRequest.newBuilder(mclogsEndpoint)
                    .POST(HttpRequest.BodyPublishers.ofString("content=${URLEncoder.encode(crash.getFriendlyReport(ReportType.CRASH),
                        StandardCharsets.UTF_8)}"))
                    .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                    .build(),
                    HttpResponse.BodyHandlers.ofString())

                if (response.statusCode() != 200) {
                    throw Exception("Non-success status code from request $response")
                }

                val responseBodyParsing = JsonParser.parseString(response.body()).asJsonObject
                val responseBodyParsedUrl = responseBodyParsing.get("url").asString

                crashLogLink = responseBodyParsedUrl

                Winterisms.LOGGER.error("[Winterisms] ${Config.modpackTitle} crashed! Log uploaded: $responseBodyParsedUrl")

                if (Config.openBrowserOnGameCrash) openBrowser(URI.create(responseBodyParsedUrl))
            } catch (ex: Exception) {
                Winterisms.LOGGER.error("[Winterisms] Crash report has failed to upload ${ex}")
            }
        }
    }

    fun openBrowser(crashLink: URI) {
        try {
            Util.getPlatform().openUri(crashLink)
        } catch (ex: Exception) {
            Winterisms.LOGGER.error("[Winterisms] Failed to open crash log in browser! ${ex.message}")
        }
    }

    fun gAIDeterrent(stringBuilder: StringBuilder, user: String, ai: String): StringBuilder {
        if (Config.gAIDeterrent) {
            return stringBuilder
                .append("---------------------------------------------------------------------------------------")
                .append("\n\n")
                .append(user)
                .append("\n")
                .append("---------------------------------------------------------------------------------------")
                .append("\n\n")
                .append(ai)
                .append("\n")
        }

        return stringBuilder.append("")
    }

    fun uhString(stringBuilder: StringBuilder): StringBuilder {
        if (Config.uploadCrashToMCLogs) {
            stringBuilder
                .append("---------------------------------------------------------------------------------------")
                .append("\n\n")
                .append("Anyway, please provide the link shown on the top of your browser to a developer or the pack creator!")
                .append("\n\n")
                .append("---------------------------------------------------------------------------------------")
                .append("\n\n")
        }

        return stringBuilder.append("")
    }
}