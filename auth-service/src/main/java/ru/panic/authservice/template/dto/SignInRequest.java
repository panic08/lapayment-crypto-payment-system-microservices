package ru.panic.authservice.template.dto;

import lombok.Getter;

@Getter
public class SignInRequest {
    private String username;
    private String password;
    private Data data;
    @Getter
    public static class Data{
        private String ipAddress;
        private BrowserInfo browserInfo;
        private DeviceInfo deviceInfo;
        @Getter
        public static class BrowserInfo{
            private String browserName;
            private String browserVersion;
        }
        @Getter
        public static class DeviceInfo{
            private String deviceType;
            private String deviceName;
            private String operatingSystem;
        }
    }
}
