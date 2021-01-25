package com.e.herolist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.e.herolist.domain.ResponseCharacterModel
import com.e.herolist.data.listener.APIListener
import com.e.herolist.data.repository.CharacterRepository
import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber


class MainViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {

    private val mCharacterRepository = CharacterRepository(application)
    private val mResponse = MutableLiveData<ResponseCharacterModel>()
    private var limit = 50
    private var offset = 0
    var characters: LiveData<ResponseCharacterModel> = mResponse

    private val job = Job()
    override val coroutineContext: CoroutineContext
    get() = Dispatchers.IO + job

    fun list() {
        launch {
            try {
                val listener = object : APIListener<ResponseCharacterModel> {
                        override fun onSuccess(model: ResponseCharacterModel) {
                            mResponse.value = model

                        }
                        override fun onFailure(str: String) {
                           // Timber.e(e)
                        }
                    }
                    limit.let { mCharacterRepository.list(listener,limit,offset) }
                    offset += limit
                } catch (e: Exception) {
                    Timber.e(e)
                    mResponse.value = null
                }

            }

        }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}