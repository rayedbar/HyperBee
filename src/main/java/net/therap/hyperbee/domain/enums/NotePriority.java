package net.therap.hyperbee.domain.enums;

import static net.therap.hyperbee.utils.constant.Constant.*;

/**
 * @author bashir
 * @since 11/22/16
 */
public enum NotePriority {

    HIGH(HIGH_PRIORITY),
    MEDIUM(MEDIUM_PRIORITY),
    LOW(LOW_PRIORITY);

    private String priority;

    NotePriority(String priority) {
        this.priority = priority;
    }

    public String getPriority() {
        return priority;
    }
}
