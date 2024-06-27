package com.dogpamines.dogseek.redistTest;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/redistest")
public class LoginController {

    private final LoginService loginService;
    private final CacheService cacheService;

    public LoginController(LoginService loginService, CacheService cacheService) {
        this.loginService = loginService;
        this.cacheService = cacheService;
    }

    @GetMapping("/login")
    public String loginUser(@RequestParam("memberId") String memberId) {
        System.out.println("loginUser...");
        if (loginService.checkAvailableMember(memberId)) {
            System.out.println("checkAvailableMember is true");
            cacheService.visitMember(memberId);
            return "OK";
        } else {
            System.out.println("checkAvailableMember is false");
            throw new IllegalArgumentException("올바르지 않은 Member");
        }
    }

    @GetMapping("/count")
    public Long getUserVisitedCount() {
        System.out.println("getUserVisitedCount...");
        return cacheService.getMemberVisitedCount()
                .orElseThrow(IllegalArgumentException::new);
    }

    @DeleteMapping("/visitant")
    public String clearVisitant() {
        System.out.println("clearVisitant...");
        cacheService.clearVisitant();
        return "OK";
    }
}
