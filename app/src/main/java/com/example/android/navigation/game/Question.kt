package com.example.android.navigation.game

/**
 * A data class representing a question, with its corresponding possible answers.
 * The first answers in the list is the correct answer for the question.
 */
data class Question(val text: String,
                    val answers: List<String>);