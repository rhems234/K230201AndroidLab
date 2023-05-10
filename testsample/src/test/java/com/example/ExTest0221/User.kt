package com.example.ExTest0221
import java.util.*
import java.util.Scanner

val scanner: Scanner = Scanner(System.`in`);

class User(val id:String,val pw:String, val email:String, val phone:String)

class Login{
   companion object{
       fun loginTest(user:User){
           if(user.id.equals("admin") && user.pw.equals("1234")){
               println("로그인 성공")
           } else{
               println("로그인 실패")
           }
       }
   }
}

fun main(args:Array<String>) {
    print("ID 입력하세요 : ")
    val id = scanner.nextLine()

    print("PW 입력하세요 : ")
    val pw = scanner.nextLine()

    print("email 입력하세요 : ")
    val email = scanner.nextLine()

    print("phone 입력하세요 : ")
    val phone = scanner.nextLine()
    val lsy = User(id,pw,email,phone)

    Login.loginTest(lsy)
}