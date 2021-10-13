package com.ycuwq.datepicker.date

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.util.AttributeSet
import com.ycuwq.datepicker.R
import com.ycuwq.datepicker.WheelPicker
import java.text.NumberFormat

/**
 * <pre>
 *
 *     author: Hy
 *     time  : 2021/10/13
 *     desc  : the custom number example
 *
 * </pre>
 */
class CustomPicker @JvmOverloads constructor(
    context: Context,
    val attributes: AttributeSet? = null,
    int: Int = R.attr.minNumber
) : WheelPicker<Int>(context, attributes, int) {
    private var mMinNum: Int = 0
    private var mMaxNum: Int = 0
    private var mDataList: ArrayList<Int> = arrayListOf()
    private var mSelectedListener:CustomPickerListener ?= null


    init {
        initAttrs()
        isShowCurtainBorder = false
        isShowCurtain = false
        itemMaximumWidthText = "00"
        selectedItemTextColor = Color.parseColor("#0AD28C") //green
        val numberFormat = NumberFormat.getNumberInstance()
        numberFormat.minimumIntegerDigits = 2
        dataFormat = numberFormat
        setList()
        setCurrentPosition(0,false)
    }

    private fun initAttrs(){
        attributes?.let {
            val a: TypedArray = context.obtainStyledAttributes(it, R.styleable.CustomPicker)
            mMinNum = a.getInt(R.styleable.CustomPicker_minNumber,0)
            mMaxNum = a.getInt(R.styleable.CustomPicker_maxNumber,20)
            a.recycle()
        }
    }


    fun setOnDataSelectListener(listener: CustomPickerListener){
        mSelectedListener = listener
        setOnWheelChangeListener { item, _ ->
            mSelectedListener?.onNumberSelected(item)
        }
    }


    fun setMaxAndMinNumber(maxNum: Int, minNum: Int) {
        mMaxNum = maxNum
        mMinNum = minNum
        setList()
    }

    private fun setList() {
        if (mDataList.isNotEmpty()) mDataList.clear()
        for (i: Int in mMinNum until mMaxNum) {
            mDataList.add(i)
        }
        dataList = mDataList
    }

    interface CustomPickerListener{
        fun onNumberSelected(number:Int)
    }

}