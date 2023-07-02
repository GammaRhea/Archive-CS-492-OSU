package com.example.xivrelictracker.ui

import androidx.lifecycle.*
import com.example.xivrelictracker.api.XIVApiService
import com.example.xivrelictracker.data.QuestObject
import com.example.xivrelictracker.data.ZodiacWeaponRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

class ZodiacWeaponViewModel : ViewModel() {
    private val repository = ZodiacWeaponRepository(XIVApiService.create())

    private val _questList = MutableLiveData<QuestObject?>(null)
    val questList: LiveData<QuestObject?> = _questList

    private val _mQuestList = MutableLiveData<List<QuestObject?>>(null)
    val mQuestList: LiveData<List<QuestObject?>> = _mQuestList

    private val _error = MutableLiveData<Throwable?>(null)
    val error: LiveData<Throwable?> = _error

    fun loadQuestList(qID: String?, lang: String?, columns: String?, apiKey: String) {
        viewModelScope.launch {
            val result = repository.loadQuestList(qID, lang, columns, apiKey)
            _error.value = result.exceptionOrNull()
            _questList.value = result.getOrNull()
        }
    }

//    fun loadMultiQuestList(qIDs: List<String?>, lang: String?, columns: String?, apiKey: String) = liveData<List<QuestObject>> {
//        viewModelScope.launch {
//            val result = qIDs.map { quest ->
//                async { repository.loadQuestList(quest, lang, columns, apiKey) }
//            }.awaitAll().filterNotNull()
//        }
//    }
}