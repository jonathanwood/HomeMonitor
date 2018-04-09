/*
 * Copyright 2018 Jonathan Shaw Wood.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.woodcomputing.homemonitor.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 *
 * @author <a href="mailto:jonathan@woodcomputing.com">Jonathan Wood</a>
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ForecastDate {
    
    private String epoch;
    private String pretty;
    private int day;
    private int month;
    private int year;
    private int yday;
    private int hour;
    private String min;
    private int sec;
    
    @JsonProperty(value = "isdst")
    private boolean dst;
    
    @JsonProperty(value = "monthname")
    private String monthName;

    @JsonProperty(value = "monthname_short")
    private String shortMonthName;
    
    private String weekday;
    
    @JsonProperty(value = "weekday_short")
    private String shortWeekday;
    
    private String ampm;
    
    @JsonProperty(value = "tz_short")
    private String shortTZ;

    @JsonProperty(value = "tz_long")
    private String longTZ;

}
