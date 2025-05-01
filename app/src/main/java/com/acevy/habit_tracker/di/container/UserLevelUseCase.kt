package com.acevy.habit_tracker.di.container

import com.acevy.habit_tracker.domain.usecase.userlevel.GetUserLevelUseCase
import com.acevy.habit_tracker.domain.usecase.userlevel.InsertUserLevelUseCase
import com.acevy.habit_tracker.domain.usecase.userlevel.UpdateUserLevelUseCase
import com.acevy.habit_tracker.domain.usecase.userlevel.UpdateUserXpAndLevelUseCase

data class UserLevelUseCases(
    val getUserLevelByUseCase: GetUserLevelUseCase,
    val insertUserLevelUseCase: InsertUserLevelUseCase,
    val updateUserLevelUseCase: UpdateUserLevelUseCase,
    val updateUserXpAndLevelUseCase: UpdateUserXpAndLevelUseCase
)