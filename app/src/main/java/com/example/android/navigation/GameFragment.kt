/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.android.navigation.databinding.FragmentGameBinding

class GameFragment : Fragment() {

    val quiz = Quiz()
    private lateinit var binding: FragmentGameBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_game, container, false)

        // Bind this fragment class to the layout
        binding.game = this

        updateQuestionUI()

        // Set the onClickListener for the submitButton
        binding.submitButton.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER")
        { view: View ->
            val checkedId = binding.questionRadioGroup.checkedRadioButtonId
            // Do nothing if nothing is checked (id == -1)
            if (-1 != checkedId) {
                var answerIndex: Int = -1
                when (checkedId) {
                    R.id.firstAnswerRadioButton -> answerIndex = 0
                    R.id.secondAnswerRadioButton -> answerIndex = 1
                    R.id.thirdAnswerRadioButton -> answerIndex = 2
                    R.id.fourthAnswerRadioButton -> answerIndex = 3
                }
                if (quiz.isChosenAnswerCorrect(answerIndex)) {
                    if (quiz.allQuestionsDone) {
                        // We've won!  Navigate to the gameWonFragment.
                        view.findNavController().navigate(
                                GameFragmentDirections.actionNavFragmentGameToNavFragmentGameWon(quiz.numberOfQuestions, quiz.currentQuestionIndex))
                    } else {
                        // Not at the final question yet, continue.
                        quiz.nextQuestion()
                        updateQuestionUI()
                    }
                } else {
                    // Game over! A wrong answer sends us to the gameOverFragment.
                    view.findNavController().navigate(GameFragmentDirections.actionNavFragmentGameToNavFragmentGameOver())
                }
            }
        }
        return binding.root
    }

    private fun updateQuestionUI() {
        (activity as AppCompatActivity).supportActionBar?.title =
                getString(R.string.title_android_trivia_question,
                        quiz.currentQuestionIndex + 1,
                        quiz.numberOfQuestions)
        binding.invalidateAll()
    }
}
