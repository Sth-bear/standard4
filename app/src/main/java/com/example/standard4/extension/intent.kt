package com.example.standard4.extension

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.core.os.bundleOf



inline fun <reified T : Any> Activity.extraNotNull(key:String, default: T? = null) = lazy {// activity의 extra가 있으면 -> key/ value
    val value = intent?.extras?.getLong(key) // 아래에서 집어넣은 bundleOf(*pairs)에서 key의  value를 찾는것.
    requireNotNull(if (value is T) value else default) {key} // is T << Type이 있는 경우, null이 아님. -> key   value를 할당 else -> default를 할당
}

inline fun <reified T : Any> newIntent(context: Context): Intent =   //inline -> 함수의 호출과정을 간소화시킴. 현대는 자동으로 한다고하는데 필요한가?
    Intent(context, T::class.java)                                   //reified -> Type을 any로 받는만큼 실제 타입이 뭔지 확인 -> 들어오는 타입에 따라 intent를 생성
                                                                     //Activity가 들어온다면 Activity를 타겟으로 하는 Intent를 생성함.

inline fun <reified T: Any> Context.launchActivity (
    vararg pairs: Pair<String, Any?>  //vararg -> variable number of arguments 가변값을 받을 수 있도록 함. String(key)값으로 Any?(value, null이 올 수 있음)
) {
    val intent = newIntent<T>(this) // 위의 타입에 관한 생성된 intent에
    intent.putExtras(bundleOf(*pairs)) // Extra's' intent에 bundle로 pairs의 모든 값들을 추가함(*pairs)
    startActivity(intent) //정보를 담아서 실행.
}