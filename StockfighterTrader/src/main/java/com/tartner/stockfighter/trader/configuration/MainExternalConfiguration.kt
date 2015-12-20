package com.tartner.stockfighter.trader.configuration

import com.fasterxml.jackson.annotation.JsonProperty
import io.dropwizard.Configuration
import org.hibernate.validator.constraints.NotEmpty

class MainExternalConfiguration : Configuration() {
    @NotEmpty @JsonProperty
    var template: String? = null

    @NotEmpty @JsonProperty
    var defaultName = "Stranger"
}
