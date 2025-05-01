package com.acevy.habit_tracker.di.container

import com.acevy.habit_tracker.domain.usecase.user.GetUserByIdUseCase
import com.acevy.habit_tracker.domain.usecase.user.InsertUserUseCase
import com.acevy.habit_tracker.domain.usecase.user.UpdateUserUseCase

data class UserUseCases(
    val insertUserUseCase: InsertUserUseCase,
    val getUserByIdUseCase: GetUserByIdUseCase,
    val updateUserUseCase: UpdateUserUseCase,
)