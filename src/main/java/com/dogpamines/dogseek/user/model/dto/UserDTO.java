package com.dogpamines.dogseek.user.model.dto;

import com.dogpamines.dogseek.common.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Schema(description = "회원 정보 DTO")
public class UserDTO {
    @Schema(description = "회원 코드(PK)")
    private int userCode;
    @Schema(description = "회원 플랫폼")
    private String userPlatform;
    @Schema(description = "회원 ID")
    @NotBlank(message = "아이디는 공백일 수 없습니다.")
    @Email
    private String userId;
    @Schema(description = "회원 비밀번호")
    @NotBlank(message = "비밀번호는 공백일 수 없습니다.")
    @Pattern(regexp = "^(?!((?=.*[A-Za-z]+)|(?=.*[~!@#$%^&*()_+=]+)|(?=.*[0-9]+))$)[A-Za-z\\d~!@#$%^&*()_+=]{8,12}$",
            message = "비밀번호는 영문, 숫자, 특수문자 중 두 종류 이상 포함된 8~12자의 글자여야 합니다.")
    private String userPass;
    @Schema(description = "회원 닉네임")
    @NotBlank(message = "닉네임은 공백일 수 없습니다.")
    @Pattern(regexp = "^(?=.*[a-z0-9가-힣])[a-z0-9가-힣]{2,7}$",
            message = "닉네임은 한글, 영문, 숫자 사용 가능하고 2~7자의 글자여야 합니다.")
    private String userNick;
    @Schema(description = "회원 연락처")
    @NotBlank(message = "연락처는 공백일 수 없습니다.")
    @Pattern(regexp = "^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$",
            message = "연락처 형태는 010-0000-0000 입니다.")
    private String userPhone;
    @Schema(description = "회원 가입일")
    private String userSignup;
    @Schema(description = "회원 최근접속일")
    private String userLatest;
    @Schema(description = "회원 권한")
    private UserRole userAuth;
    @Schema(description = "회원 탈퇴일")
    private String userLeave;
    @Schema(description = "회원 토큰")
    private String userToken;

    public UserDTO(){}

    public UserDTO(int userCode, String userPlatform, String userId, String userPass, String userNick, String userPhone, String userSignup, String userLatest, UserRole userAuth, String userLeave, String userToken) {
        this.userCode = userCode;
        this.userPlatform = userPlatform;
        this.userId = userId;
        this.userPass = userPass;
        this.userNick = userNick;
        this.userPhone = userPhone;
        this.userSignup = userSignup;
        this.userLatest = userLatest;
        this.userAuth = userAuth;
        this.userLeave = userLeave;
        this.userToken = userToken;
    }
    @Schema(description = "회원 권한")
    public List<String> getRole() {
        if (this.userAuth.getRole().length() > 0) {
            return Arrays.asList(this.userAuth.getRole().split(","));
        }
        return new ArrayList<>();
    }

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    public String getUserPlatform() {
        return userPlatform;
    }

    public void setUserPlatform(String userPlatform) {
        this.userPlatform = userPlatform;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public String getUserSignup() {
        return userSignup;
    }

    public void setUserSignup(String userSignup) {
        this.userSignup = userSignup;
    }

    public String getUserLatest() {
        return userLatest;
    }

    public void setUserLatest(String userLatest) {
        this.userLatest = userLatest;
    }

    public UserRole getUserAuth() {
        return userAuth;
    }

    public void setUserAuth(UserRole userAuth) {
        this.userAuth = userAuth;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserLeave() {
        return userLeave;
    }

    public void setUserLeave(String userLeave) {
        this.userLeave = userLeave;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userCode=" + userCode +
                ", userPlatform='" + userPlatform + '\'' +
                ", userId='" + userId + '\'' +
                ", userPass='" + userPass + '\'' +
                ", userNick='" + userNick + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userSignup='" + userSignup + '\'' +
                ", userLatest='" + userLatest + '\'' +
                ", userAuth=" + userAuth +
                ", userLeave='" + userLeave + '\'' +
                ", userToken='" + userToken + '\'' +
                '}';
    }
}
