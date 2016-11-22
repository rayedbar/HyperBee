package net.therap.hyperbee.domain.enums;

import static net.therap.hyperbee.domain.constant.DomainConstant.*;

/**
 * @author bashir
 * @since 11/21/16
 */
public enum DisplayStatus {

    INACTIVE(DISPLAY_INACTIVE), ACTIVE(DISPLAY_ACTIVE);

    private String status;

    DisplayStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
