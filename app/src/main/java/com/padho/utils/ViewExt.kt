package com.fdbanks.utils

import android.app.Service
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.net.Uri
import android.text.*
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.transition.Slide
import android.transition.TransitionManager
import android.view.Gravity
import android.view.KeyEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.text.PrecomputedTextCompat
import androidx.core.view.isVisible
import androidx.core.widget.TextViewCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.padho.R
import com.google.android.material.snackbar.Snackbar


import java.io.File

fun View.showKeyboard() {
    (this.context.getSystemService(Service.INPUT_METHOD_SERVICE) as? InputMethodManager)
        ?.showSoftInput(this, 0)
}

fun View.hideKeyboard() {
    (this.context.getSystemService(Service.INPUT_METHOD_SERVICE) as? InputMethodManager)
        ?.hideSoftInputFromWindow(this.windowToken, 0)
}

fun View.toVisible() {
    this.visibility = View.VISIBLE
}

fun View.toGone() {
    this.visibility = View.GONE
}

fun View.toInvisible() {
    this.visibility = View.GONE
}


fun View.slideVisibility(visibility: Boolean, durationTime: Long = 300) {
    val transition = Slide(Gravity.BOTTOM)
    transition.apply {
        duration = durationTime
        addTarget(this@slideVisibility)
    }
    TransitionManager.beginDelayedTransition(this.parent as ViewGroup, transition)
    this.isVisible = visibility
}


/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            afterTextChanged.invoke(s.toString())
        }
    })
}

fun EditText.getString():String{
    return this.text.trim().toString()
}

fun ImageView.loadImage(@DrawableRes resId: Int) =   Glide.with(context)
    .load(resId).into(this)
//fun ImageView.loadImage(url: String) =
//    Picasso.get().load(url).placeholder(R.drawable.ic_healthy_food)
//        .error(R.drawable.ic_healthy_food).into(this)

fun ImageView.loadImage(@DrawableRes placeHolder: Int, url: String) =
    Glide.with(context)
        .load(url).placeholder(placeHolder).error(placeHolder).into(this)

fun ImageView.loadImage(context: Context, url: String) {
    Glide.with(context)
        .load(url)
        .into(this)
}


fun ImageView.loadImageFromUriRoundCorner(context: Context, path: String?) {
    Glide.with(context)
        .load(Uri.fromFile(File(path)))
        .apply(RequestOptions().transforms(CenterCrop(), RoundedCorners(10)))
        .into(this)
}

fun AppCompatTextView.setTextFutureExt(text: String) =
    setTextFuture(
        PrecomputedTextCompat.getTextFuture(
            text,
            TextViewCompat.getTextMetricsParams(this),
            null
        )
    )

fun AppCompatEditText.setTextFutureExt(text: String) =
    setText(
        PrecomputedTextCompat.create(text, TextViewCompat.getTextMetricsParams(this))
    )

fun TextView.makeTextLink(
    str: String,
    underlined: Boolean,
    isBold: Boolean = false,
    color: Int?,
    action: (() -> Unit)? = null
) {
    val spannableString = SpannableString(text)
    val textColor = color ?: currentTextColor
    val clickableSpan = object : ClickableSpan() {
        override fun onClick(textView: View) {
            action?.invoke()
        }

        override fun updateDrawState(drawState: TextPaint) {
            super.updateDrawState(drawState)
            drawState.isUnderlineText = underlined
            drawState.color = textColor
            if (isBold)
                drawState.typeface = Typeface.DEFAULT_BOLD
        }
    }
    val index = spannableString.indexOf(str)
    spannableString.setSpan(
        clickableSpan,
        index,
        index + str.length,
        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    text = spannableString
    movementMethod = LinkMovementMethod.getInstance()
    highlightColor = Color.TRANSPARENT

}


fun TextView.setSpanString(
    message: String?,
    color: Int = R.color.light_blue,
    startPos: Int,
    isBold: Boolean = false,
    isUnderLine: Boolean = false,
    endPos: Int = message?.length ?: 0,
    onClick: () -> Unit = {}
) {

    val ss = SpannableString(message)
    val clickableSpan = object : ClickableSpan() {
        override fun onClick(textView: View) {
            onClick()
        }

        override fun updateDrawState(ds: TextPaint) {
            super.updateDrawState(ds)
            ds.isUnderlineText = isUnderLine
            ds.color = ContextCompat.getColor(context, color)
            if (isBold)
                ds.typeface = Typeface.DEFAULT_BOLD
        }
    }

    ss.setSpan(clickableSpan, startPos, endPos, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

    text = ss
    movementMethod = LinkMovementMethod.getInstance()
    highlightColor = ContextCompat.getColor(context, android.R.color.transparent)

}

fun TextView.setSpanStringWhite(
    message: String?,
    color: Int = R.color.white,
    startPos: Int,
    isBold: Boolean = false,
    isUnderLine: Boolean = false,
    endPos: Int = message?.length ?: 0,
    onClick: () -> Unit = {}
) {

    val ss = SpannableString(message)
    val clickableSpan = object : ClickableSpan() {
        override fun onClick(textView: View) {
            onClick()
        }

        override fun updateDrawState(ds: TextPaint) {
            super.updateDrawState(ds)
            ds.isUnderlineText = isUnderLine
            ds.color = ContextCompat.getColor(context, color)
            if (isBold)
                ds.typeface = Typeface.DEFAULT_BOLD
        }
    }

    ss.setSpan(clickableSpan, startPos, endPos, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

    text = ss
    movementMethod = LinkMovementMethod.getInstance()
    highlightColor = ContextCompat.getColor(context, android.R.color.transparent)

}

fun TextView.isBlank(): Boolean = text.toString().trim().isNullOrEmpty()

fun View.hideKeyBoard() {

    this.let {
        val imm =
            this.context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.hideSoftInputFromWindow(it.windowToken, 0)
    }
}

fun TextView.content(): String = text.toString().trim()
fun radioBTColor(): ColorStateList {
    return ColorStateList(
        arrayOf(
            intArrayOf(-android.R.attr.state_checked),
            intArrayOf(android.R.attr.state_checked)
        ), intArrayOf(
            Color.GRAY,  //disabled
            Color.BLACK //enabled
        )
    )
}



