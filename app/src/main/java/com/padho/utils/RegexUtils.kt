package com.fdbanks.utils

import androidx.appcompat.widget.AppCompatEditText

import java.util.regex.Matcher
import java.util.regex.Pattern


object RegexUtils {
    private val PHONE_NUMBER_REGEX = Pattern.compile("[0]*[1-9][0-9]*")
    private val EMAIL_ADDRESS: Pattern = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )

    fun isValidEmail(email: String): Boolean {
        return EMAIL_ADDRESS.matcher(email.trim()).matches()
    }

    fun isValidPhone(phone:String):Boolean{
        return  PHONE_NUMBER_REGEX.matcher(phone.trim()).matches()
    }
    fun isTrimEmpty(value: String): Boolean {
        return value.trim().isEmpty()
    }

    fun isPasswordValid(password: String): Boolean {
        val regExpn = "^(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$"
        val inputStr: CharSequence = password
        val pattern: Pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE)
        val matcher: Matcher = pattern.matcher(inputStr)
        return matcher.matches()
    }

    fun textCheck(text: String): String {
        if (text.trim().isNotBlank() || text.trim().isNotEmpty()) {
            return text
        } else return ""

    }
    fun checkInputFileds(fields: AppCompatEditText):Boolean{
        var state=false
        if (fields.text.toString().trim().isEmpty()){
            state= false
        }else{
            state=true
        }
        return state
    }
}
