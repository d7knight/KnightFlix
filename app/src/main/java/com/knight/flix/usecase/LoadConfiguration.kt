package com.knight.flix.usecase

import com.knight.flix.data.ConfigurationRepository
import javax.inject.Inject

class LoadConfiguration @Inject constructor(private val configurationRepository: ConfigurationRepository) {
    operator fun invoke() {
        configurationRepository.loadConfiguration()
    }
}
