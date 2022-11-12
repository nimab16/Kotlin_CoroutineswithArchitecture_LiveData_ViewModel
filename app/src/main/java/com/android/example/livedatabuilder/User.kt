package com.android.example.livedatabuilder

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import io.realm.kotlin.types.RealmObject
import kotlinx.coroutines.flow.map

class User: RealmObject {
    var name: String = ""
}


class LoginViewModel : ViewModel() {
    private val realm: Realm = Realm.open(RealmConfiguration.create(setOf(User::class)))

    init {
        realm.writeBlocking {
            copyToRealm(User().apply { name = "Nima Barati" })
        }
    }
    //    val user = MutableLiveData<User>()
//    val user: LiveData<User> = liveData {
    val user: LiveData<User?> = realm.query<User>().first().asFlow().map { it.obj }.asLiveData()
//        return u
////    emit(User().apply { name = "Foo Bar" })
//    }
}