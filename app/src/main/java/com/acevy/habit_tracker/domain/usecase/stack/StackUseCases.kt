package com.acevy.habit_tracker.domain.usecase.stack

data class StackUseCases(
    val create: CreateStackUseCase,
    val update: UpdateStackUseCase,
    val delete: DeleteStackUseCase,
    val getAll: GetAllStacksUseCase,
    val getById: GetStackByIdUseCase
)
