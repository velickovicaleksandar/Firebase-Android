package mk.ukim.finki.firebase_mpip.models

//Индекс
//Име
//Презиме
//Телефонски број
//Адреса на живеење
class Student(
    val index: String,
    val name: String,
    val lastname: String,
    val number: String,
    val address: String
) {
    constructor() : this("","","","","")

}