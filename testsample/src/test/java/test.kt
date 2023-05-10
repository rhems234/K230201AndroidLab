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
// 코틀린에서는 기본적으로 상속이 안됩니다.
// 그래서, 필요한 키워드가 : open
// 부모꺼를 사용 할려면, 반드시 초기화를 해야 사용가능합니다.
// super() 주생성자 호출 해야합니다.
open class Super{
    open var superData = 10
    // 접근 지정자 확인, protected 만 확인
    // 결론, 자식 클래스에서만, 부모 클래스 멤버에 접근 가능 여부 확인.
    // 즉, main 함수에서는 접근 불가
    protected var protectedData = 20
    open fun superFun() {
        println("I am spuerFun : $superData")
    }
}
class Sub : Super(){
    // 부모의 멤버를 다시 재정의해서 사용할려고 하는데. 안됨.
    // 이유? 키워드, open, override 없어서.
    override var superData = 20

    override fun superFun() {
        // 자식 클래스 접근 가능.
        protectedData++
       println("자식의 재정의 : " +superData)
    }
}

class User3(name: String){
    // 주생성자 생략 -> 디폴트 생성자를 만들어줌.
    // 보조 생성자를 이용

    // 문제점 -> 주 생성자와, 보조 생성자가 같이 있을 경우
    // 해결책 -> 보조 생성자에서, 주 생성자로 연결하는 부분이 필요
    // this
    // 결론, 보조 생성자를 이용하면 되지만, 주 생성자를 이용해라.
    // 작업 할 때에도, 주 생성자에서 작업을 더 많이 하는 편.
    constructor(name: String, count: Int) : this(name){

    }
}

class User2(val name: String, count:Int){
    // 주생성자의 지역변수 name
    // 주생성자는 class 이름 옆에 선언이 되고, constructor를 생략을 많이함

    // 클랙스의 멤버 변수 name
    //var name="lsy"
    init {
        // init 함수 안에서는 주 생성자의 매개변수를 사용가능.
        // 하지만, class 멤버 변수로는 사용이 불가능.
        println("init 호출. 주생성자 매개변수 사용 : $name")
    }

    fun someFun() {
        println("init 호출. 주생성자 매개변수 사용 : $name")
    }
}

class User{ // 주 생성자가 생략이 되었고, 보조 생성자를 사용.
    var name = "pjs"
    // 생성자 모양이, 자바에서는 클래스명으로 똑같지만,
    // 코틀린은 생성자 키워드가 따로 존재함.
    // 보조 생성자
    constructor(name : String) {
        this.name = name
    }
    fun someFun() {
        println("name : $name")
    }
    class SomeClass{ }
}

// 비 data 클래스
class NonDataClass(val name: String, val pw:String){
    lateinit var email:String
    constructor(name: String, pw: String, email:String):this(name, pw) {
        this.email = email
    }
}
//  data 클래스 -> 실제 값을 비교 해주는 변수는 주 생성자의 변수만 해줌.
data class DataClass(val name: String, val pw:String){

}

fun main() {
    // data 클래스 실제 값 비교
    val data13 = DataClass("lsy", "1234")
    val data14 = DataClass("lsy", "1234")
    println("data13 주소값 : $data13")
    println("data14 주소값 : $data14")
    println("equals 이용한 값비교 : ${data13.equals(data14)}")

    // 실제 값이 아닌, 메모리 주소값 비교 부분 확인.
    val non1 = NonDataClass("lsy", "1234")
    val non2 = NonDataClass("lsy", "1234")
    println("non1의 주소값 : $non1")
    println("non2의 주소값 : $non2")
    println("equals 이용한 값비교 : ${non1.equals(non2)}")

    //=================================================================
    val obj = Sub()
    obj.superData = 20
    obj.superFun()
    // 접근 불가
    //obj.protectedData

    //=================================================================
    var user3 = User3("kkang", 10)

    // ================================================================
    val user2 = User2("pjs", count = 10)
    println("user2 사용해보기 : " +user2)

    //객체 생성, 인스턴스 생성
    // 자바 : User user = new User("pjs");
    val user = User("박준성")
    println("user의 name : "+user.name)
    user.someFun()


    //==============================================================
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