package com.example.android.navigation.game

sealed class BaseCommand {
    /**
     * When the user inputs an error
     */
    class Error(val errorString : String) : BaseCommand()

    /**
     * When the user answers three questions correct
     */
    class Finish(val finishString : String): BaseCommand()
    /**
     * When the user inputs correct
     */
    class Correct(val correctString: String): BaseCommand()
}