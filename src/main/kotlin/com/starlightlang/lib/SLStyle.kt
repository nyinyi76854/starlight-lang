package com.starlightlang.lib

import android.content.res.ColorStateList
import android.graphics.*
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.core.view.setPadding

class SLStyle {

    var width: Int? = null
    var height: Int? = null
    var minWidth: Int? = null
    var minHeight: Int? = null
    var maxWidth: Int? = null
    var maxHeight: Int? = null
    var weight: Float? = null
    var aspectRatio: Float? = null

    enum class FlexDirection { ROW, COLUMN }
    var flexDirection: FlexDirection? = null
    var flexWrap: Boolean? = null
    var justifyContent: JustifyContent? = null
    var alignItems: AlignItems? = null
    var alignContent: AlignItems? = null
    var gap: Int? = null
    var rowGap: Int? = null
    var columnGap: Int? = null

    var flexGrow: Float? = null
    var flexShrink: Float? = null
    var flexBasis: Int? = null
    enum class JustifyContent { FLEX_START, FLEX_END, CENTER, SPACE_BETWEEN, SPACE_AROUND }
    enum class AlignItems { FLEX_START, FLEX_END, CENTER, STRETCH }

    enum class Display { FLEX, BLOCK, NONE }
    var display: Display? = null

    var margin: Int? = null
    var marginLeft: Int? = null
    var marginTop: Int? = null
    var marginRight: Int? = null
    var marginBottom: Int? = null
    var marginHorizontal: Int? = null
    var marginVertical: Int? = null

    var padding: Int? = null
    var paddingLeft: Int? = null
    var paddingTop: Int? = null
    var paddingRight: Int? = null
    var paddingBottom: Int? = null
    var paddingHorizontal: Int? = null
    var paddingVertical: Int? = null

    var backgroundColor: Int? = null
    var backgroundGradient: IntArray? = null
    var cornerRadius: Float? = null
    var borderColor: Int? = null
    var borderWidth: Int? = null
    var borderStyle: BorderStyle? = null
    var rippleColor: Int? = null
    var pressedBackgroundColor: Int? = null

    enum class GradientDrawableShape { RECTANGLE, OVAL, LINE, RING }
    var shape: GradientDrawableShape? = null
    enum class BorderStyle { SOLID, DASHED, DOTTED }

    var visible: Boolean? = null
    var alpha: Float? = null
    var opacity: Float? = null

    var rotation: Float? = null
    var rotationX: Float? = null
    var rotationY: Float? = null
    var scaleX: Float? = null
    var scaleY: Float? = null
    var translationX: Float? = null
    var translationY: Float? = null
    var pivotX: Float? = null
    var pivotY: Float? = null
    var elevation: Float? = null
    var zIndex: Float? = null
    var transformOriginX: Float? = null
    var transformOriginY: Float? = null

    enum class Overflow { VISIBLE, HIDDEN }
    var overflow: Overflow? = null
    var overflowX: Overflow? = null
    var overflowY: Overflow? = null
    var clipToOutline: Boolean? = null

    enum class Position { RELATIVE, ABSOLUTE }
    var position: Position? = null
    var top: Int? = null
    var bottom: Int? = null
    var left: Int? = null
    var right: Int? = null

    var gravity: Int? = null
    var layoutGravity: Int? = null

    var textColor: Int? = null
    var textSize: Float? = null
    var bold: Boolean = false
    var italic: Boolean = false
    var typeface: Typeface? = null
    var letterSpacing: Float? = null
    var lineSpacingExtra: Float? = null
    var lineHeight: Float? = null
    var shadowColor: Int? = null
    var shadowRadius: Float? = null
    var shadowDx: Float? = null
    var shadowDy: Float? = null
    var shadowOpacity: Float? = null
    var textTransform: TextTransform? = null
    enum class TextTransform { NONE, UPPERCASE, LOWERCASE, CAPITALIZE }

    fun apply(view: View) {

        val params = view.layoutParams ?: ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        width?.let { params.width = it }
        height?.let { params.height = it }

        if (params is LinearLayout.LayoutParams) {
            weight?.let { params.weight = it }
        }
        view.layoutParams = params

        minWidth?.let { view.minimumWidth = it }
        minHeight?.let { view.minimumHeight = it }
        if (view is TextView) {
            maxWidth?.let { view.maxWidth = it }
            maxHeight?.let { view.maxHeight = it }
        }

        aspectRatio?.let { ratio ->
            view.post {
                val w = view.measuredWidth
                if (w > 0) {
                    view.layoutParams.height = (w / ratio).toInt()
                    view.requestLayout()
                }
            }
        }

        display?.let {
            view.visibility = when (it) {
                Display.NONE -> View.GONE
                else -> View.VISIBLE
            }
        }

        val pl = paddingLeft ?: paddingHorizontal ?: padding ?: 0
        val pr = paddingRight ?: paddingHorizontal ?: padding ?: 0
        val pt = paddingTop ?: paddingVertical ?: padding ?: 0
        val pb = paddingBottom ?: paddingVertical ?: padding ?: 0
        view.setPadding(pl, pt, pr, pb)

        if (params is ViewGroup.MarginLayoutParams) {
            val ml = marginLeft ?: marginHorizontal ?: margin ?: 0
            val mr = marginRight ?: marginHorizontal ?: margin ?: 0
            val mt = marginTop ?: marginVertical ?: margin ?: 0
            val mb = marginBottom ?: marginVertical ?: margin ?: 0
            params.setMargins(ml, mt, mr, mb)
            view.layoutParams = params
        }

        if (backgroundColor != null || borderColor != null || cornerRadius != null || backgroundGradient != null) {
            val drawable = GradientDrawable()
            drawable.shape = when(shape ?: GradientDrawableShape.RECTANGLE){
                GradientDrawableShape.RECTANGLE -> GradientDrawable.RECTANGLE
                GradientDrawableShape.OVAL -> GradientDrawable.OVAL
                GradientDrawableShape.LINE -> GradientDrawable.LINE
                GradientDrawableShape.RING -> GradientDrawable.RING
            }

            backgroundColor?.let { drawable.setColor(it) }
            backgroundGradient?.let { drawable.colors = it }

            borderColor?.let { color ->
                val widthPx = borderWidth ?: 2
                when(borderStyle){
                    BorderStyle.DASHED -> drawable.setStroke(widthPx, color, 10f, 10f)
                    BorderStyle.DOTTED -> drawable.setStroke(widthPx, color, 2f, 10f)
                    else -> drawable.setStroke(widthPx, color)
                }
            }

            view.background = drawable
        }

        rippleColor?.let { color ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && view.isClickable) {
                val mask = GradientDrawable().apply { setColor(Color.WHITE) }
                val ripple = android.graphics.drawable.RippleDrawable(
                    ColorStateList.valueOf(color),
                    view.background,
                    mask
                )
                view.background = ripple
            }
        }

        pressedBackgroundColor?.let { pressed ->
            backgroundColor?.let { normal ->
                val states = arrayOf(intArrayOf(android.R.attr.state_pressed), intArrayOf())
                view.backgroundTintList = ColorStateList(states, intArrayOf(pressed, normal))
            }
        }

        visible?.let { view.visibility = if (it) View.VISIBLE else View.GONE }
        alpha?.let { view.alpha = it }
        opacity?.let { view.alpha = it }

        rotation?.let { view.rotation = it }
        rotationX?.let { view.rotationX = it }
        rotationY?.let { view.rotationY = it }
        scaleX?.let { view.scaleX = it }
        scaleY?.let { view.scaleY = it }
        translationX?.let { view.translationX = it }
        translationY?.let { view.translationY = it }
        pivotX?.let { view.pivotX = it }
        pivotY?.let { view.pivotY = it }
        transformOriginX?.let { view.pivotX = it }
        transformOriginY?.let { view.pivotY = it }
        elevation?.let { ViewCompat.setElevation(view, it) }
        zIndex?.let { view.translationZ = it }

        if(view is ViewGroup){
            overflow?.let { view.clipChildren = it == Overflow.HIDDEN }
        }
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            clipToOutline?.let { view.clipToOutline = it }
        }

        if(view is LinearLayout){
            flexDirection?.let { view.orientation = if(it == FlexDirection.ROW) LinearLayout.HORIZONTAL else LinearLayout.VERTICAL }

            alignItems?.let { align ->
                view.gravity = when(align){
                    AlignItems.FLEX_START -> Gravity.TOP
                    AlignItems.FLEX_END -> Gravity.BOTTOM
                    AlignItems.CENTER -> Gravity.CENTER_VERTICAL
                    AlignItems.STRETCH -> Gravity.FILL_VERTICAL
                }
            }

            justifyContent?.let { justify ->
                when(justify){
                    JustifyContent.FLEX_START -> view.gravity = Gravity.START
                    JustifyContent.FLEX_END -> view.gravity = Gravity.END
                    JustifyContent.CENTER -> view.gravity = Gravity.CENTER_HORIZONTAL
                    JustifyContent.SPACE_BETWEEN -> {
                        for(i in 0 until view.childCount){
                            val child = view.getChildAt(i)
                            val lp = child.layoutParams
                            if(lp is LinearLayout.LayoutParams) lp.weight = 1f
                            child.layoutParams = lp
                        }
                    }
                    JustifyContent.SPACE_AROUND -> {
                        val gapPx = gap ?: 8
                        for(i in 0 until view.childCount){
                            val child = view.getChildAt(i)
                            val lp = child.layoutParams
                            if(lp is LinearLayout.LayoutParams){
                                lp.leftMargin = gapPx/2
                                lp.rightMargin = gapPx/2
                            }
                            child.layoutParams = lp
                        }
                    }
                }
            }
        }

        if(view is TextView){
            textColor?.let { view.setTextColor(it) }
            textSize?.let { view.textSize = it }

            if(bold && italic) view.setTypeface(view.typeface, Typeface.BOLD_ITALIC)
            else if(bold) view.setTypeface(view.typeface, Typeface.BOLD)
            else if(italic) view.setTypeface(view.typeface, Typeface.ITALIC)

            typeface?.let { view.typeface = it }
            letterSpacing?.let { view.letterSpacing = it }
            lineSpacingExtra?.let { view.setLineSpacing(it,1f) }
            lineHeight?.let { if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) view.lineHeight = it.toInt() }

            textTransform?.let {
                view.text = when(it){
                    TextTransform.UPPERCASE -> view.text.toString().uppercase()
                    TextTransform.LOWERCASE -> view.text.toString().lowercase()
                    TextTransform.CAPITALIZE -> view.text.toString().split(" ").joinToString(" ") { word -> word.replaceFirstChar { it.uppercase() } }
                    else -> view.text
                }
            }

            shadowColor?.let {
                view.setShadowLayer(shadowRadius ?: 4f, shadowDx ?: 0f, shadowDy ?: 2f, it)
            }
        }

        if(position == Position.ABSOLUTE){
            val lp = view.layoutParams
            if(lp is ViewGroup.MarginLayoutParams){
                top?.let { lp.topMargin = it }
                bottom?.let { lp.bottomMargin = it }
                left?.let { lp.leftMargin = it }
                right?.let { lp.rightMargin = it }
                view.layoutParams = lp
            }
        }
    }
}

fun <T: View> T.slStyle(block: SLStyle.() -> Unit): T{
    val style = SLStyle().apply(block)
    style.apply(this)
    return this
}