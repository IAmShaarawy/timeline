package dev.elshaarawy.timeline.features.timeline

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.elshaarawy.timeline.data.repositories.TimelineRepository

/**
 * @author Mohamed Elshaarawy on Dec 23, 2019.
 */
class TimelineViewModel(timelineRepository: TimelineRepository) : ViewModel() {
    val timelineLiveData by lazy { timelineRepository.loadTimeline(viewModelScope) }
}