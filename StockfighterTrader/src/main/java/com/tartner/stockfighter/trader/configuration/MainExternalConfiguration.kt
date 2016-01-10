package com.tartner.stockfighter.trader.configuration

import com.fasterxml.jackson.annotation.JsonProperty
import io.dropwizard.Configuration
import org.hibernate.validator.constraints.NotEmpty

class MainExternalConfiguration : Configuration() {
    companion object {
        public val APIKey = "4d05754c6ab729aae1ffb3858fc14da21c385b11"
    }

    @NotEmpty @JsonProperty
    var template: String? = null

    @NotEmpty @JsonProperty
    var defaultName = "Stranger"
}
