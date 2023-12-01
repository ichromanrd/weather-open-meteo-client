package com.ichromanrd.weather.common.constants

enum class WeatherCodes(val code: Int, val description: String) {
    /**
     * 0	        Clear sky
     * 1, 2, 3	    Mainly clear, partly cloudy, and overcast
     * 45, 48	    Fog and depositing rime fog
     * 51, 53, 55	Drizzle: Light, moderate, and dense intensity
     * 56, 57	    Freezing Drizzle: Light and dense intensity
     * 61, 63, 65	Rain: Slight, moderate and heavy intensity
     * 66, 67	    Freezing Rain: Light and heavy intensity
     * 71, 73, 75	Snow fall: Slight, moderate, and heavy intensity
     * 77	        Snow grains
     * 80, 81, 82	Rain showers: Slight, moderate, and violent
     * 85, 86	    Snow showers slight and heavy
     * 95 *	        Thunderstorm: Slight or moderate
     * 96, 99 *	    Thunderstorm with slight and heavy hail
     */
    CLEAR_SKY(0, "Clear Sky"),
    MAINLY_CLEAR(1, "Mainly Clear"),
    PARTLY_CLOUDY(2, "Partly Cloudy"),
    OVERCAST(3, "Overcast"),
    FOG(45, "Fog"),
    DEPOSITING_RIME_FOG(48, "Depositing Rime Fog"),
    DRIZZLE_LIGHT(51, "Drizzle: Light"),
    DRIZZLE_MODERATE(53, "Drizzle: Moderate"),
    DRIZZLE_DENSE_INTENSITY(55, "Drizzle: Dense Intensity"),
    FREEZING_DRIZZLE_LIGHT(56, "Freezing Drizzle: Light"),
    FREEZING_DRIZZLE_DENSE_INTENSITY(57, "Freezing Drizzle: Dense Intensity"),
    RAIN_SLIGHT(61, "Rain: Slight"),
    RAIN_MODERATE(63, "Rain: Moderate"),
    RAIN_HEAVY_INTENSITY(65, "Rain: Heavy Intensity"),
    FREEZING_RAIN_LIGHT(66, "Freezing Rain: Light"),
    FREEZING_RAIN_HEAVY_INTENSITY(67, "Freezing Rain: Heavy Intensity"),
    SNOW_FALL_SLIGHT(71, "Snow Fall: Slight"),
    SNOW_FALL_MODERATE(73, "Snow Fall: Moderate"),
    SNOW_FALL_HEAVY_INTENSITY(75, "Snow Fall: Heavy Intensity"),
    SNOW_GRAINS(77, "Snow Grains"),
    RAIN_SHOWERS_SLIGHT(80, "Rain Showers: Heavy Slight"),
    RAIN_SHOWERS_MODERATE(81, "Rain Showers: Moderate"),
    RAIN_SHOWERS_VIOLENT(82, "Rain Showers: Violent"),
    SNOW_SHOWERS_SLIGHT(85, "Snow Showers: Slight"),
    SNOW_SHOWERS_HEAVY(86, "Snow Showers: Heavy"),
    THUNDERSTORM(95, "Thunderstorm: Slight or Moderate"),
    THUNDERSTORM_WITH_SLIGHT_AND_HEAVY_HAIL_1(96, "Thunderstorm with Slight and Heavy Hail"),
    THUNDERSTORM_WITH_SLIGHT_AND_HEAVY_HAIL_2(99, "Thunderstorm with Slight and Heavy Hail");

    companion object {
        private val map = WeatherCodes.values().associateBy { it.code }
        infix fun from(value: Int) = map[value]
    }
}