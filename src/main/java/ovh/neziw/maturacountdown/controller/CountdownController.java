/*
 * This file is part of "Spring-MaturaCountdown", licensed under MIT License.
 *
 *  Copyright (c) 2025 neziw
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */
package ovh.neziw.maturacountdown.controller;

import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ovh.neziw.maturacountdown.configuration.EventDayConfiguration;

@Controller
public class CountdownController {

    private final EventDayConfiguration eventDayConfiguration;

    public CountdownController(final EventDayConfiguration eventDayConfiguration) {
        this.eventDayConfiguration = eventDayConfiguration;
    }

    @GetMapping("/")
    public String countdownPage(final Model model) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy, HH:mm").withLocale(Locale.forLanguageTag("pl"));
        final String formattedDate = this.eventDayConfiguration.getEventDateTime().format(formatter);
        model.addAttribute("eventTimestamp", this.eventDayConfiguration.getEventDateTime().toInstant(ZoneOffset.UTC).toEpochMilli());
        model.addAttribute("formattedDate", formattedDate);
        return "countdown";
    }
}