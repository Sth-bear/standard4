package com.example.standard4.data

class DataSource {
    companion object { // -> class안에 상속시킴 -> companion object로 싱글톤영역 생성
        private var INSTANCE: DataSource? = null //아래 과정으로 이미 instance가 있는지? + 대문자선언 -> 상수선언
        fun getDataSource(): DataSource {
            return synchronized(DataSource::class) { //synchronized -> 타에서 접근제한. 동시성 문제를 해결
                val newInstance =
                    INSTANCE ?: DataSource() // 기존  Instance가 있다면 활용, 없다면 datasource 객체를 생성
                INSTANCE = newInstance //새로 생성된 값을 할당함
                newInstance //생성된 dataSource객체를 반환함
            }
        }
    }

    fun getCardList(): List<CardInfo> { // cardInfo(데이터 틀) 형식으로 리턴함
        return cardList() // cardList안의 값들을 -> cardList : 카드정보들을 리턴하는 함수
    }

//    fun getCardForID(id: Long): CardInfo {  //id( cardInfo안의 값들을 호출해서 id가 같다면 cards에 담아서 리턴한다.
//        cardList().let { cards ->
//            return cards.first { it.id == id } //.first -> null을 배출할 수 없음. 안전할때만 사용할 것. -> .find사용시 null처리를 했던것을 생각.
//        }
//    }
}