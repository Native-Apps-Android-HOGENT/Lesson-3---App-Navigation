---
author: Jens Buysse, Harm De Weirdt
title: Lesson 3: App Navigation
date: October 2019
---

# Questions about course videos?

# Lesson contents

## Navigation Graph: navigation made easy

![Navigation Graph Screenshot](assets/img/navigation_graph.PNG)

## SafeArgs: when Fragments need arguments

```
<fragment
    android:id="@+id/nav_fragment_gameWon"
    android:name="com.example.android.navigation.GameWonFragment"
    android:label="fragment_game_won"
    tools:layout="@layout/fragment_game_won">
    <action
        android:id="@+id/action_nav_fragment_gameWon_to_nav_fragment_game"
        app:destination="@id/nav_fragment_game"
        app:enterAnim="@anim/slide_in_left"
        app:exitAnim="@anim/slide_out_right"
        app:popEnterAnim="@anim/slide_in_right"
        app:popExitAnim="@anim/slide_out_left"
        app:popUpTo="@+id/nav_fragment_title"
        app:popUpToInclusive="false" />
    <argument
        android:name="numQuestions"
        app:argType="integer" />
    <argument
        android:name="numCorrect"
        app:argType="integer" />
</fragment>
```

# Changes from course solution

* [https://github.com/Native-Apps-Android-HOGENT/Lesson-3-App-Navigation](https://github.com/Native-Apps-Android-HOGENT/Lesson-3-App-Navigation)
⋅⋅* Espresso Tests
⋅⋅* IDs in layout in accordance with naming conventions

# Extra: Dual Pane layouts

![Example dual pane layouts](assets/img/dualpane.jpg)

## How?

[Master-Detail views with Navigation Components](https://proandroiddev.com/master-detail-views-with-navigation-components-a20405f31974) by Lara Martin
[https://github.com/Native-Apps-Android-HOGENT/Lesson-3-Extra-Master-Detail-Layout](https://github.com/Native-Apps-Android-HOGENT/Lesson-3-Extra-Master-Detail-Layout)

## Components of a Dual Pane Layout

* Two layout files for each layout (different qualifiers)
* Two NavControllers
* Code that builds layout differently depending on current configuration





