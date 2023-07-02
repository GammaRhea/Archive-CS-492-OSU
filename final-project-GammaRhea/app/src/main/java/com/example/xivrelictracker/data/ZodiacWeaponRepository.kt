package com.example.xivrelictracker.data

import com.example.xivrelictracker.api.XIVApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ZodiacWeaponRepository (
    private val service: XIVApiService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    private var currentQID: String? = null
    private var currentLang: String? = null
    private var currentcol: String? = null
    private var cachedQuest: QuestObject? = null

    suspend fun loadQuestList(
        qID: String?,
        lang: String?,
        columns: String?,
        apiKey: String
    ) : Result<QuestObject> {
        return if (qID == currentQID && lang == currentLang && columns == currentcol && cachedQuest!= null) {
            Result.success(cachedQuest!!)
        } else {
            currentQID = qID
            currentLang = lang
            currentcol = columns
            withContext(ioDispatcher) {
                try {
                    val questList = service.loadQuestList(qID, lang, columns, apiKey)
                    cachedQuest = questList
                    Result.success(questList)
                } catch (e: Exception) {
                    Result.failure(e)
                }
            }
        }
    }
}