package com.example.cooljugator

import com.example.cooljugator.entity.Verbs
import com.example.cooljugator.repository.VerbsRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.springframework.stereotype.Component

// https://stackoverflow.com/questions/35479631/how-to-use-spring-annotations-like-autowired-in-kotlin
// https://stackoverflow.com/questions/6827752/whats-the-difference-between-component-repository-service-annotations-in
@Component
class VerbsImportManager(private val verbsRepository: VerbsRepository) {

    // https://dzone.com/articles/waiting-for-coroutines
    fun manageImport() {

        val document: Document = Jsoup.connect("https://cooljugator.com").get()
        val unParsedVerbs = document.select("div#main-language-selection a")
        val languageCodesPath = unParsedVerbs.map { a -> a.attr("href").replace("/", "") }

        runBlocking {

            val re = Regex("[0-9]")

            val responses = languageCodesPath.map { languageCodePath ->
                val url = "https://cooljugator.com/$languageCodePath"

                GlobalScope.async {
                    val countryCodeDocument = Jsoup.connect(url).get()
                    val commonVerbs = countryCodeDocument.select("div.wordcloud a").map { a ->
                        val text = a.text()
                        re.replace(text, "")
                    }
                    Pair(languageCodePath, commonVerbs)
                }
            }
            responses.map { it.await() }.forEach { pair ->
                verbsRepository.save(Verbs(languageCode = pair.first, commonVerbs = pair.second.joinToString()))
            }
        }

        println("\nCommon verbs parsed successfully!")
    }
}