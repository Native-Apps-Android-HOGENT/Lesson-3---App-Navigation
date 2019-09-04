package com.example.android.navigation

import kotlin.math.min

class Quiz {

    data class Question(val text: String, val answers: List<String>)

    val allQuestionsDone: Boolean
        get() = currentQuestionIndex == numberOfQuestions

    // The first answer is the correct one.  We randomize the answers before showing the text.
    // All questions must have four answers.  We'd want these to contain references to string
    // resources so we could internationalize. (or better yet, not define the questions in code...)
    private val questions: MutableList<Question> = mutableListOf(
            Question(text = "What is Android Jetpack?",
                    answers = listOf("all of these", "tools", "documentation", "libraries")),
            Question(text = "Base class for Layout?",
                    answers = listOf("ViewGroup", "ViewSet", "ViewCollection", "ViewRoot")),
            Question(text = "Layout for complex Screens?",
                    answers = listOf("ConstraintLayout", "GridLayout", "LinearLayout", "FrameLayout")),
            Question(text = "Pushing structured data into a Layout?",
                    answers = listOf("Data Binding", "Data Pushing", "Set Text", "OnClick")),
            Question(text = "Inflate layout in fragments?",
                    answers = listOf("onCreateView", "onActivityCreated", "onCreateLayout", "onInflateLayout")),
            Question(text = "Build system for Android?",
                    answers = listOf("Gradle", "Graddle", "Grodle", "Groyle")),
            Question(text = "Android vector format?",
                    answers = listOf("VectorDrawable", "AndroidVectorDrawable", "DrawableVector", "AndroidVector")),
            Question(text = "Android Navigation Component?",
                    answers = listOf("NavController", "NavCentral", "NavMaster", "NavSwitcher")),
            Question(text = "Registers app with launcher?",
                    answers = listOf("intent-filter", "app-registry", "launcher-registry", "app-launcher")),
            Question(text = "Mark a layout for Data Binding?",
                    answers = listOf("<layout>", "<binding>", "<data-binding>", "<dbinding>"))
    )

    lateinit var answers: MutableList<String>

    val currentQuestion: Question
        get() = questions[currentQuestionIndex]

    // Start at -1 so we can use nextQuestion during initialization
    var currentQuestionIndex = -1
        private set

    val numberOfQuestions = min((questions.size + 1) / 2, 3)

    init {
        questions.shuffle()
        nextQuestion()
    }

    fun nextQuestion() {
        if (!allQuestionsDone) {
            currentQuestionIndex++
            // randomize the answers into a copy of the array
            answers = currentQuestion.answers.toMutableList()
            // and shuffle them
            answers.shuffle()
        }
    }

    fun isChosenAnswerCorrect(chosenAnswerIndex: Int): Boolean {
        // The first answer in the original question is always the correct one, so if our
        // answer matches, we have the correct answer.
        return (answers[chosenAnswerIndex] == currentQuestion.answers[0])

    }

}