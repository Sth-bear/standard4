package com.example.standard4

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.standard4.data.CardInfo
import com.example.standard4.data.DataSource

class CardViewModel(val dataSource: DataSource): ViewModel() {
    val cardLiveData = dataSource.getCardList()

    fun getCardForId(id:Long):CardInfo {
//        return cardLiveData[id.toInt()] //실제 id값으로 하고 싶다면 ->
        cardLiveData.let {card ->
            return card.first{it.id == id}
        }
    }
}

class CardViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CardViewModel::class.java)) {
            return  CardViewModel(dataSource = DataSource.getDataSource()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}