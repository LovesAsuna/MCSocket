package org.sct.mcsocket.enumeration;

import lombok.Getter;

/**
 * @author icestar
 */

public enum ConfigType {

    /*调试路径*/
    DEBUG("settings.debug");


    @Getter String path;

    private ConfigType(String path) {
        this.path = path;
    }
}
