package com.example.edittextinrecyclerview

import androidx.lifecycle.ViewModel
import com.example.edittextinrecyclerview.model.Person
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _personList: MutableStateFlow<List<Person>> = MutableStateFlow(emptyList())
    val personList = _personList.asStateFlow()

    var count = 0

    fun addPerson() {
        val newPerson = Person("이호성$count", "${count}번째 사용자")
        count +=1
        _personList.value += newPerson
    }

    fun removePerson(position: Int){
        val list = _personList.value.toMutableList()
        list.removeAt(position)
        _personList.value = list
    }
}