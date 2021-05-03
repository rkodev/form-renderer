package com.roksky.form_renderer.model

interface ElementValue<T> {
    fun toDisplayValue(): String
    fun value(): T
}