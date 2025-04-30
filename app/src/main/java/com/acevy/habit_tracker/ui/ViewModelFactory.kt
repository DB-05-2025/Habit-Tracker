package com.acevy.habit_tracker.ui

class ViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return when {
            modelClass.isAssignableFrom(HabitViewModel::class.java) -> {
                val useCases = Injection.provideHabitUseCases(context)
                HabitViewModel(useCases) as T
            }

            modelClass.isAssignableFrom(TrackViewModel::class.java) -> {
                val useCases = Injection.provideTrackUseCases(context)
                TrackViewModel(useCases) as T
            }

            modelClass.isAssignableFrom(ProgressViewModel::class.java) -> {
                val useCases = Injection.provideProgressUseCases(context)
                ProgressViewModel(useCases) as T
            }

            modelClass.isAssignableFrom(StackViewModel::class.java) -> {
                val useCases = Injection.provideStackUseCases(context)
                StackViewModel(useCases) as T
            }

            modelClass.isAssignableFrom(NotificationViewModel::class.java) -> {
                val useCases = Injection.provideNotificationUseCases(context)
                NotificationViewModel(useCases) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}
