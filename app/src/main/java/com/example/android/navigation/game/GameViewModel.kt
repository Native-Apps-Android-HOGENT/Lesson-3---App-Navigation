package com.example.android.navigation.game

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.android.navigation.R

class GameViewModel: ViewModel(){

    /**
     * The current question in the game
     */
    private val  _currentQuestion = MutableLiveData<Question>()
    val currentQuestion : LiveData<Question>
    get() = _currentQuestion

    /**
     * The list with possible answers of the current question
     */
    private val _answers =  MutableLiveData<MutableList<String>>()
    val answers : LiveData<MutableList<String>>
    get() = _answers


    val command = SingleLiveEvent<BaseCommand>()

    /**
     * The index of the current question.
     */
     var questionIndex = 0

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
     val numQuestions = Math.min((questions.size + 1) / 2, 3)

    init {
        // Shuffles the questions and sets the question index to the first question.
        randomizeQuestions()
    }


    /**
     * Checks whether the answer  provided by the user is correct.
     */
    fun checkIfCorrectAnswer( checkedId: Int){

        // Do nothing if nothing is checked (id == -1)
        if (-1 != checkedId) {
            var answerIndex = 0
            when (checkedId) {
                R.id.secondAnswerRadioButton -> answerIndex = 1
                R.id.thirdAnswerRadioButton -> answerIndex = 2
                R.id.fourthAnswerRadioButton -> answerIndex = 3
            }
            // The first answer in the original question is always the correct one, so if our
            // answer matches, we have the correct answer.
            if (_answers.value!![answerIndex] == _currentQuestion.value!!.answers[0]) {
                questionIndex++
                // Advance to the next question
                if (questionIndex < numQuestions) {
                    _currentQuestion.value = questions[questionIndex]
                    setQuestion()
                    //binding.invalidateAll()
                    command.value = BaseCommand.Correct("Answer is correct")
                } else {
                    // We've won!  Navigate to the gameWonFragment.
                    command.value = BaseCommand.Finish("You've won")
                    //view.findNavController().navigate(
                      //      GameFragmentDirections.actionNavFragmentGameToNavFragmentGameWon(numQuestions, questionIndex))
                    //return GAME_FINISHED
                }
            } else {
                // Game over! A wrong answer sends us to the gameOverFragment.
                command.value = BaseCommand.Error("Error and finish the game")
                //view.findNavController().navigate(GameFragmentDirections.actionNavFragmentGameToNavFragmentGameOver())
                //return INCORRECT
            }
        }
        //return DO_NOTHING

    }

    /**
     * Randomize the questions and set the first question
     */

    private fun randomizeQuestions() {
        questions.shuffle()
        questionIndex = 0
        setQuestion()
    }


    private fun setQuestion() {
        _currentQuestion.value= questions[questionIndex]
        // randomize the answers into a copy of the array
        _answers.value = currentQuestion.value!!.answers.toMutableList()
        // and shuffle them
        answers.value!!.shuffle()
        //(activity as AppCompatActivity).supportActionBar?.title = getString(R.string.title_android_trivia_question, questionIndex + 1, numQuestions)
    }

    companion object{
        const val CORRECT = 1
        const val INCORRECT = 2
        const val GAME_FINISHED = 3
        const val DO_NOTHING = 4
    }
}