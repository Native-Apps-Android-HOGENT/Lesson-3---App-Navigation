package com.example.android.navigation

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class QuizTest {

    private lateinit var quiz: Quiz

    @Before
    fun setUp() {
        quiz = Quiz()
    }

    @Test
    fun quiz_constructor_initializesOK() {
        assertEquals(0, quiz.currentQuestionIndex)
        assertFalse(quiz.allQuestionsDone)
    }

    @Test
    fun quiz_nextQuestion_indexUpdates() {
        // Act
        quiz.nextQuestion()

        // Assert
        assertEquals(1, quiz.currentQuestionIndex)
    }

    @Test
    fun quiz_nextQuestion_showsDifferentAnswers() {
        // Arrange
        val originalAnswers = quiz.answers

        // Act
        quiz.nextQuestion()

        // Assert
        assertNotEquals(originalAnswers, quiz.answers)
    }

    @Test
    fun quiz_allQuestionsDone() {
        //Arrange
        repeat(quiz.numberOfQuestions) {
            quiz.nextQuestion()
        }

        //Assert
        assertTrue(quiz.allQuestionsDone)
    }
}