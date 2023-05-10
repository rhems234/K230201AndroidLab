// 최상위 영역
// java : int num = 1;
// 코트린 : val(or var) 변수명 : 타입 = 값
// 깃 사용시 주의사항.
// 1. 학원, 2.집, 3. 깃 원격지
// 항상 어디가 최신인지 알아야함.
val num : Int = 1
// 우리가 왜 IDE를 사용하나요?
// 편의성. 기본적인 문법 체크를 해줍니다.
// 문법에 다 외울려고 안했으면 합니다.
// 최소한 기본 문법 정도만 알고, 진행하자.
// 통계적으로 접근해라. -> 일단, 많이 자주 사용하는 것부터 시작.
// 최상위 영역에서는 선언만 하면 오류가 난다.
// 일단 IDE 문법 체크를 최대한 이용하자
// val num2 : String;
class test {
}

fun main() {
    var data12 = arrayOf<Int>(1,2,3)
    for((index, value) in data12.withIndex()) {
        print("인덱스의 값 : ")
        println(index)
        print("value의 값 : ")
        println(value)
        if(index !== data12.size-1) {
            print(",")
        }
    }
    println()

    //------------------------------------------------------------------
    //반복문 활용해보기
    fun sum10 ():Int {
        var result = 0
        // in 1..10, in 1 until 10, in 1..10 step 2, i in 10 downTo 1
        for (i in 10 downTo  1 step  2) {
            val sum = 0
            var result = sum + i
            println("반복문의 result의 값은 : $result")
        }
        return result
    }
    sum10()

    //반복문 활용해보기
    var data11 = arrayOf<Int>(1,2,3)
    for(i in data11.indices) {
        print(data11[i])
        if(i !== data11.size-1) {
            print(",")
        }
    }
    println()
    //=========================================================
    var data10 = 5
    val result10 = when{
        // is 해당 타입이 맞는지 확인.
        data10 < 10 -> "data10의 값은 문자열"
        else -> {
            "data10 의 값은 ??"
        }
    }
    println("data10 조건으로 result10 출력하기 : $result10")

//=====================================================================
    var data9 : Any =  5
    when (data9) {
        // is 해당 타입이 맞는지 확인.
        is String -> println("data9의 값은 문자열 : $data9")
        "10" -> println("data8 is 10")
        in 1 .. 10 -> println("data9의 값은 숫자 : $data9")
        "abc" -> println("data8 is abc")
        else -> {
            println("data is not valid data9")
        }
    }

    //=================================================================
    var data8 = "abc"
    when (data8) {
        "10" -> println("data8 is 10")
        "abc" -> println("data8 is abc")
        else -> {
            println("data is not valid data8")
        }
    }
    var data6 = 10
    when (data6) {
        10 -> println("data6 is 10")
        20 -> println("data6 is 20")
        else -> {
            println("data is not valid data6")
        }
    }

    //==============================================================
    var data5 = 10
    var result = if(data5 > 0) {
        println("data > 0")
        true
    } else {
        println("data <= 0")
        false
    }
    println(result)

    //============================================================
    var map = mapOf<String, String>(Pair("one", "hello"), "two" to "2")
    println("""
       map size : ${map.size}
       map data 인덱스 3 : ${map.get("one")}, ${map.get("two")}
    """.trimIndent())

    //===================================================================
    // 가변리스트 확인
    var mL = mutableListOf<Int>(1,2,3)
    mL.add(3, 100)
    println("""
       mL size : ${mL.size}
       mL data 인덱스 3 : ${mL[3]}
    """.trimIndent())

    var list = listOf<Int>(1,2,3)
    // 불변 리스트 변경 불가.
    //list[0] = 100
    println("""
       list size : ${list.size}
       list data : ${list[0]}, ${list[1]}, ${list[2]}
    """.trimIndent())

    //=====================================================================
    var data4 = intArrayOf(1,2,3)
    val data3 = arrayOf<Int>(1,2,3)

    var data2 : IntArray = IntArray(3, {0})
    data2[0] = 100
    println("data1의 값 조회 : ${data2[0]}")
//========================================================================
    // 배열 -> 자바 : 동일한 데이터 타입의 값들을 할당함.
    // 비교 vs 자바스크립트 : 여러 가지의 데이터 타입의 값들을 할당함.
    // Array(배열의 갯수, 초깃값
    // 람다식은 문법이 : { 매개변수 -> 실행될 문장 }
    // 람다식에서 매개변수가 1개면 화살표, 매개변수를 생략
    // : { 실행될 문장. }
    val data1:Array<Int> = Array(3, { 0 })
    println("data1의 값 조회 : ${data1[0]}")
    data1[0] = 10
    data1[1] = 20
    data1.set(2, 30)

    println("""
        array size : ${data1.size}
        array data : ${data1[0]}, ${data1[1]}, ${data1.get(2)}
    """
    )
    //==================================================================
    // 함수의 매개변수에서는 var, val 키워드 사용하면 안됌.
    // 자동으로 val가 들어가 있다.
    fun sum3(no:Int, no2:Int) { // Unit, 자바 : void 생략
        val result = no + no2
        println("no + no2 = $result")
    }

//======================================================================
    // 함수의 결과값의 타입을 Nothing
    fun some1():Nothing? { // ?는 null 혀용이 가능한 연산자.
        return null
    }

    // -----------------------------------------------------------------
    val num3 : Any = "이상용" // Any, Object와 비슷.
    fun sum2(no:Int, no2:Int) { // Unit, 자바 : void 생략
        val result = no + no2
        println("no + no2 = $result")
    }
    sum2(no = 10, no2 = 20)

    //-------------------------------------------------------------------
    fun sum(no : Int) : Int{
        // 타입이 추론이 되면, 생략가능.
        var sum = 0
        for(i in 1..no){
            sum += i
        }
        return sum
    }
    val name:String = "kkang"
    println("name : $name, sum : ${sum(10)}, plus:${10+20}")
    println("Hello World")
    println("num의 값 : $num")
}